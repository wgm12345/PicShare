package com.picshare.dao;

import com.picshare.entity.Image;
import com.picshare.entity.User;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by wgm on 17/3/21.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;

//    public void setSessionFactory(SessionFactory sf){
//        this.sessionFactory = sf;
//    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public User getUserById(long userId) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id",userId));
        User user = (User)criteria.uniqueResult();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        User user = (User)criteria.uniqueResult();
        return user;
    }

    @Override
    public void addFollowUserForFan(User fan, User followUser) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        //fan.getFollowUsers().add(followUser);
        followUser.getFans().add(fan);
        //session.persist(fan);
        session.persist(followUser);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public void addCollectImage(User user, Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        //fan.getFollowUsers().add(followUser);
        user.getCollections().add(image);
        //session.persist(fan);
        session.persist(user);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public void cancelCollect(User user, Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        user.getCollections().remove(image);
        session.persist(user);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public void cancelFollow(User fan, User followUser) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        fan.getFollowUsers().remove(followUser);
        session.persist(fan);
        session.flush();
        session.getTransaction().commit();
    }


}
