package com.example.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    
    @GetMapping("/test")
    public User test(User pUser) {
        
        return pUser;
    }
}
