package com.brotherhood.stocktaking.scheduling;

import com.brotherhood.stocktaking.models.entities.*;
import com.brotherhood.stocktaking.models.requests.UpdateRaportRequest;
import com.brotherhood.stocktaking.repositories.LocalizationRepositoryImpl;
import com.brotherhood.stocktaking.repositories.RaportRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.LocalizationRepository;
import com.brotherhood.stocktaking.repositories.interfaces.RaportRepository;
import com.brotherhood.stocktaking.services.RaportService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;

@Component
@Transactional
public class ProcessRaportsTask {
    private final static String RAPORTS_DIR = "/var/www/bth.nazwa.pl/public_html/raports/";
    private RaportService raportService;
    private RaportRepository raportRepository;
    private LocalizationRepository localizationRepository;

    @Autowired
    public ProcessRaportsTask(RaportService raportService, RaportRepositoryImpl raportRepository
            , LocalizationRepositoryImpl localizationRepository) {
        this.raportService = raportService;
        this.raportRepository = raportRepository;
        this.localizationRepository = localizationRepository;

        File theDir = new File(RAPORTS_DIR);

        if (!theDir.exists()) {
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                se.printStackTrace();
            }
            if (result) {
                System.out.println("DIR for raports created");
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void executeTask() {
        for (RaportOrderEntity order : raportService.getAllRaportsOrders()) {
            System.out.println("generating order with id: " + order.getRaportOrderId());
            try {
                generatePdf(order);
            } catch (FileNotFoundException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    protected void generatePdf(RaportOrderEntity raportOrder) throws FileNotFoundException, DocumentException {
        RaportEntity raportEntity = raportRepository.getRaport(raportOrder.getRaportOrderId());
        Document document = new Document();
        String filePath = RAPORTS_DIR + System.currentTimeMillis() + ".pdf";
        raportService.updateRaport(new UpdateRaportRequest(raportEntity.getRaportId(), RaportStatus.PENDING, null));
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        RaportEntity raport = raportRepository.getRaport(raportOrder.getRaportOrderId());
        document.add(new Paragraph(raport.getRaportName()));

        StringBuilder usersList = new StringBuilder();
        for (String userName : raportOrder.getUsersNamesJson().split("\\|")) {
            usersList.append("+ ").append(userName).append("\n");
        }
        if (usersList.length() == 0) {
            usersList = new StringBuilder(" empty ");
        }

        StringBuilder localizationsList = new StringBuilder();
        for (String localization : raportOrder.getLocalizationsJson().split("\\|")) {
            localizationsList.append("+ ").append(localization).append("\n");
        }
        if (localizationsList.length() == 0) {
            localizationsList.append(" empty ");
        }

        Chunk chunk = new Chunk(" \n\n", font);
        chunk.append("\nRaport owner: " + raport.getUser().getName() + " " + raport.getUser().getSurname());
        chunk.append("\n\n\nRaport params \n\tMin value: \t"
                + (raportOrder.getValueMin() == null ? "-" : raportOrder.getValueMin())
                + "\n\tMax value: \t"
                + (raportOrder.getValueMax() == null ? "-" : raportOrder.getValueMax())
                + "\n\tCode types: \t" + raportOrder.getCodeType().name()
                + "\n\n\n\tUsers list containing raport items:\t\n" + usersList
                + "\n\n\n\tLocalizations list:\t\n" + localizationsList);
        ArrayList<ItemEntity> resultItemsList = new ArrayList<>();
        String[] usersListArray = raportOrder.getUsersNamesJson().split("\\|");
        String[] localizationsArray = raportOrder.getLocalizationsJson().split("\\|");
        for (String localizationString : localizationsArray) {
            LocalizationEntity localizationEntity = localizationRepository.get(localizationString);

            if (localizationEntity.getItemEntities() != null) {
                for (ItemEntity item : localizationEntity.getItemEntities()) {
                    if (raportOrder.getCodeType() == CodeType.QR || raportOrder.getCodeType() == CodeType.BARCODE) {
                        if (item.getCodeType() != raportOrder.getCodeType()) {
                            continue;
                        }
                    }

                    if (raportOrder.getValueMin() != null && item.getValue() < raportOrder.getValueMin()) {
                        continue;
                    }
                    if (raportOrder.getValueMax() != null && item.getValue() > raportOrder.getValueMax()) {
                        continue;
                    }

                    for (String user : usersListArray) {
                        if ((item.getUser().getName() + " " + item.getUser().getSurname()).toLowerCase()
                                .equals(user.toLowerCase())) {
                            resultItemsList.add(item);
                        }
                    }

                }
            }
        }
        chunk.append("\n\n\n\n");
        document.add(chunk);

        chunk = new Chunk("", font);
        BigDecimal sum = new BigDecimal(0);
        for (ItemEntity item : resultItemsList) {
            sum = sum.add(new BigDecimal(item.getValue() * item.getCount()));
            chunk
                    .append("Name: ").append(item.getName()).append("\n")
                    .append("Count: ").append(String.valueOf(item.getCount())).append("\n")
                    .append("Localization: ").append(item.getLocalization().getName()).append("\n")
                    .append("Single value: ").append(String.valueOf(item.getValue())).append("\n")
                    .append("Total value: ").append(String.valueOf(item.getValue() * item.getCount())).append("\n")
                    .append("Owner: ").append(item.getUser().getName()).append(" ").append(item.getUser().getSurname())
                    .append("\n").append("------------------------------------------------------\n");
        }
        chunk.append("\n\n\nTotal sum of items: " + sum.toString());
        document.add(chunk);
        document.close();

        Path p = Paths.get(filePath);
        String groupName = "www-data";
        UserPrincipalLookupService lookupService = FileSystems.getDefault()
                .getUserPrincipalLookupService();
        GroupPrincipal group = null;
        try {
            group = lookupService.lookupPrincipalByGroupName(groupName);
            Files.getFileAttributeView(p, PosixFileAttributeView.class,
                    LinkOption.NOFOLLOW_LINKS).setGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }

        raportService.updateRaport(new UpdateRaportRequest(raportEntity.getRaportId(), RaportStatus.FINISHED, filePath));
        System.out.println("now");
    }
}