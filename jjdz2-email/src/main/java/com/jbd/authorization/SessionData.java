package com.jbd.authorization;


import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;


@SessionScoped
@Entity
@Table(name = "User")
@NamedQuery(name = "SessionData.findAll", query = "select p FROM SessionData p")
public class SessionData implements Serializable {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SessionData.class);
    private static final Marker MARKER = MarkerFactory.getMarker("SessionData");
    public static final int ADMIN = 1;
    public static final int LOCAL_USER = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private boolean isLogged = false;
    private String username;
    private String usermail;
    private int privilege;
    @Transient
    private LocalDateTime loginTime;
    @Transient
    private String code = null;
    @Transient
    private Locale locale;


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

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void login(String code, String username, String usermail, int privilege) {
        if (!code.equals("")) {
            this.isLogged = true;
            this.username = username;
            this.usermail = usermail;
            this.code = code;
            this.loginTime = LocalDateTime.now();
            if (privilege != ADMIN || privilege != LOCAL_USER) {
                this.privilege = LOCAL_USER;
            } else if (privilege == LOCAL_USER) {
                this.privilege = privilege;
            } else
                this.privilege = privilege;
        }


    }

    public void logout() {
        this.isLogged = false;
        this.username = "";
        this.usermail = "";
        this.code = null;
        LOGGER.info(MARKER,"Logout Successful");

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
