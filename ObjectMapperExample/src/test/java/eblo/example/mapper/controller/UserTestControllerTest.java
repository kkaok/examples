package eblo.example.mapper.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import eblo.example.mapper.CustomObjectMapper;
import eblo.example.mapper.deser.CustomUserDeserializer;
import eblo.example.mapper.deser.CustomUserSerializer;
import eblo.example.mapper.domain.User;


public class UserTestControllerTest {

    final String expected1 = "{\"userId\":\"userId1\",\"userPwd\":null,\"name\":\"tester1\",\"authType\":\"web\",\"timestamp\":null,\"isUpdate\":false,\"authGroup\":\"admin\" }";
    final String expected2 = "{\"userId\":\"userId2\",\"userPwd\":null,\"name\":\"tester2\",\"authType\":\"web\",\"timestamp\":null,\"isUpdate\":false,\"authGroup\":\"user\" }";
    final String expected3 = "{\"userId\":\"userId3\",\"userPwd\":null,\"name\":\"tester2\",\"authType\":\"web\",\"timestamp\":\"2019-03-04 13:59:59\",\"date\":\"2019-03-04\",\"isUpdate\":false,\"authGroup\":\"user\" }";
    final String listExpected = "["+expected1+","+expected2+"]";

    @Test
    public void objectToJson() throws JsonProcessingException {
        User user = new User("userId1", "tester1", "web");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 없는 필드로 인한 오류 무시
        String userAsString = objectMapper.writeValueAsString(user);
        assertThat(userAsString, containsString("userId1"));
    }

    @Test
    public void customObjectToJson() throws JsonProcessingException {
        User user = new User("userId1", "tester1", "web");

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(User.class, new CustomUserSerializer());
        objectMapper.registerModule(simpleModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 없는 필드로 인한 오류 무시
        String userAsString = objectMapper.writeValueAsString(user);
        assertThat(userAsString, equalTo("{\"userId\":\"userId1\",\"userName\":\"tester1\",\"authType\":\"web\"}"));
    }

    @Test
    public void customObjectMapper() throws IOException {
        ObjectMapper objectMapper = new CustomObjectMapper();
        User user = objectMapper.readValue(expected3, User.class);
        assertThat(user.getTimestamp().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), equalTo("2019-03-04 13:59:59"));
    }

    @Test
    public void jsonToObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 없는 필드로 인한 오류 무시
        User user = objectMapper.readValue(expected1, User.class);
        assertThat(user.getUserId(), equalTo("userId1"));
    }

    @Test
    public void customJsonToObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(User.class, new CustomUserDeserializer());
        objectMapper.registerModule(simpleModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 없는 필드로 인한 오류 무시
        User user = objectMapper.readValue(expected1, User.class);
        assertThat(user.getUserId(), equalTo("userId1"));
    }

    @Test
    public void jsonToMap() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 없는 필드로 인한 오류 무시
        Map<String, Object> map = objectMapper.readValue(expected1, new TypeReference<Map<String,Object>>(){});
        assertThat(map.get("userId"), equalTo("userId1"));
    }

    @Test
    public void jsonToListObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 없는 필드로 인한 오류 무시
        List<User> users = objectMapper.readValue(listExpected, new TypeReference<List<User>>(){});
        for(User user : users) {
            //System.out.println(user.toString());
            assertThat(user.getName(), containsString("tester"));
        }
    }

}
