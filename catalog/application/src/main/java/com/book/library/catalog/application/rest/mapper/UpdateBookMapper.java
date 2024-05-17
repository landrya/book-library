package com.book.library.catalog.application.rest.mapper;

import com.book.library.catalog.application.api.model.UpdateBookDto;
import com.book.library.catalog.domain.model.command.UpdateBook;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateBookMapper {

    public static UpdateBook toDomain(UpdateBookDto dto) {
        return new UpdateBook(
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


