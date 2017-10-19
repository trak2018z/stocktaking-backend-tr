package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.RaportEntity;
import com.brotherhood.stocktaking.models.entities.RaportOrderEntity;
import com.brotherhood.stocktaking.models.entities.RaportStatus;
import com.brotherhood.stocktaking.repositories.RaportRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.RaportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaportService {
    private RaportRepository raportRepository;

    @Autowired
    public RaportService(RaportRepositoryImpl raportRepository) {
        this.raportRepository = raportRepository;
    }

    public List<RaportEntity> getAllRaports(Integer userId) {
        return raportRepository.get(userId);
    }

    public List<RaportOrderEntity> getAllRaportsOrders(Integer userId) {
        return raportRepository.getRaportOrders(userId);
    }

    public boolean deleteRaport(Integer raportId) {
        return raportRepository.deleteRaport(raportId);
    }

    public boolean deleteRaportOrder(Integer raportOrderId) {
        return raportRepository.deleteRaportOrder(raportOrderId);
    }

    public boolean updateRaport(Integer raportId, RaportStatus raportStatus) {
        return raportRepository.updateRaportStatus(raportId, raportStatus);
    }

    public boolean addRaport() {
        return raportRepository.add(null);
    }

    public boolean addRaportOrder() {
        return raportRepository.addOrder(null);
    }
}
