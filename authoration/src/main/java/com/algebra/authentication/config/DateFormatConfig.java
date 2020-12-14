package com.algebra.authentication.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author al
 * @date 2020/12/14 8:59
 * @description
 */
@JsonComponent
public class DateFormatConfig {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
        @Override
        public Date parse(String source) throws ParseException {
            try {
                if(StringUtils.isEmpty(source)){
                    return null;
                }
                return super.parse(source);
            } catch (Exception e) {
                return new StdDateFormat().parse(source);
            }
        }
    };

    public static class DateJsonSerializer extends JsonSerializer<Date>{
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            dateFormat.setTimeZone(TimeZone.getTimeZone(""));
            jsonGenerator.writeString(dateFormat.format(date));
        }
    }

    public static class DateJsonDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            try {
                dateFormat.setTimeZone(TimeZone.getTimeZone(""));
                return dateFormat.parse(jsonParser.getText());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
