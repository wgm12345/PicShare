package com.picshare.dao;

import com.picshare.entity.Comment;
import com.picshare.entity.Image;
import com.picshare.entity.User;

import java.util.List;

/**
 * Created by wgm on 17/3/24.
 */
public interface CommentDao {
    public void addComment(String content,User user,Image img);
    public List getDataOfCommentForImageDisplayPageOrderByCreateTime(String[] columns,Image image);
}
