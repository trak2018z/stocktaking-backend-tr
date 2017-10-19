package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.RaportEntity;
import com.brotherhood.stocktaking.models.entities.RaportOrderEntity;
import com.brotherhood.stocktaking.models.entities.RaportStatus;

import java.util.List;

public interface RaportRepository {
    List<RaportEntity> get(Integer userId);
    
    List<RaportOrderEntity> getRaportOrders(Integer userId);
    
    boolean deleteRaportOrder(Integer raportOrderId);
    
    boolean updateRaportStatus(Integer raportOrderId, RaportStatus newRaportStatus);

    boolean deleteRaport(Integer rapordId);

    boolean add(RaportEntity raportEntity);

    boolean addOrder(RaportOrderEntity raportOrderEntity);
}
