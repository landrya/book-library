package com.book.library.catalog.domain.service;

import java.util.UUID;

public class DefaultIdGenerator implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
