package com.pizzeriaweb.bokoffpizza.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pictureURL;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dishes_products",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    @OneToMany(mappedBy = "dish")
    private Set<DishSize> dishSizes;

    public Dish() {
    }

    public Dish(String name, String pictureURL, Set<Product> products) {
        this.name = name;
        this.pictureURL = pictureURL;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<DishSize> getDishSizes() {
        return dishSizes;
    }

    public void setDishSizes(Set<DishSize> dishSizes) {
        this.dishSizes = dishSizes;
    }
}

