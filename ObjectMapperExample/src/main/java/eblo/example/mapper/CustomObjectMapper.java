package eblo.example.mapper;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import eblo.example.mapper.deser.CustomDateDeserializer;
import eblo.example.mapper.deser.CustomTimestampDeserializer;

public class CustomObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;

    public CustomObjectMapper(){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Timestamp.class, new CustomTimestampDeserializer());
        simpleModule.addDeserializer(Date.class, new CustomDateDeserializer());
//        simpleModule.addDeserializer(Long.class, new LongDeserializer());
//        simpleModule.addDeserializer(Integer.class, new IntegerDeserializer());
//        simpleModule.addDeserializer(Float.class, new FloatDeserializer());
//        simpleModule.addDeserializer(Double.class, new DoubleDeserializer());
        registerModule(simpleModule);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 없는 필드로 인한 오류 무시
    }
}
