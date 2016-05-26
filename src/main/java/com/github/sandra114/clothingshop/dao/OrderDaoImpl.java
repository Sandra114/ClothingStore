package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.Order;
import com.github.sandra114.clothingshop.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Sandra
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery("from Order");
        List<Order> list = (List<Order>) q.list();
        list.forEach(order -> Hibernate.initialize(order.getOrders()));
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void add(Order order) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }

    @Override
    public void update(Order order) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
    }
}
