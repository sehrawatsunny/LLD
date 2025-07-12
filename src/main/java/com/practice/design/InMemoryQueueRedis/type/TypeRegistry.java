package com.practice.design.InMemoryQueueRedis.type;

import java.util.concurrent.ConcurrentHashMap;

// Singleton class.
// Store the type of each attribute key.
@SuppressWarnings("LombokGetterMayBeUsed")
public class TypeRegistry {
    private static final TypeRegistry instance = new TypeRegistry();
    // Concurrent hashmap to store attribute key and the type of value , as
    // this hashmap will be accessed by multiple threads.
    ConcurrentHashMap<String, Class<?>> typeMap = new ConcurrentHashMap<>();

    private TypeRegistry() {
    }

    public static TypeRegistry getInstance() {
        return instance;
    }

    public Class<?> getType(String attributeKey) {
        return typeMap.get(attributeKey);
    }

    public void register(String attributeKey, Object value) {
        if (typeMap.containsKey(attributeKey)) {
            Class<?> valueType = value.getClass();
            Class<?> registeredType = typeMap.get(attributeKey);
            if (!registeredType.equals(valueType)) {
                throw new TypeMismatchException("Attribute '" + attributeKey + "' expected type "
                        + registeredType.getSimpleName() + " but got " + valueType.getSimpleName());
            }
        } else {
            typeMap.put(attributeKey, value.getClass());
        }
    }

}


