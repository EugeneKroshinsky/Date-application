package com.example.DateApplication.dto.exceptions;

import lombok.Getter;

@Getter
public class NoEntityException extends RuntimeException {
    private final int entityId;

    public NoEntityException(int entityId) {
        super("Entity with id " + entityId + " not found");
        this.entityId = entityId;
    }
}
