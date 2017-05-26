package com.picshare.service;

import com.picshare.dao.UserDao;
import com.picshare.entity.Image;
import com.picshare.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wgm on 17/3/21.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Override
    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return  userDao.getUserByEmail(email);
    }

    @Override
    public void addFollowUserForFan(User fan, User followUser) {
        this.userDao.addFollowUserForFan(fan, followUser);
    }

    @Override
    public void addCollectImage(User user, Image image) {
       this.userDao.addCollectImage(user, image);
    }

    @Override
    public void cancelCollect(User user, Image image) {
        this.userDao.cancelCollect(user, image);
    }

    @Override
    public void update(User user) {
        this.userDao.update(user);
    }

    @Override
    public void cancelFollow(User fan, User followUser) {
        this.userDao.cancelFollow(fan,followUser);
    }
}
