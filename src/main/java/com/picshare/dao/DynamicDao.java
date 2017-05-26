package com.picshare.dao;

import com.picshare.entity.Image;
import com.picshare.entity.User;

/**
 * Created by wgm on 17/5/5.
 */
public interface DynamicDao {
    public void createDynamicsWhenFollowUserUpload(User followUser,Image uploadMaster);
}
