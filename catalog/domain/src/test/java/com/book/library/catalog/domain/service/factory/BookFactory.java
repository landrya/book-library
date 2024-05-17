package com.book.library.catalog.domain.service.factory;

import com.book.library.catalog.domain.model.aggregate.Book;
import com.book.library.catalog.domain.model.valueobect.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BookFactory {

    private String id;
    private String title;
    private List<String> authors;
    private String isbn;

    public static BookFactory initialize() {
        BookFactory bookFactory = new BookFactory();
        bookFactory.id = UUID.randomUUID().toString();
        bookFactory.title = UUID.randomUUID().toString();
        bookFactory.authors = List.of(Constants.DEFAULT_AUTHOR);
        bookFactory.isbn = UUID.randomUUID().toString();
        return bookFactory;
    }

    public Book build() {
        return new Book(id, title, authors, isbn, null, null, new ArrayList<>(), new HashMap<>());
    }

    public BookFactory withId(String id) {
        this.id = id;
        return this;
    }

    public BookFactory withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookFactory withAuthors(List<String> authors) {
        this.authors = authors;
        return this;
    }
}
