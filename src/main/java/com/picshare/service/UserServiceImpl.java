package com.picshare.service;

import com.picshare.dao.UserDao;
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
}
