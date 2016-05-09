package com.github.sandra114.clothingshop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sandra
 */
@Entity
@Table(name = "itemdescription")
public class ItemDescription {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "titile")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "items", cascade = CascadeType.ALL)
    private Set<Size> sizes = new HashSet<>();

    private String photo;
    private String gender;
    private double price;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDescription that = (ItemDescription) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (!title.equals(that.title)) return false;
        if (!category.equals(that.category)) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        return gender.equals(that.gender);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + gender.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ItemDescription{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", photo='" + photo + '\'' +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}
