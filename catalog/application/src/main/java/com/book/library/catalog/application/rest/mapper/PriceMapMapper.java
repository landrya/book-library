package com.book.library.catalog.application.rest.mapper;

import com.book.library.catalog.domain.model.entity.BookFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PriceMapMapper {


    public static Map<BookFormat, Double> toDomain(Map<String, String> priceMap) {

        if (priceMap == null) {
            return null;
        }

        Map<BookFormat, Double> domainPriceMap = new EnumMap<>(BookFormat.class);

        priceMap.forEach((key, value)
                -> domainPriceMap.put(BookFormat.valueOf(key), Double.valueOf(value)));

        return domainPriceMap;

    }

    public static Map<String, String> toDto(Map<BookFormat, Double> priceMap) {

        Map<String, String> dtoPriceMap = new HashMap<>();

        priceMap.forEach((key, value)
                -> dtoPriceMap.put(key.name(), String.valueOf(value)));

        return dtoPriceMap;

    }
}
