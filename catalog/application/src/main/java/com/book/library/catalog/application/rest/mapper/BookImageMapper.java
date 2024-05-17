package com.book.library.catalog.application.rest.mapper;

import com.book.library.catalog.application.api.model.BookImageDto;
import com.book.library.catalog.domain.model.valueobect.BookImage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookImageMapper {

    public static BookImage toDomain(BookImageDto dto) {
        if (dto == null) {
            return null;
        }
        return new BookImage(dto.getFrontUrl(), dto.getBackUrl());
    }

    public static BookImageDto toDto(BookImage domain) {
        if (domain == null) {
            return null;
        }
        return new BookImageDto(domain.frontUrl(), domain.backUrl());
    }
}
