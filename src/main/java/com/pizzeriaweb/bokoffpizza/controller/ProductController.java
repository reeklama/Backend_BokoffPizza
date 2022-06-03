package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.model.ProductModel;
import com.pizzeriaweb.bokoffpizza.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin( origins = "*", maxAge = 3500)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<?> getProductsList() {
        try {
            List<ProductModel> productModelList = new ArrayList<>();
            productService.getProducts().forEach(product -> productModelList.add(ProductModel.toModel(product)));
            return ResponseEntity.ok(productModelList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductModel request) {
        productService.addProduct(request.getName());
        return ResponseEntity.ok("Ингредиент добавлен");
    }

    @DeleteMapping("/product")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductModel request) {
        productService.deleteProduct(request.getName());
        return ResponseEntity.ok("Ингредиент удалён");
    }
}
