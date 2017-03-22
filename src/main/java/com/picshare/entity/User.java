package com.picshare.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wgm on 17/3/21.
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

    private Date signUpDateTime;

    private String headImgPath;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignUpDateTime() {
        return signUpDateTime;
    }

    public void setSignUpDateTime(Date signUpDateTime) {
        this.signUpDateTime = signUpDateTime;
    }

    public String getHeadImgPath() {
        return headImgPath;
    }

    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }
}
