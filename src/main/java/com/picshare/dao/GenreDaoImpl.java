package com.picshare.dao;

import com.picshare.entity.Genre;
import com.picshare.entity.Image;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by wgm on 17/4/26.
 */
@Repository
public class GenreDaoImpl implements GenreDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addGenre(Genre genre) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(genre);
    }

    @Override
    public Genre getGenreById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Genre.class);
        criteria.add(Restrictions.eq("id", id));
        Genre genre =  (Genre)criteria.uniqueResult();

        return genre;
    }
    @Override
    public Genre getGenreByName(String name){
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Genre.class);
        criteria.add(Restrictions.eq("genreName", name));
        Genre genre =  (Genre)criteria.uniqueResult();
        return genre;
    }
}
