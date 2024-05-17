package com.book.library.catalog.domain.service;

import com.book.library.catalog.domain.model.aggregate.Book;
import com.book.library.catalog.domain.model.command.UpdateBook;
import com.book.library.catalog.domain.repository.BooksCatalogRepository;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookManagementService {

    private final BooksCatalogRepository booksCatalog;
    private final IdGenerator idGenerator;


    public String addBookToCatalog(Book book) {
        String id = idGenerator.generateId();
        booksCatalog.add(book.toBuilder()
                .id(id)
                .build());
        return id;
    }

    public void removeBookFromCatalog(String id) {
        booksCatalog.remove(id);
    }

    public List<Book> searchBookByTitle(String titleQuery) {
        return booksCatalog.searchByTitle(titleQuery);
    }

    public List<Book> listAllBooks() {
        return booksCatalog.listAllBooks().stream()
                .sorted(Comparator.comparing(Book::title))
                .toList();
    }

    public Optional<Book> getBookFromCatalog(String isbn) {
        return booksCatalog.getBook(isbn);
    }

    public void updateBook(String id, UpdateBook updateBook) {
        booksCatalog.updateBook(id, updateBook);
    }
}
