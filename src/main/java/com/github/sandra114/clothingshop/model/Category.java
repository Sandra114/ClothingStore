package com.github.sandra114.clothingshop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sandra
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<ItemDescription> itemDescription = new HashSet<>();

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && title.equals(category.title);

    }

    public Set<ItemDescription> getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(Set<ItemDescription> itemDescription) {
        this.itemDescription = itemDescription;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
