package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.*;
import com.brotherhood.stocktaking.models.requests.CreateRaportOrderRequest;
import com.brotherhood.stocktaking.models.requests.UpdateRaportRequest;

import java.util.List;

public interface RaportRepository {
    List<RaportEntity> get(Integer userId, Integer page);

    List<RaportOrderEntity> getRaportOrders(Integer userId);

    List getRaportOrders();

    RaportEntity getRaport(Integer raportId);

    RaportOrderEntity getRaportOrder(Integer raportOrderId);

    boolean deleteRaportOrder(Integer raportOrderId);
    
    boolean updateRaportStatus(UpdateRaportRequest updateRaportRequest);

    boolean deleteRaport(Integer rapordId);

    boolean add(RaportEntity raportEntity);

    boolean addOrder(int userId, CreateRaportOrderRequest orderRequest);

    boolean addItemsListForRaportOrder(Integer raportOrderId, List<Integer> itemsIds);

    void addRaportOrderItem(RaportOrderItemEntity raportOrderEntity);

    void addRaportOrderLocalization(RaportOrderLocalizationEntity raportOrderLocalizationEntity);

    void addRaportOrderUserEntity(RaportOrderUserEntity raportOrderUserEntity);

    void removeRaportOrderUserEntitie(Integer raportOrderId);
}
