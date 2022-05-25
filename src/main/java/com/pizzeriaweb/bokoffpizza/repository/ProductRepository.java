package com.pizzeriaweb.bokoffpizza.repository;

import com.pizzeriaweb.bokoffpizza.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
