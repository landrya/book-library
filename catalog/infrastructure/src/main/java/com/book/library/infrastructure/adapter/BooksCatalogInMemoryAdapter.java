package com.book.library.infrastructure.adapter;


import com.book.library.catalog.domain.model.aggregate.Book;
import com.book.library.catalog.domain.model.command.UpdateBook;
import com.book.library.catalog.domain.repository.BooksCatalogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BooksCatalogInMemoryAdapter implements BooksCatalogRepository {

    private final ConcurrentHashMap<String, Book> catalog = new ConcurrentHashMap<>();


    @Override
    public void add(Book book) {
        catalog.put(book.id(), book);
    }

    @Override
    public void remove(String id) {
        catalog.remove(id);
    }

    @Override
    public Optional<Book> getBook(String id) {
        return Optional.ofNullable(catalog.get(id));
    }

    @Override
    public List<Book> searchByTitle(String titleQuery) {
        return catalog.values().stream()
                .filter(book -> book.title().contains(titleQuery))
                .toList();
    }

    @Override
    public List<Book> listAllBooks() {
        return new ArrayList<>(catalog.values());
    }

    public void removeAllBooks() {
        catalog.clear();
    }

    @Override
    public void updateBook(String id, UpdateBook updateBook) {

        Book.BookBuilder builder = catalog.get(id).toBuilder();

        if (updateBook.title() != null) {
            builder.title(updateBook.title());
        }

        if (updateBook.description() != null) {
            builder.description(updateBook.description());
        }

        if (updateBook.authors() != null) {
            builder.authors(updateBook.authors());
        }

        if (updateBook.isbn() != null) {
            builder.isbn(updateBook.isbn());
        }

        if (updateBook.tags() != null) {
            builder.tags(updateBook.tags());
        }

        if (updateBook.image() != null) {
            builder.image(updateBook.image());
        }

        if (updateBook.prices() != null) {
            builder.prices(updateBook.prices());
        }

        catalog.put(id, builder.build());
    }
}
