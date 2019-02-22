package eblo.example.cache.service;

import java.util.Date;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import eblo.example.cache.domain.User;

@Service
public class UserService {

    @Cacheable(cacheNames="userCache", key="#user.userId", condition = "#user.isUpdate != true")
    public User getUser(User user) {
        user.setTimestamp(new Date());
        return user;
    }

    @CachePut(cacheNames="userCache", key="#user.userId")
    public User addUser(User user) {
        user.setTimestamp(new Date());
        return user;
    }

    @CachePut(cacheNames="userCache", key="#user.userId")
    public User modifyUser(User user) {
        user.setTimestamp(new Date());
        return user;
    }

    @CacheEvict(cacheNames="userCache", key="#user.userId")
    public void removeUser(User user) {
        user.setTimestamp(new Date());
    }
}
