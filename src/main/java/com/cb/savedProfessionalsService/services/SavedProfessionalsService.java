package com.cb.savedProfessionalsService.services;

import com.cb.savedProfessionalsService.database.DAO.SavedProfessionalsDAO;
import com.cb.savedProfessionalsService.database.models.SavedProfessional;

import java.util.List;

public class SavedProfessionalsService {
    private SavedProfessionalsDAO savedProfessionalsDAO;
    public SavedProfessionalsService(SavedProfessionalsDAO savedProfessionalsDAO) {
        this.savedProfessionalsDAO = savedProfessionalsDAO;
    }
    public List<SavedProfessional> getSavedProfessionals(int userId) {
        return this.savedProfessionalsDAO.getSavedProfessionals(userId);
    }
}
