package com.hx;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by xing on 2016/6/19.
 */
public class LocalDateTimeSerializer extends JsonSerializer {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if(value instanceof LocalDateTime) {
            LocalDateTime localDateTime=(LocalDateTime)value;
            jgen.writeString(localDateTime.format(format));
        }
    }
}
