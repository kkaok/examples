package eblo.example.mapper.deser;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomTimestampDeserializer extends JsonDeserializer<Timestamp>{

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public CustomTimestampDeserializer() {
        super();
    }

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String valueAsString = jsonParser.getValueAsString();
        if (StringUtils.isEmpty(valueAsString)) {
            return null;
        }
        try {
            SimpleDateFormat transFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            //transFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return new Timestamp(transFormat.parse(valueAsString).getTime());
        }catch(Exception e) {
            return null;
        }
    }

}
