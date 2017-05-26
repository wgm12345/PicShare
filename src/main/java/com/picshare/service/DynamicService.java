package com.picshare.service;

import com.picshare.entity.Image;
import com.picshare.entity.User;

/**
 * Created by wgm on 17/5/5.
 */
public interface DynamicService {
    public void createDynamicForCommentReply();
    public void createDynamicsWhenFollowUserUpload(User followUser,Image uploadMaster);
}
