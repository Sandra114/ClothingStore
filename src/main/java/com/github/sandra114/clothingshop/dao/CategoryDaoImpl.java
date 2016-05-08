package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.Category;
import com.github.sandra114.clothingshop.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Sandra
 */
public class CategoryDaoImpl implements CategoryDao {

    private static final String FROM_CATEGORY = "from Category";
    private static final String FROM_CATEGORY_C_WHERE_ID = "from Category c where c.id=:id";
    private static final String ID = "id";

    public void add(Category category) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
    }

    public void delete(Category category) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(category);
        session.flush();
        session.getTransaction().commit();
    }

    public void update(Category category) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(category);
        session.getTransaction().commit();
    }

    public List<Category> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery(FROM_CATEGORY);
        List<Category> list = (List<Category>) q.list();
        session.getTransaction().commit();
        return list;
    }

    public Category getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery(FROM_CATEGORY_C_WHERE_ID);
        q.setParameter(ID, id);
        List<Category> list = (List<Category>) q.list();
        session.getTransaction().commit();
        return list.get(0);
    }
}
