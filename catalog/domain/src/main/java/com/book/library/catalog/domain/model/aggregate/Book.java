package com.book.library.catalog.domain.model.aggregate;

import com.book.library.catalog.domain.model.entity.BookFormat;
import com.book.library.catalog.domain.model.valueobect.BookImage;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder(toBuilder = true)
public record Book(
        String id,
        String title,
        List<String> authors,
        String isbn,
        String description,
        BookImage image,
        List<String> tags,
        Map<BookFormat, Double> prices
) {
    public Book {
        tags = List.of();
        prices = Map.of();
    }
}
