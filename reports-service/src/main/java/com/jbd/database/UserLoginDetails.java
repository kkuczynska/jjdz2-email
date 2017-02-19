package com.jbd.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USRLOGIN")
public class UserLoginDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    private String loginDateTime;

    public UserLoginDetails(String userEmail, String loginDate, String loginTime) {
        this.userEmail = userEmail;
        this.loginDateTime = loginDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(String loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

}
