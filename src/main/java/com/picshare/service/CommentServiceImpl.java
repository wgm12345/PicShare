package com.picshare.service;

import com.picshare.dao.CommentDao;
import com.picshare.entity.Image;
import com.picshare.entity.User;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wgm on 17/3/24.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public void addComment(String content, User user, Image img) {
        this.commentDao.addComment(content,user,img);
    }

    @Override
    public List getDataOfCommentToDisplay(String[] columns, Image image, int numOfPage, int maxNumInOnePage) {
        List res = this.commentDao.getDataOfCommentForImageDisplayPageOrderByCreateTime(columns,image);
        int startIndex = (numOfPage - 1) * maxNumInOnePage;
        int endIndex = startIndex + maxNumInOnePage > res.size() - 1 ? res.size() : startIndex + maxNumInOnePage;
        return res.subList(startIndex,endIndex);
    }
}
