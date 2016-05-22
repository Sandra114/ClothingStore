package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.OrderItem;
import com.github.sandra114.clothingshop.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author Sandra
 */
public class OrderItemImpl implements OrderItemDao {
    @Override
    public void add(OrderItem orderItem) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(orderItem);
        session.getTransaction().commit();
    }
}
