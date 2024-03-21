package com.example.spring_boot_api.repository;

import com.example.spring_boot_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from  Product  p where  p.price = ?1")
    Iterable<Product> searchProduct(int price);

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    Iterable<Product> arrangePrice();
    @Query("SELECT p FROM Product p ORDER BY p.price DESC LIMIT 3")
    Iterable<Product> arrangePriceTop();
    @Query("SELECT p FROM Product p WHERE p.category.name LIKE ?1")
    Iterable<Product> searchByCategory(String name);
}
