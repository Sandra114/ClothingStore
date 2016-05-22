package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.Client;
import com.github.sandra114.clothingshop.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author Sandra
 */
public class ClientDaoImpl implements ClientDao {
    @Override
    public void add(Client client) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(client);
        session.getTransaction().commit();
    }
}
