package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.ItemDescription;

import java.util.List;

/**
 * @author Sandra
 */
public interface ItemDescriptionDao {
    void add(ItemDescription itemDescription);

    void delete(ItemDescription itemDescription);

    void update(ItemDescription itemDescription);

    List<ItemDescription> getAll();

    ItemDescription getById(int id);
}
