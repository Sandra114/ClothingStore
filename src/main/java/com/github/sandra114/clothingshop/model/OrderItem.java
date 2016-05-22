package com.github.sandra114.clothingshop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Sandra
 */
@Entity
@Table(name = "orderitem")
public class OrderItem {
    @Id
    @GeneratedValue
    private int id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Size size;

    public OrderItem(int quantity, Order order, Size size) {
        this.quantity = quantity;
        this.order = order;
        this.size = size;
    }

    public OrderItem() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (quantity != orderItem.quantity) return false;
        if (!order.equals(orderItem.order)) return false;
        return size.equals(orderItem.size);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quantity;
        result = 31 * result + order.hashCode();
        result = 31 * result + size.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", order=" + order +
                ", size=" + size +
                '}';
    }
}
