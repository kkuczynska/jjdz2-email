package com.jbd.Authorization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@SessionScoped
@Entity
@Table(name = "User")
@NamedQuery(name = "SessionData.findAll", query = "select p FROM SessionData p")
public class SessionData implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger(SessionData.class);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private boolean isLogged = false;
    private String username;
    private String usermail;
    private String privilege;
    @Transient
    private LocalDateTime loginTime;
    @Transient
    private String code = null;

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void login(String code, String username, String usermail, String privilege) {
        if(!code.equals("")){
            this.isLogged = true;
            this.username = username;
            this.usermail = usermail;
            this.code = code;
            this.loginTime = LocalDateTime.now();
            if(privilege.equals(null)){
                this.privilege = "local";
            }
            else if(privilege.equals("local")){
                this.privilege = privilege;
            }
            else
                this.privilege = privilege;
        }


    }

    public void logout(){
        this.isLogged = false;
        this.username = "";
        this.usermail = "";
        this.code = null;
        LOGGER.info("Logout Successful");

    }

    @Override
    public String toString() {
        return "SessionData{" +
                "id=" + id +
                ", isLogged=" + isLogged +
                ", username='" + username + '\'' +
                ", usermail='" + usermail + '\'' +
                ", privilege='" + privilege + '\'' +
                ", loginTime=" + loginTime +
                ", code='" + code + '\'' +
                '}';
    }
}
