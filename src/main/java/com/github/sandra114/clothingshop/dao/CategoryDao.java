package com.github.sandra114.clothingshop.dao;

import com.github.sandra114.clothingshop.model.Category;

import java.util.List;

/**
 * @author Sandra
 */
public interface CategoryDao {

    void add(Category category);

    void delete(Category category);

    void update(Category category);

    List<Category> getAll();

    Category getById(int id);
}
