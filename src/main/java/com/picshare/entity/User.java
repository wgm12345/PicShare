package com.picshare.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wgm on 17/3/21.
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "persistenceGenerator")
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")//定义生成策略，可以不用在数据库中设置自增
    private long id;


    private String name;

    @NaturalId
    private String email;

    @Column(name = "emailConfirmed")
    private boolean isEmailConfirmed; //邮箱是否进行过确认

    private String password; //密码，打算采用加密存储

    @CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    private Date signUpDateTime; //自动注入注册时间戳

    //@Enumerated(EnumType.STRING)//
    private String sex;

    private String headImgPath; //头像图片相对路径

    private boolean isDeleted; //是否被删除

    private boolean isSeaded; //是否被封号

    //参数说明还没懂
    @OneToMany(mappedBy = "uploader",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Image> images = new ArrayList<>(); //用户图片集合


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)//lazy or eager 要如何取舍研究下
    private List<Login> logins = new ArrayList<>(); //实现用户多种方式登录

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Fan_FollowUser",
            joinColumns = {@JoinColumn(name = "fanId")},
            inverseJoinColumns = {@JoinColumn(name = "followUserId")}
    )
    private List<User> fans = new ArrayList<>();//粉丝

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Fan_FollowUser",
            joinColumns = {@JoinColumn(name = "followUserId")},
            inverseJoinColumns = {@JoinColumn(name = "fanId")}
    )
    private List<User> followUsers = new ArrayList<>();//关注的的人


    @OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Dynamic> dynamicses = new ArrayList<>();//用户动态信息

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Collection",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "imageId")}
    )
    private List<Image> collections = new ArrayList<>();

    //functions

    //Construction
//    public User(){}
//
//    public User(String name, String email, String password, String sex, String headImgPath) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.sex = sex;
//        this.headImgPath = headImgPath;
//    }
    //



    //getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setIsEmailConfirmed(boolean isEmailConfirmed) {
        this.isEmailConfirmed = isEmailConfirmed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isDeleled() {
        return isDeleted;
    }

    public void setIsDeeted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isSeaded() {
        return isSeaded;
    }

    public void setIsSeaded(boolean isSeaded) {
        this.isSeaded = isSeaded;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Login> getLogins()
    {
        return logins;
    }

    public List<User> getFans() {
        return fans;
    }

    public List<User> getFollowUsers() {
        return followUsers;
    }

    public List<Dynamic> getDynamicses() {
        return dynamicses;
    }

    public List<Image> getCollections() {
        return collections;
    }
    //overwrite


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getId() == user.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}


