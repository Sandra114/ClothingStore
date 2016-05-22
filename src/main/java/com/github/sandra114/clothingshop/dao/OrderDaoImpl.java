package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.Order;
import com.github.sandra114.clothingshop.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author Sandra
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public void add(Order order) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }
}
