package com.book.library.catalog.application;

import com.book.library.catalog.domain.repository.BooksCatalogRepository;
import com.book.library.catalog.domain.service.BookManagementService;
import com.book.library.catalog.domain.service.DefaultIdGenerator;
import com.book.library.catalog.domain.service.IdGenerator;
import com.book.library.infrastructure.adapter.BooksCatalogInMemoryAdapter;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public IdGenerator idGenerator() {
        return new DefaultIdGenerator();
    }

    @Bean
    public BookManagementService bookManagementService(BooksCatalogRepository booksCatalog, IdGenerator idGenerator) {
        return new BookManagementService(booksCatalog, idGenerator);
    }

    @Bean
    public BooksCatalogRepository booksCatalogInMemoryAdapter() {
        return new BooksCatalogInMemoryAdapter();
    }

}
