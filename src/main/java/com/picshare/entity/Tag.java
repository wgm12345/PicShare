package com.picshare.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wgm on 17/3/31.
 */
@Entity
@Table(name = "Tag")
public class Tag {

    //properties
    @Id
    private long id;

    private String tagName;

    //getter and setter


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
