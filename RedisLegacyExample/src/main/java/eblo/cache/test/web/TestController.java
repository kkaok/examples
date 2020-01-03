package eblo.cache.test.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eblo.cache.test.web.domain.User;
import eblo.cache.test.web.service.TestCacheService;

@RestController
public class TestController {

    @Autowired
    private TestCacheService testCacheService;
    
    @RequestMapping("/test")
    public List<User> getUsers() {//Welcome page, non-rest
        return testCacheService.getUsers();
    }

    @RequestMapping("/test-refresh")
    public List<User> refreshUsers() {//Welcome page, non-rest
        testCacheService.removeCacheUsers();
        return testCacheService.getUsers();
    }
}
