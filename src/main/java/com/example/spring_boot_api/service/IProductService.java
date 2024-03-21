package com.example.spring_boot_api.service;

import com.example.spring_boot_api.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(long id);
    Product save(Product product);
    void delete(long id);
    Iterable<Product> search(int price);
    Iterable<Product> arrangePrice();
    Iterable<Product> arrangePriceTop();
    Iterable<Product> searchByCategory(String name);
}
