package com.picshare.entity;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wgm on 17/4/1.
 */
@Entity
@Table(name = "Login")
@IdClass(Login.LoginPK.class)
public class Login{

    //property
    @Id
    private String loginProvider; //登录方式
    @Id
    private String loginKey; //登录密码

    @Id
    @ManyToOne
    @JoinColumn( name = "userId",foreignKey = @ForeignKey(name = "FK_Login_userId"))
    private User user;

    public static class LoginPK implements Serializable{
        private String loginProvider;
        private String loginKey;
        private User user;

    }

    //construction

    //getter and setter

    public String getLoginProvider() {
        return loginProvider;
    }

    public void setLoginProvider(String loginProvider) {
        this.loginProvider = loginProvider;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}