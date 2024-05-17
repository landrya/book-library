package com.book.library.catalog.domain.repository;

import com.book.library.catalog.domain.model.aggregate.Book;
import com.book.library.catalog.domain.model.command.UpdateBook;

import java.util.List;
import java.util.Optional;

public interface BooksCatalogRepository {

    void add(Book book);

    void remove(String id);

    Optional<Book> getBook(String isbn);

    List<Book> searchByTitle(String titleQuery);

    List<Book> listAllBooks();

    void updateBook(String id, UpdateBook updateBook);
}
