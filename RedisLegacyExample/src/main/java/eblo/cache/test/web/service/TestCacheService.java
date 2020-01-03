package eblo.cache.test.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import eblo.cache.test.web.domain.User;

@Service
public class TestCacheService {

    @Cacheable(cacheNames="userCache")
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("고객1", 21));
        users.add(new User("고객2", 22));
        users.add(new User("고객3", 23));
        return users;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames="userCache", allEntries = true)
        })
    public void removeCacheUsers(){
    }
    
}
