package com.picshare.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wgm on 17/3/31.
 */
@Entity
@Table(name = "Role")
public class Role {

    //properties
    @Id
    private int id;

    private String roleName;

    //getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
