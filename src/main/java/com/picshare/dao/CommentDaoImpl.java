package com.picshare.dao;

import com.picshare.entity.Comment;
import com.picshare.entity.Image;
import com.picshare.entity.User;
import com.sun.tools.internal.ws.processor.modeler.annotation.AnnotationProcessorContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by wgm on 17/3/24.
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addComment(String content, User user, Image img) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = new Comment();
        comment.setImage(img);
        comment.setUser(user);
        comment.setContent(content);
        session.persist(comment);
        session.flush();
    }

    @Override
    public List getDataOfCommentForImageDisplayPageOrderByCreateTime(String[] columns,Image image) {
        Session session = sessionFactory.getCurrentSession();
        String sColumns = " ";
        for(int i = 0;i < columns.length - 1 ; i++){
            sColumns += columns[i] +", ";
        }
        sColumns += columns[columns.length - 1];
        String hql = "select :columns " +
                     "from com.picshare.entity.Comment c " +
                     "where c.image = :image " +
                     "order by :column desc ";

        List result = session.createQuery(hql)
                .setParameter("columns",sColumns)
                .setParameter("column","publicTime")
                .setParameter("image",image)
                .list();
        return result;
    }
}
