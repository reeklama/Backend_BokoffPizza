package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Product;
import com.pizzeriaweb.bokoffpizza.exception.EmptyProductsListException;
import com.pizzeriaweb.bokoffpizza.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @Test
    void getProductsException() {
        assertThrows(EmptyProductsListException.class, () -> productService.getProducts());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getProducts() throws Exception {
        List<Product> list = new ArrayList<>();
        list.add(new Product());
        Mockito.doReturn(list)
                .when(productRepository)
                .findAll();
        assertEquals(1, productService.getProducts().size());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    void addProduct() {

        productService.addProduct("cheese");
        Mockito.verify(productRepository, Mockito.times(1)).save(ArgumentMatchers.any(Product.class));
        Mockito.verify(productRepository, Mockito.times(1)).findByName("cheese");
    }

    @Test
    void deleteProduct() {
        Product product = new Product();
        product.setDishes(new HashSet<>());
        Mockito.doReturn(product)
                .when(productRepository)
                .findByName("cheese");
        productService.deleteProduct("cheese");
        Mockito.verify(productRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Product.class));
        Mockito.verify(productRepository, Mockito.times(1)).findByName("cheese");
    }
}