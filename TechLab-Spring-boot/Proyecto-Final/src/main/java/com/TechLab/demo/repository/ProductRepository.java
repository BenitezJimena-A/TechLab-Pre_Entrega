package com.TechLab.demo.repository;

import com.TechLab.demo.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html


  List<Product> findByNameContainingAndCategoryContainingIgnoreCase(String name, String category);

  List<Product> findByCategoryContainingIgnoreCase(String category);

  List<Product> findByNameContainingIgnoreCase(String nombre);
}
