package com.cb.savedProfessionalsService.database.models;

import java.sql.Timestamp;

public class BaseEntity {
    private int id;
    private Timestamp updatedAt;

    public BaseEntity() {

    }

    public BaseEntity(int id, Timestamp updatedAt) {
        this.id = id;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
