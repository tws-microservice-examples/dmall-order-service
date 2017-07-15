package com.dmall.order.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lianghuang on 04/04/2017.
 */
public class CustomDateToStringSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        String dateString = dateFormat.format(value);
        jgen.writeString(dateString);
    }
}
