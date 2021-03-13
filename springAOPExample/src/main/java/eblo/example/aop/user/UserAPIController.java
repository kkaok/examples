package eblo.example.aop.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eblo.example.aop.annotation.HisLogManager;
import eblo.example.aop.annotation.HisLogParam;
import eblo.example.aop.user.domain.User;
import eblo.example.log.domain.AppType;
import eblo.example.log.domain.ReqType;
import eblo.example.log.domain.TargetId;

@RestController
public class UserAPIController {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> abstractBaseException(HttpServletRequest request, HttpServletResponse response, RuntimeException exception) {
        Map<String, String> result = new HashMap<>();
        result.put("code", "400");
        result.put("message", exception.getMessage());
        return result;
    }

    @GetMapping("/users")
    @HisLogManager(targetId=TargetId.USER, appType=AppType.WEB, reqType=ReqType.READ)
    public List<User> findUsers(@HisLogParam User pUser) {
        List<User> users = new ArrayList<>();
        users.add(new User("test1", 21));
        users.add(new User("test2", 22));
        users.add(new User("test3", 23));
        users.add(new User("test4", 24));
        return users;
    }

    @GetMapping("/users/{userId}")
    @HisLogManager(targetId=TargetId.USER, appType=AppType.WEB, reqType=ReqType.DETAIL)
    public User findUserDetail(@HisLogParam User pUser) {
        pUser.setCreteDt(new Date());
        return pUser;
    }

    @PostMapping("/users")
    @HisLogManager(targetId=TargetId.USER, appType=AppType.WEB, reqType=ReqType.CREATE)
    public User createUser(@HisLogParam User pUser) {
        pUser.setCreteDt(new Date());
        return pUser;
    }

    @PutMapping("/users/{userId}")
    @HisLogManager(targetId=TargetId.USER, appType=AppType.WEB, reqType=ReqType.UPDATE)
    public User modifyUser(@RequestBody @HisLogParam User pUser) {
        pUser.setCreteDt(new Date());
        return pUser;
    }
    
    @GetMapping("/users-error")
    @HisLogManager(targetId=TargetId.USER, appType=AppType.WEB, reqType=ReqType.READ)
    public User testUserThrowing(HttpServletRequest request, @HisLogParam User pUser) {
        request.setAttribute("test", "fail");
        throw new RuntimeException("에러 발생!!!");
    }
}
