package com.book.library.catalog.application.rest.mapper;

import com.book.library.catalog.application.api.model.CatalogBookSummaryDto;
import com.book.library.catalog.domain.model.aggregate.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookSummaryMapper {

    public static CatalogBookSummaryDto toDto(Book book) {
        return new CatalogBookSummaryDto(book.isbn(), book.title(), book.authors());
    }
}
