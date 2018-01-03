package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.LocalizationEntity;
import com.brotherhood.stocktaking.repositories.LocalizationRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizationService {
    private LocalizationRepository localizationRepository;

    @Autowired
    public LocalizationService(LocalizationRepositoryImpl localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    public LocalizationEntity get(Integer localizationId) {
        return localizationRepository.get(localizationId);
    }

    public List<LocalizationEntity> getPage(int page) {
        return localizationRepository.getPage(page);
    }

    public boolean add(String roomName) {
        return localizationRepository.add(roomName);
    }

    public boolean delete(String roomName) {
        return localizationRepository.delete(roomName);
    }

    public List get() {
        return localizationRepository.get();
    }

    public LocalizationEntity get(String roomName) {
        return localizationRepository.get(roomName);
    }
}
