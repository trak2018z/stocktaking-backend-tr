package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.RaportEntity;
import com.brotherhood.stocktaking.models.entities.RaportOrderEntity;
import com.brotherhood.stocktaking.models.requests.CreateRaportOrderRequest;
import com.brotherhood.stocktaking.models.requests.UpdateRaportRequest;
import com.brotherhood.stocktaking.repositories.RaportRepositoryImpl;
import com.brotherhood.stocktaking.repositories.UserRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.RaportRepository;
import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
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

    public List<RaportEntity> getAllRaports(Integer userId, int page) {
        return raportRepository.get(userId, page);
    }

    public List<RaportOrderEntity> getAllRaportsOrders(Integer userId) {
        return raportRepository.getRaportOrders(userId);
    }

    public List<RaportOrderEntity> getAllRaportsOrders() {
        return raportRepository.getRaportOrders();
    }

    public boolean deleteRaport(Integer raportId) {
        return raportRepository.deleteRaport(raportId);
    }

    public boolean deleteRaportOrder(Integer raportOrderId) {
        return raportRepository.deleteRaportOrder(raportOrderId);
    }

    public boolean updateRaport(UpdateRaportRequest updateRaportRequest) {
        return raportRepository.updateRaportStatus(updateRaportRequest);
    }

    public boolean addRaport() {
        return raportRepository.add(null);
    }

    public boolean addRaportOrder(int userId, CreateRaportOrderRequest orderRequest) {
        return raportRepository.addOrder(userId, orderRequest);
    }
}
