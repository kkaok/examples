package eblo.example.mapper.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    private String userId;
    private String userPwd;
    private String name;
    private String authType;
    private Timestamp timestamp;
    private Date date;
    private boolean isUpdate = false;
    
    public User() {
        super();
    }
    
    public User(String userId, String name, String authType) {
        super();
        this.userId = userId;
        this.name = name;
        this.authType = authType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userPwd=" + userPwd + ", name=" + name + ", authType=" + authType
                + ", timestamp=" + timestamp + ", date=" + date + ", isUpdate=" + isUpdate + "]";
    }
    
}
