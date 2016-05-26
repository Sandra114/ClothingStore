package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.Order;

import java.util.List;

/**
 * @author Sandra
 */
public interface OrderDao {
    List<Order> getAll();

    void add(Order order);

    void update(Order order);
}
