package eblo.example.aop.user.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private String userId;

    private Integer age;

    private Date creteDt;
    
    public User() {
        super();
    }

    public User(String userId, Integer age) {
        this.userId = userId;
        this.age = age;
    }

}
