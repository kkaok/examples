package eblo.example.mapper.deser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import eblo.example.mapper.domain.User;

public class CustomUserSerializer extends StdSerializer<User>{

    private static final long serialVersionUID = 1L;

    public CustomUserSerializer() {
        this(null);
    }
 
    public CustomUserSerializer(Class<User> user) {
        super(user);
    }
 
    @Override
    public void serialize(
            User user, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("userId", user.getUserId());
        jsonGenerator.writeStringField("userName", user.getName());
        jsonGenerator.writeStringField("authType", user.getAuthType());
        //jsonGenerator.writeNumberField("timestamp", (new Date()).getTime());
        jsonGenerator.writeEndObject();
    }
}

