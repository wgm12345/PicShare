package com.picshare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wgm on 17/4/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)//
@JsonSerialize
public class DisplayDataOfComment implements Serializable{

    private String content;
    private String commenterName;
    private String publicTime;
    private String commenterHeadImagePath;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommenterHeadImagePath() {
        return commenterHeadImagePath;
    }

    public void setCommenterHeadImagePath(String commenterHeadImagePath) {
        this.commenterHeadImagePath = commenterHeadImagePath;
    }



    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public DisplayDataOfComment(String content, String commenterName, String publicTime, String commenterHeadImagePath) {
        this.content = content;
        this.commenterName = commenterName;
        this.publicTime = publicTime;
        this.commenterHeadImagePath = commenterHeadImagePath;
    }

    //    public DisplayDataOfComment(Comment comment){
//        User commenter = comment.getUser();
//        this.content = comment.getContent();
//        this.commenterName = commenter.getName();
//        this.commenterHeadImagePath = commenter.getHeadImgPath();
//        //this.publicTime = comment.getPublicDateTime();
//    }
}
