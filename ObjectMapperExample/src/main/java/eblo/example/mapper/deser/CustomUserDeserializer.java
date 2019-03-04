package eblo.example.mapper.deser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import eblo.example.mapper.domain.User;

public class CustomUserDeserializer extends StdDeserializer<User>{

    private static final long serialVersionUID = 1L;

    public CustomUserDeserializer() {
        this(null);
    }

    public CustomUserDeserializer(Class<?> user) {
        super(user);
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        User user = new User();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        JsonNode userNameNode = node.get("name");
        JsonNode userIdNode = node.get("userId");
        JsonNode authTypeNode = node.get("authType");
        String userName = userNameNode.asText();
        String userid = userIdNode.asText();
        String authType = authTypeNode.asText();
        user.setUserId(userid);
        user.setName(userName);
        user.setAuthType(authType);
        return user;
    }

}
