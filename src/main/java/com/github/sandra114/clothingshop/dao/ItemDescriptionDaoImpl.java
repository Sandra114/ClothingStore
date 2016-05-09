package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.ItemDescription;
import com.github.sandra114.clothingshop.model.Size;
import com.github.sandra114.clothingshop.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Sandra
 */
public class ItemDescriptionDaoImpl implements ItemDescriptionDao {
    private static final String FROM_ITEM_DEISCRIPTIONS = "from ItemDescription";
    private static final String FROM_ITEM_BY_ID = "from ItemDescription i where i.id=:id";
    private static final String ID = "id";

    public void add(ItemDescription itemDescription) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(itemDescription);
        session.getTransaction().commit();
    }

    public void delete(ItemDescription itemDescription) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(itemDescription);
        session.flush();
        session.getTransaction().commit();
    }

    public void update(ItemDescription itemDescription) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(itemDescription);
        session.getTransaction().commit();
    }

    public List<ItemDescription> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery(FROM_ITEM_DEISCRIPTIONS);
        List<ItemDescription> list = (List<ItemDescription>) q.list();
        for (ItemDescription itemDescription : list) {
            Hibernate.initialize(itemDescription.getCategory());
        }
        session.getTransaction().commit();
        return list;
    }

    public ItemDescription getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery(FROM_ITEM_BY_ID);
        q.setParameter(ID, id);
        List<ItemDescription> list = (List<ItemDescription>) q.list();
        ItemDescription itemDescription = list.get(0);
        Hibernate.initialize(itemDescription.getSizes());
        for (Size size : itemDescription.getSizes()) {
            Hibernate.initialize(size.getStock());
        }
        Hibernate.initialize(itemDescription.getCategory());
        session.getTransaction().commit();
        return itemDescription;
    }
}
