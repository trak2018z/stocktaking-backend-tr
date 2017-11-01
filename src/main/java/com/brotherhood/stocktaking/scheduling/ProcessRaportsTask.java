package com.brotherhood.stocktaking.scheduling;

import com.brotherhood.stocktaking.models.entities.RaportEntity;
import com.brotherhood.stocktaking.models.entities.RaportOrderEntity;
import com.brotherhood.stocktaking.models.entities.RaportStatus;
import com.brotherhood.stocktaking.models.requests.UpdateRaportRequest;
import com.brotherhood.stocktaking.services.RaportService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class ProcessRaportsTask {
    private final static String RAPORTS_DIR = "";
    private RaportService raportService;

    @Autowired
    public ProcessRaportsTask(RaportService raportService) {
        this.raportService = raportService;
    }

    @Scheduled(fixedRate = 10000)
    public void executeTask() {
        System.out.println("Start generating RaportOrder!");
        for (RaportOrderEntity order : raportService.getAllRaportsOrders()) {
            System.out.println("generating order with id: " + order.getRaportOrderId());
            try {
                generatePdf(order);
            } catch (FileNotFoundException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    private void generatePdf(RaportOrderEntity raportOrder) throws FileNotFoundException, DocumentException {
        RaportEntity raportEntity = raportOrder.getRaportEntity();
        Document document = new Document();
        String filePath = RAPORTS_DIR + System.currentTimeMillis() + ".pdf";

        raportService.updateRaport(new UpdateRaportRequest(raportEntity.getRaportId(), RaportStatus.PENDING, null));

        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(raportOrder.getUser().getName(), font);
        chunk.append(" test text");
        document.add(chunk);
        document.close();

        raportService.updateRaport(new UpdateRaportRequest(raportEntity.getRaportId(), RaportStatus.FINISHED, filePath));
    }
}