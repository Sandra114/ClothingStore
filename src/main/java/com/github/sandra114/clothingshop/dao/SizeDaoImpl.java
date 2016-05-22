package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.Size;
import com.github.sandra114.clothingshop.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Sandra
 */
public class SizeDaoImpl implements SizeDao {
    private static final String ID = "id";
    private static final String FROM_SIZE_WHERE_ID_ID = "from Size where id=:id";

    @Override
    public Size getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery(FROM_SIZE_WHERE_ID_ID);
        q.setParameter(ID, id);
        List<Size> list = (List<Size>) q.list();
        Size size = list.get(0);
        Hibernate.initialize(size.getItems());
        session.getTransaction().commit();
        return size;
    }
}
