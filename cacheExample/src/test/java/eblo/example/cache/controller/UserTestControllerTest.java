package eblo.example.cache.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import eblo.example.cache.domain.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestControllerTest {

    private User pUser;
    
    @Before
    public void setUp() {
        this.pUser = new User("test", "테스터", "web");
    }
    
    @SuppressWarnings("unused")
    private static <T extends Serializable> T clone(final T object) { 
        return SerializationUtils.clone(object);
    }

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    private User getUser(MockHttpServletRequestBuilder req) throws Exception {
        // 값을 가져와서 처리하기 
        MvcResult result = this.mockMvc.perform(req)
        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.userId").value("test"))
//        .andDo(print())
        .andReturn();
        
        // expected : {"userId":"test","userPwd":null,"name":null,"authType":null,"timestamp":"2019-02-22T07:24:33.784+0000","isUpdate":false}
        String jsonInString = result.getResponse().getContentAsString();
        // Json String을 Map에 담기 
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonInString, User.class);
    }

    @Test
    public void getUserTest() throws Exception {
        User param = clone(pUser);
        MockHttpServletRequestBuilder req = get("/users/"+param.getUserId());
        User rUser1 = getUser(req);
        Date ts1 = rUser1.getTimestamp();

        User rUser2 = getUser(req);
        Date ts2 = rUser2.getTimestamp();
        
        assertTrue(ts1.compareTo(ts2) == 0);
    }

    @Test
    public void getUserUpdateTest() throws Exception {
        User param = clone(pUser);
        MockHttpServletRequestBuilder req = get("/users/"+param.getUserId());
        User rUser1 = getUser(req);
        Date ts1 = rUser1.getTimestamp();
        
        req.param("isUpdate", "true");
        User rUser2 = getUser(req);
        Date ts2 = rUser2.getTimestamp();
        // 두개의 결과가 달라야만 한다.  
        assertTrue(ts1.compareTo(ts2) != 0);
    }
    
    @Test
    public void addUserTest() throws Exception {
        User param = clone(pUser);
        param.setUserId("1234");
        MockHttpServletRequestBuilder postReq = post("/users");
        postReq.param("userId", param.getUserId());
        postReq.param("name", param.getName());
        postReq.param("authType", param.getAuthType());
        
        User rUser1 = getUser(postReq);
        Date ts1 = rUser1.getTimestamp();
        
        MockHttpServletRequestBuilder req = get("/users/"+param.getUserId());
        User rUser2 = getUser(req);
        Date ts2 = rUser2.getTimestamp();
        // 두개의 결과가 달라야만 한다.  
        assertTrue(ts1.compareTo(ts2) == 0);
    }
    
}
