package com.practice.design.AdvertisementEngine.exception;

public class EntityNotFoundException extends AdvertisementEngineException {
    public EntityNotFoundException(String entityType, String entityId) {
        super(String.format("%s with ID '%s' not found", entityType, entityId));
    }
}

