package com.github.sandra114.clothingshop.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sandra
 */
@Entity
@Table(name = "stock")
public class Stock {
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "stock"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "item_id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Size size;

    private int rest;

    public Stock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (id != stock.id) return false;
        if (rest != stock.rest) return false;
        return size.equals(stock.size);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + size.hashCode();
        result = 31 * result + rest;
        return result;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", size=" + size +
                ", rest=" + rest +
                '}';
    }
}
