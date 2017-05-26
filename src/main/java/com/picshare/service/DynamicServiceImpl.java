package com.picshare.service;

import com.picshare.dao.DynamicDao;
import com.picshare.entity.Image;
import com.picshare.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wgm on 17/5/5.
 */
@Service
public class DynamicServiceImpl implements DynamicService{
    @Autowired
    private DynamicDao dynamicDao;

    @Override
    public void createDynamicForCommentReply() {

    }

    @Override
    public void createDynamicsWhenFollowUserUpload(User followUser, Image uploadMaster) {
        this.dynamicDao.createDynamicsWhenFollowUserUpload(followUser,uploadMaster);
    }
}
