package eblo.example.mapper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eblo.example.mapper.domain.User;

@RestController
public class UserTestController {

    @GetMapping("/users/{userId}")
    public User getUser(User pUser) {
        return pUser;
    }
    
}
