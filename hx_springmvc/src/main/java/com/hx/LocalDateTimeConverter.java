package com.hx;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by xing on 2016/6/19.
 */
public class LocalDateTimeConverter  implements Converter<String, LocalDateTime> {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.from(format.parse(source));
    }
}
