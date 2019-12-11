package com.entertainment.userservice.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
    private static final SimpleDateFormat firstDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat secondDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken().equals(JsonToken.VALUE_STRING)) {
            try {
                Date date = firstDateFormat.parse(jsonParser.getText());
                return date;
            } catch (ParseException e) {
                try {
                    return secondDateFormat.parse(jsonParser.getText());
                } catch (ParseException ignored) { }
            }
        }
        return null;
    }
}
