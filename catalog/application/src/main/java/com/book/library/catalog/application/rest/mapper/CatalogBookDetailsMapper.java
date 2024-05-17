package com.book.library.catalog.application.rest.mapper;


import com.book.library.catalog.application.api.model.CatalogBookDetailsDto;
import com.book.library.catalog.domain.model.aggregate.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CatalogBookDetailsMapper {

    public static CatalogBookDetailsDto toDto(Book book) {
        return new CatalogBookDetailsDto(
                book.title(),
                book.isbn(),
                book.authors(),
                book.description(),
                BookImageMapper.toDto(book.image()),
                book.tags(),
                PriceMapMapper.toDto(book.prices())
        );
    }
}
