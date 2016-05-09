package com.github.sandra114.clothingshop.model;

import javax.persistence.*;

/**
 * @author Sandra
 */
@Entity
@Table(name = "items")
public class Size {
    @Id
    @GeneratedValue
    private int id;

    private int size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_desc_id")
    private ItemDescription items;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "size", cascade = CascadeType.ALL)
    private Stock stock;

    public Size() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ItemDescription getItemDescription() {
        return items;
    }

    public void setItemDescription(ItemDescription items) {
        this.items = items;
    }

    public ItemDescription getItems() {
        return items;
    }

    public void setItems(ItemDescription items) {
        this.items = items;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size1 = (Size) o;

        if (id != size1.id) return false;
        if (size != size1.size) return false;
        return items.equals(size1.items);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + size;
        result = 31 * result + items.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", size=" + size +
                ", itemDescription=" + items +
                '}';
    }
}
