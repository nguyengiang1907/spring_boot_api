package com.example.spring_boot_api.controller;

import com.example.spring_boot_api.model.Category;
import com.example.spring_boot_api.model.Product;
import com.example.spring_boot_api.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @GetMapping
    public ResponseEntity<List<Product>> findAllProduct(){
        List<Product> products = (List<Product>) iProductService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("/search/{price}")
    public ResponseEntity<Iterable<Product>> searchProduct(@PathVariable int price){
        List<Product> computers = (List<Product>) iProductService.search(price);
        if (computers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(computers,HttpStatus.OK);
    }
    @GetMapping("/arrange")
    public ResponseEntity<Iterable<Product>> arrangeProduct(){
        List<Product> products = (List<Product>) iProductService.arrangePrice();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("/arrangeTop")
    public ResponseEntity<Iterable<Product>> arrangeProductTop(){
        List<Product> products = (List<Product>) iProductService.arrangePriceTop();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("/searchCategory/{name}")
    public ResponseEntity<Iterable<Product>> searchByCategory(@PathVariable String name){
        List<Product> products = (List<Product>) iProductService.searchByCategory(name);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id){
        Optional<Product> productOptional = iProductService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(iProductService.save(product),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id , @RequestBody Product computer){
        Optional<Product> productOptional = iProductService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        computer.setId(productOptional.get().getId());
        return new ResponseEntity<>(iProductService.save(computer),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteCustomer(@PathVariable long id){
        Optional<Product> productOptional = iProductService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductService.delete(id);
        return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
    }
}
