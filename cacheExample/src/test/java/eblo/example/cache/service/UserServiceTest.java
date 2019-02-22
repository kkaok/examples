package eblo.example.cache.service;

import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eblo.example.cache.domain.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    
    private User pUser;
    
    @Before
    public void setUp() {
        this.pUser = new User("test", "테스터", "web");
    }
    
    @SuppressWarnings("unused")
    private static <T extends Serializable> T clone(final T object) { 
        return SerializationUtils.clone(object);
    }

    @Test
    public void getUser() {
        User param = clone(pUser);
        User rUser1 = userService.getUser(param);
        Date ts1 = rUser1.getTimestamp();

        User rUser2 = userService.getUser(param);
        Date ts2 = rUser2.getTimestamp();
        
        assertTrue(ts1.compareTo(ts2) == 0);
    }

    @Test
    public void getUserUpdate() {
        User param = clone(pUser);
        User rUser1 = userService.getUser(param);
        Date ts1 = rUser1.getTimestamp();

        // update 조건을 주면 캐시를 타지 않는다. 
        param.setIsUpdate(true);
        User rUser2 = userService.getUser(param);
        Date ts2 = rUser2.getTimestamp();
        // 두개의 결과가 달라야만 한다.  
        assertTrue(ts1.compareTo(ts2) != 0);
    }

    @Test
    public void addUser() {
        User param = clone(pUser);
        param.setUserId("1234");
        // 사용자 추가 
        User rUser1 = userService.addUser(param);
        Date ts1 = rUser1.getTimestamp();

        // 사용자 조회 
        User rUser2 = userService.getUser(param);
        Date ts2 = rUser2.getTimestamp();
        
        assertTrue(ts1.compareTo(ts2) == 0);
    }

    @Test
    public void modifyUser() {
        User param = clone(pUser);
        // 사용자 조회 
        User rUser1 = userService.getUser(param);
        Date ts1 = rUser1.getTimestamp();
        
        // 사용자 수정 
        param.setAuthType("facebook");
        User rUser2 = userService.modifyUser(param);
        Date ts2 = rUser2.getTimestamp();
        
        assertTrue(ts1.compareTo(ts2) != 0);
    }

    @Test
    public void deleteUser() {
        User param = clone(pUser);
        // 사용자 조회 
        User rUser1 = userService.getUser(param);
        Date ts1 = rUser1.getTimestamp();
        
        // 사용자 삭제 
        userService.removeUser(param);

        // 사용자 조회 
        User rUser2 = userService.getUser(param);
        Date ts2 = rUser2.getTimestamp();

        assertTrue(ts1.compareTo(ts2) != 0);
    }
    

}
