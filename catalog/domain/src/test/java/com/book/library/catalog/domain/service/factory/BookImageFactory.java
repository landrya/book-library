package com.book.library.catalog.domain.service.factory;

import com.book.library.catalog.domain.model.valueobect.BookImage;

import java.util.UUID;

public class BookImageFactory {

    private String frontUrl;
    private String backUrl;

    public static BookImageFactory initialize() {
        BookImageFactory factory = new BookImageFactory();
        factory.frontUrl = UUID.randomUUID().toString();
        factory.backUrl = UUID.randomUUID().toString();
        return factory;
    }

    public BookImage build() {
        return new BookImage(frontUrl, backUrl);
    }

}
