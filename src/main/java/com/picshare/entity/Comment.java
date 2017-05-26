package com.picshare.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wgm on 17/3/24.
 */
@Entity
@Table(name = "Comment")
public class Comment {
    //properties
    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "persistenceGenerator")
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")//定义生成策略，可以不用在数据库中设置自增
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId",
            foreignKey = @ForeignKey(name = "FK_Comment_userId")
    )
    private User user;

    @ManyToOne
    @JoinColumn(name = "imgId",foreignKey = @ForeignKey(name = "FK_Comment_imageId"))
    private Image image;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicDateTime;

    private String content;

    private boolean isDeleted;

    private boolean isLegal;

    //constructor


    //getter and setter


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public com.picshare.entity.Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Date getPublicDateTime() {
        return publicDateTime;
    }

    public void setPublicDateTime(Date publicDateTime) {
        this.publicDateTime = publicDateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public void setIsLegal(boolean isLegal) {
        this.isLegal = isLegal;
    }
}
