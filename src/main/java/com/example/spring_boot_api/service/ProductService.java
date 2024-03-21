package com.example.spring_boot_api.service;

import com.example.spring_boot_api.model.Product;
import com.example.spring_boot_api.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
       return iProductRepository.save(product);
    }

    @Override
    public void delete(long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> search(int price) {
        return iProductRepository.searchProduct(price);
    }

    @Override
    public Iterable<Product> arrangePrice() {
        return iProductRepository.arrangePrice();
    }
    @Override
    public Iterable<Product> arrangePriceTop() {
        return iProductRepository.arrangePriceTop();
    }

    @Override
    public Iterable<Product> searchByCategory(String name) {
        return iProductRepository.searchByCategory("%"+name+"%");
    }
}
