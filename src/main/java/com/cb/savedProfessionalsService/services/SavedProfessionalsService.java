package com.cb.savedProfessionalsService.services;

import com.cb.savedProfessionalsService.database.DAO.SavedProfessionalsDAO;
import com.cb.savedProfessionalsService.database.models.SavedProfessional;

import java.util.List;
import java.util.Set;

public class SavedProfessionalsService {
    private SavedProfessionalsDAO savedProfessionalsDAO;
    public SavedProfessionalsService(SavedProfessionalsDAO savedProfessionalsDAO) {
        this.savedProfessionalsDAO = savedProfessionalsDAO;
    }
    public Set<SavedProfessional> getSavedProfessionals(int userId) {
        return this.savedProfessionalsDAO.getSavedProfessionals(userId);
    }

    public void insertSavedProfessional(SavedProfessional savedProfessional) {
        this.savedProfessionalsDAO.insertSavedProfessional(savedProfessional);
    }
}
