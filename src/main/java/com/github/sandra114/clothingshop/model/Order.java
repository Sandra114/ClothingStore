package com.github.sandra114.clothingshop.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sandra
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "order_date")
    private Date date;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_phone")
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderItem> orders = new HashSet<>();

    public Order() {
    }

    public Order(Date date, String status, Client client) {
        this.date = date;
        this.status = status;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status.trim();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderItem> orders) {
        this.orders = orders;
    }

    public void addOrderItem(OrderItem orderItem) {
        orders.add(orderItem);
    }

    public int getCount() {
        return orders.stream()
                .map(OrderItem::getQuantity)
                .reduce((integer, integer2) -> integer + integer2)
                .orElse(0);
    }

    public double getTotalPrice() {
        return orders.stream()
                .map(item -> item.getQuantity() * item.getSize().getItems().getPrice())
                .reduce((aDouble, aDouble2) -> aDouble+aDouble2)
                .orElse(0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!date.equals(order.date)) return false;
        if (!status.equals(order.status)) return false;
        return client.equals(order.client);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + date.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", client=" + client +
                '}';
    }
}
