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

    public int insertSavedProfessional(SavedProfessional savedProfessional) {
        return this.savedProfessionalsDAO.insertSavedProfessional(savedProfessional);
    }

    public SavedProfessional getLatestSavedProfessional() {
        List<SavedProfessional> list = this.savedProfessionalsDAO.getLatestSavedProfessional();
        return list.size() == 0 ? null : list.get(0);
    }

    public void updateSP(int id, int tid) {
        this.savedProfessionalsDAO.updateSP(id, tid);
        return;
    }

}
