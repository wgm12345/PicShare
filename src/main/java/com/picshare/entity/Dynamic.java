package com.picshare.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by wgm on 17/4/24.
 */


@Entity
@Table(name = "Dynamic")
public class Dynamic {

    //property
    @Id
    @GeneratedValue(generator = "persistenceGenerator")
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")//定义生成策略，可以不用在数据库中设置自增
    private long id;

    @ManyToOne
    @JoinColumn(name = "senderId",foreignKey = @ForeignKey(name = "FK_Dynamic_senderId"))
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId",foreignKey = @ForeignKey(name = "FK_Dynamic_receiverId"))
    private User receiver;
    private String content;
    private boolean isReaded;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //getter and setter


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReaded() {
        return isReaded;
    }

    public void setIsReaded(boolean isReaded) {
        this.isReaded = isReaded;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
