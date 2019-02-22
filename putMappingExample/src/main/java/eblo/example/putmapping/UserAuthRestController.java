package eblo.example.putmapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthRestController { 

    @PutMapping("/api/users/{userId}")
    public User modifyUser(User user) {
        return user;
    }

}

