package com.picshare.entity;

import javax.persistence.*;

/**
 * Created by wgm on 17/3/30.
 */
@Entity
@Table(name = "Genre")
public class Genre {

    //properties
    @Id
    private int id;

    private String genreName;

    //getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
