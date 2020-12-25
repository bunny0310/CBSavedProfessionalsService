package com.cb.savedProfessionalsService.database.models;

import java.sql.Timestamp;

public class SavedProfessional extends BaseEntity {
    private String professionalName;
    private String professionalCompany;
    private String professionalJobTitle;
    private String professionalEmail;
    private String templateAlias;
    private int userId;
    private int professionalId;
    private int count;
    private int templateId;
    private Timestamp lastEmailed;
    private int lastCopy;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getLastCopy() {
        return lastCopy;
    }

    public void setLastCopy(int lastCopy) {
        this.lastCopy = lastCopy;
    }

    public Timestamp getLastEmailedlastEmailed() {
        return lastEmailed;
    }

    public void setLastEmailed(Timestamp lastEmailed) {
        this.lastEmailed = lastEmailed;
    }

    public int getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(int professionalId) {
        this.professionalId = professionalId;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getProfessionalCompany() {
        return professionalCompany;
    }

    public void setProfessionalCompany(String professionalCompany) {
        this.professionalCompany = professionalCompany;
    }

    public String getProfessionalJobTitle() {
        return professionalJobTitle;
    }

    public void setProfessionalJobTitle(String professionalJobTitle) {
        this.professionalJobTitle = professionalJobTitle;
    }

    public String getProfessionalEmail() {
        return professionalEmail;
    }

    public void setProfessionalEmail(String professionalEmail) {
        this.professionalEmail = professionalEmail;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SavedProfessional() {

    }

    public SavedProfessional(int id, Timestamp updatedAt, String professionalName, String professionalCompany, String professionalJobTitle, String professionalEmail, int count, int professionalId, int userId, Timestamp lastEmailed, int lastCopy) {
        super(id, updatedAt);
        this.professionalName = professionalName;
        this.professionalCompany = professionalCompany;
        this.professionalJobTitle = professionalJobTitle;
        this.professionalEmail = professionalEmail;
        this.count = count;
        this.professionalId = professionalId;
        this.userId = userId;
        this.lastCopy = lastCopy;
        this.lastEmailed = lastEmailed;
    }
}
