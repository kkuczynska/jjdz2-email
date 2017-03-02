package com.jbd.database;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;

@SessionScoped
@Entity
@Table(name = "User")
@NamedQuery(name = "User.findAll", query = "select p FROM User p")
public class User implements Serializable, Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private boolean isLogged = false;
    private String username;
    private String usermail;
    @Transient
    private int privilege;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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


    @Override
    public int compareTo(User o) {
        if (getLoginTime().isEqual(o.getLoginTime()))
            return 0;
        else if (getLoginTime().isAfter(o.getLoginTime()))
            return 1;
        else
            return -1;
    }
}
