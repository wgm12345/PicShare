package com.picshare.service;

import com.picshare.entity.Image;
import com.picshare.entity.User;

/**
 * Created by wgm on 17/3/21.
 */
public interface UserService {
    public void addUser(User user);
    public User getUserById(long userId);
    public User getUserByEmail(String email);
    public void addFollowUserForFan(User fan,User followUser);
    public void addCollectImage(User user,Image image);
    public void cancelCollect(User user,Image image);
    public void update(User user);
    public void cancelFollow(User fan,User followUser);

}
