package com.picshare.dao;

import com.picshare.entity.Dynamic;
import com.picshare.entity.Image;
import com.picshare.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

/**
 * Created by wgm on 17/5/5.
 */
@Repository
public class DynamicDaoImpl implements DynamicDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createDynamicsWhenFollowUserUpload(User followUser,Image uploadMaster) {

        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> fans = followUser.getFans();
        for(User fan : fans){

            Dynamic dynamic = new Dynamic();
            dynamic.setSender(followUser);
            dynamic.setReceiver(fan);
            String content =  "image:" + uploadMaster.getId();
            dynamic.setContent(content);
            fan.getDynamicses().add(dynamic);
            session.persist(fan);
        }

        session.flush();
        session.getTransaction().commit();
    }
}
