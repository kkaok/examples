package eblo.cache.test.web.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    private int age;
    private Timestamp timestamp;
    
    public User(String id, int age) {
        this.id = id;
        this.age = age;
        this.timestamp = new Timestamp(new Date().getTime());
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
