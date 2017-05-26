package com.picshare.dao;

import com.picshare.entity.Image;
import com.picshare.entity.User;
import com.sun.org.apache.xpath.internal.operations.*;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.String;
import java.nio.channels.SeekableByteChannel;
import java.util.List;

/**
 * Created by wgm on 17/3/24.
 */
@Repository
public class ImageDaoImpl implements ImageDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GenreDao genreDao;

    @Override
    public void addImage(Image image) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(image);
        session.flush();
    }

    @Override
    public Image getImageById(long imageId) {
//        try{
            Session session = sessionFactory.getCurrentSession();
//        String hql = "from com.picshare.entity.Image as img where img.id = :id";
//        Query query = session.createQuery(hql);
//        query.setString("id","1");
//        List<Image> list = query.list();
//        for(Image img:list){
//            System.out.println(img.getId());
//        }
            Criteria criteria = session.createCriteria(Image.class);
            //String id = "" + imageId;
            criteria.add(Restrictions.eq("id",imageId));
            criteria.add(Restrictions.eq("isDeleted",false));
            Image image =  (Image)criteria.uniqueResult();
            //User user = (User)criteria.uniqueResult();
            System.out.println("读取" + image.getName() + "图片");

        return image;
    }

    @Override
    public List<Image> getImagesBy(int limit, String column, boolean isDesc) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Image.class);
        criteria.add(Restrictions.eq("isDeleted",false));
        criteria.setMaxResults(limit);
        if(isDesc){
            criteria.addOrder(Order.desc(column));
        }else {
            criteria.addOrder(Order.asc(column));
        }
        List<Image> images = criteria.list();
//        String hql = "from com.picshare.entity.Image img order by :column";
//        if(isDesc){
//            hql = "from com.picshare.entity.Image img order by :column desc";
//        }
//        List<Image> images = session.createQuery(hql).setParameter("column",column).setFirstResult(0).setMaxResults(limit).list();
        return images;
    }
    @Override
    public List<Image> getImagesBy(int limit, String column,String genre, boolean isDesc) {
        Session session = sessionFactory.getCurrentSession();
//        String hql = "from com.picshare.entity.Image img order by :column";
//        if(idDesc){
//            hql = "from com.picshare.entity.Image img order by :column desc";
//        }
        Criteria criteria = session.createCriteria(Image.class);
        criteria.add(Restrictions.eq("genre", genreDao.getGenreByName(genre)));
        criteria.add(Restrictions.eq("isDeleted",false));
        criteria.setMaxResults(limit);
        if(isDesc){
            criteria.addOrder(Order.desc(column));
        }else {
            criteria.addOrder(Order.asc(column));
        }
        List<Image> images = criteria.list();
        //List<Image> images = session.createQuery(hql).setParameter("column",column).setFirstResult(0).setMaxResults(limit).list();
        return images;
    }

    @Override
    public List<Image> getImagesByKey(String key) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Image.class);
        criteria.add(Restrictions.like("name","%" + key + "%"));
        criteria.add(Restrictions.eq("isDeleted", false));
        List<Image> images = criteria.list();
        return images;
    }

    @Override
    public void update(Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(image);
        session.flush();
        session.getTransaction().commit();

    }

    @Override
    public void deleteImage(Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        image.setIsDeleted(true);
        session.update(image);
        session.flush();
        session.getTransaction().commit();
    }
}
