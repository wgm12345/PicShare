package com.picshare.service;

import com.picshare.entity.Image;
import com.picshare.entity.User;

import java.util.List;

/**
 * Created by wgm on 17/3/24.
 */
public interface CommentService {
    public void addComment(String content,User user,Image img);
    public List getDataOfCommentToDisplay(String[] columns,Image image,int numOfPage,int maxNumInOnePage);
}
