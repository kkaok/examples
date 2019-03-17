package eblo.example.cache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eblo.example.cache.domain.User;
import eblo.example.cache.service.UserService;

@RestController
public class UserTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired 
    private UserService userService; 
    
    @GetMapping("/users/{userId}")
    public User getUser(User pUser) {
        return userService.getUser(pUser);
    }

    @PostMapping("/users")
    public User addUser(User pUser) {
        userService.addUser(pUser);
        return pUser;
    }

    @PutMapping("/users/{userId}")
    public String modifyUser(@RequestBody String jsonStr, User pUser) {
        log.debug(jsonStr);
        //userService.modifyUser(pUser);
        return jsonStr;
    }

    @DeleteMapping("/users/{userId}")
    public User removeUser(User pUser) {
        userService.removeUser(pUser);
        return pUser;
    }
    
}
