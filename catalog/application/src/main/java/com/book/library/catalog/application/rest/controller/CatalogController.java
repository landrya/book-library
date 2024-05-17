package com.book.library.catalog.application.rest.controller;

import com.book.library.catalog.application.api.CatalogApi;
import com.book.library.catalog.application.api.model.AddBookToCatalogDto;
import com.book.library.catalog.application.api.model.CatalogBookDetailsDto;
import com.book.library.catalog.application.api.model.CatalogBookListDto;
import com.book.library.catalog.application.api.model.IdDto;
import com.book.library.catalog.application.api.model.UpdateBookDto;
import com.book.library.catalog.application.rest.mapper.AddBookToCatalogDtoMapper;
import com.book.library.catalog.application.rest.mapper.BookSummaryMapper;
import com.book.library.catalog.application.rest.mapper.CatalogBookDetailsMapper;
import com.book.library.catalog.application.rest.mapper.UpdateBookMapper;
import com.book.library.catalog.domain.model.aggregate.Book;
import com.book.library.catalog.domain.service.BookManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CatalogController implements CatalogApi {

    private final BookManagementService bookManagementService;

    @Override
    public IdDto addBook(AddBookToCatalogDto addBookToCatalogDto) {
        String id = bookManagementService.addBookToCatalog(AddBookToCatalogDtoMapper.toDomain(addBookToCatalogDto));
        return new IdDto(id);
    }

    @Override
    public CatalogBookDetailsDto getBookById(String id) {
        Optional<Book> bookFromCatalog = bookManagementService.getBookFromCatalog(id);
        return bookFromCatalog.map(CatalogBookDetailsMapper::toDto).orElse(null);
    }

    @Override
    public CatalogBookListDto listBooks() {
        return new CatalogBookListDto(
                bookManagementService.listAllBooks().stream().map(BookSummaryMapper::toDto).toList()
        );
    }

    @Override
    public void removeBook(String id) {
        bookManagementService.removeBookFromCatalog(id);
    }

    @Override
    public void updateBook(String id, UpdateBookDto updateBookDto) {
        bookManagementService.updateBook(id, UpdateBookMapper.toDomain(updateBookDto));
    }
}
