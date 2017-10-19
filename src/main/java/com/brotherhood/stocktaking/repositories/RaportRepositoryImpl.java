package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.RaportEntity;
import com.brotherhood.stocktaking.models.entities.RaportOrderEntity;
import com.brotherhood.stocktaking.models.entities.RaportStatus;
import com.brotherhood.stocktaking.repositories.interfaces.RaportRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RaportRepositoryImpl extends AbstractRepository implements RaportRepository {
    @Override
    public List<RaportEntity> get(Integer userId) {
        List result = entityManager.createQuery("select raports from RaportEntity raports")
                .getResultList();
        return result.isEmpty() ? null : result;
    }

    @Override
    public List<RaportOrderEntity> getRaportOrders(Integer userId) {
        List result = entityManager.createQuery("select raportOrder " +
                "from RaportOrderEntity raportOrder,RaportEntity raport  " +
                "where raport.user.id=:userId " +
                "AND raport.id=raportOrder.id")
                .setParameter("userId", userId)
                .getResultList();
        return result.isEmpty() ? null : result;
    }

    @Override
    public boolean deleteRaportOrder(Integer raportOrderId) {
        RaportOrderEntity raportOrder = entityManager.find(RaportOrderEntity.class, raportOrderId);
        if (raportOrder == null) {
            return false;
        } else {
            entityManager.remove(raportOrder);
            return true;
        }
    }

    @Override
    public boolean updateRaportStatus(Integer raportId, RaportStatus newRaportStatus) {
        RaportEntity raport = entityManager.find(RaportEntity.class, raportId);
        if (raport == null) {
            return false;
        } else {
            raport.setStatus(newRaportStatus);
            entityManager.refresh(raport);
            return true;
        }
    }

    @Override
    public boolean deleteRaport(Integer rapordId) {
        RaportEntity raport = entityManager.find(RaportEntity.class, rapordId);
        if (raport == null) {
            return false;
        } else {
            entityManager.remove(raport);
            return true;
        }
    }

    @Override
    public boolean add(RaportEntity raport) {
        if (raport == null) {
            return false;
        } else {
            entityManager.persist(raport);
            return true;
        }
    }

    @Override
    public boolean addOrder(RaportOrderEntity raportOrder) {
        if (raportOrder == null) {
            return false;
        } else {
            entityManager.persist(raportOrder);
            return true;
        }
    }
}
