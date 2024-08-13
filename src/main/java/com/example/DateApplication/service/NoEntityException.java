package com.example.DateApplication.service;

public class NoEntityException extends RuntimeException {
    private final int entityId;

    public NoEntityException(int entityId) {
        super("Entity with id " + entityId + " not found");
        this.entityId = entityId;
    }

    public int getEntityId() {
        return entityId;
    }
}
