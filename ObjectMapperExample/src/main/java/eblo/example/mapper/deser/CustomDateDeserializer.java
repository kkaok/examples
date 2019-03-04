package eblo.example.mapper.deser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date>{

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public CustomDateDeserializer() {
        super();
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String valueAsString = jsonParser.getValueAsString();
        if (StringUtils.isEmpty(valueAsString)) {
            return null;
        }
        try {
            SimpleDateFormat transFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            //transFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return transFormat.parse(valueAsString);
        }catch(Exception e) {
            return null;
        }
    }

}
