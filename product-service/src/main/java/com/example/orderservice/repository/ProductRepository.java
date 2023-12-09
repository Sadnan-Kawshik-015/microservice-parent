package com.example.orderservice.repository;

import com.example.orderservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    @Query(value = "select distinct p from Product p join fetch p.category ")
    List<Product> fetchAllProduct();
    @Query(value = "select distinct p from Product p join fetch p.category where p.category.id=: id")
    List<Product> fetchProductByCategoryId(String id);
}
