package eblo.example.cache.service;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import eblo.example.cache.domain.User;

@Service
public class UserService {

    @Cacheable(cacheNames="userCache")
    public User getUser(final User user) {
        return cloneUser(user);
    }

    @Cacheable(cacheNames="userCache", key="#user.userId", condition = "#user.isUpdate != true")
    public User __getUser(User user) {
        return cloneUser(user);
    }

    @CachePut(cacheNames="userCache", key="#user.userId")
    public User addUser(User user) {
        return cloneUser(user);
    }

    @CachePut(cacheNames="userCache", key="#user.userId")
    public User modifyUser(User user) {
        return cloneUser(user);
    }

    @CacheEvict(cacheNames="userCache", key="#user.userId")
    public void removeUser(User user) {
        cloneUser(user);
    }
    
    private User cloneUser(User user) {
        User rUser = SerializationUtils.clone(user);
        rUser.setTimestamp(new Date());
        return rUser;
    }
}
