package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.*;
import com.brotherhood.stocktaking.models.requests.CreateRaportOrderRequest;
import com.brotherhood.stocktaking.models.requests.UpdateRaportRequest;
import com.brotherhood.stocktaking.repositories.interfaces.LocalizationRepository;
import com.brotherhood.stocktaking.repositories.interfaces.RaportRepository;
import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RaportRepositoryImpl extends AbstractRepository implements RaportRepository {
    private UserRepository userRepository;
    private LocalizationRepository localizationRepository;

    @Autowired
    public RaportRepositoryImpl(UserRepositoryImpl userRepository, LocalizationRepositoryImpl localizationRepository) {
        this.userRepository = userRepository;
        this.localizationRepository = localizationRepository;
    }

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
    public RaportEntity getRaport(Integer raportId) {
        return entityManager.find(RaportEntity.class, raportId);
    }

    @Override
    public RaportOrderEntity getRaportOrder(Integer raportOrderId) {
        return entityManager.find(RaportOrderEntity.class, raportOrderId);
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
    public boolean updateRaportStatus(UpdateRaportRequest request) {
        RaportEntity raport = entityManager.find(RaportEntity.class, request.getRaportId());
        if (raport == null) {
            return false;
        } else {
            raport.setStatus(request.getRaportStatus());
            raport.setUrl(request.getUrl());
            entityManager.merge(raport);

            if (request.getRaportStatus() == RaportStatus.FINISHED) {
                if (request.getUrl() == null) {
                    return false;
                }
                deleteRaportOrder(raport.getRaportOrderId().getRaportOrderId());
            }
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
    public boolean addOrder(CreateRaportOrderRequest request) {
        if (request.getUsersIds().size() == 0 || request.getRaportOwnerNick() == null) {
            return false;
        }
        UserEntity user = userRepository.get(request.getRaportOwnerNick());
        RaportEntity raport = new RaportEntity();
        raport.setStatus(RaportStatus.PREPARED_TO_EXECUTION);
        raport.setUser(user);
        entityManager.persist(raport);

        RaportOrderEntity raportOrder = new RaportOrderEntity();
        raportOrder.setUser(user);
        raportOrder.setRaportEntity(raport);
        raportOrder.setCountMin(request.getCountMin());
        raportOrder.setCountMax(request.getCountMax());
        raportOrder.setDateMin(request.getDateMin());
        raportOrder.setDateMax(request.getDateMax());
        if (request.getLocalizationsIds() != null) {
            raportOrder.setLocalizations(localizationRepository.get(request.getLocalizationsIds()));
        }
        raportOrder.setValueMin(request.getValueMin());
        raportOrder.setValueMax(request.getValueMax());
        entityManager.persist(raportOrder);
        return true;
    }

    @Override
    public boolean addItemsListForRaportOrder(Integer raportOrderId, List<Integer> itemsIds) {
        RaportOrderEntity raportOrder = getRaportOrder(raportOrderId);
        if (raportOrder == null) {
            return false;
        }
        for (Integer itemId : itemsIds) {
            addRaportOrderItem(new RaportOrderItemEntity(raportOrderId, itemId));
        }
        entityManager.refresh(raportOrder);
        return true;
    }

    @Override
    public boolean addLocalizationsListForRaportOrder(Integer raportOrderId, List<Integer> localizationsIds) {
        RaportOrderEntity raportOrder = getRaportOrder(raportOrderId);
        if (raportOrder == null) {
            return false;
        }
        for (Integer localizationId : localizationsIds) {
            addRaportOrderLocalization(new RaportOrderLocalizationEntity(raportOrderId, localizationId));
        }
        entityManager.refresh(raportOrder);
        return true;
    }

    @Override
    public void addRaportOrderItem(RaportOrderItemEntity raportOrderItemEntity) {
        entityManager.persist(raportOrderItemEntity);
    }

    @Override
    public void addRaportOrderLocalization(RaportOrderLocalizationEntity raportOrderLocalizationEntity) {
        entityManager.persist(raportOrderLocalizationEntity);
    }

}
