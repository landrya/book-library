package com.book.library.catalog.application.rest.mapper;

import com.book.library.catalog.application.api.model.AddBookToCatalogDto;
import com.book.library.catalog.domain.model.aggregate.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddBookToCatalogDtoMapper {

    public static Book toDomain(AddBookToCatalogDto dto) {
        return new Book(
                null,
                dto.getTitle(),
                dto.getAuthors(),
                dto.getIsbn(),
                dto.getDescription(),
                BookImageMapper.toDomain(dto.getImage()),
                dto.getTags(),
                PriceMapMapper.toDomain(dto.getPrices())
        );
    }

}
