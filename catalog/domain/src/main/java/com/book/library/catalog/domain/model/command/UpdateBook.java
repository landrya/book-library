package com.book.library.catalog.domain.model.command;

import com.book.library.catalog.domain.model.entity.BookFormat;
import com.book.library.catalog.domain.model.valueobect.BookImage;

import java.util.List;
import java.util.Map;

public record UpdateBook(String title,
                         List<String> authors,
                         String isbn,
                         String description,
                         BookImage image,
                         List<String> tags,
                         Map<BookFormat, Double> prices) {
}
