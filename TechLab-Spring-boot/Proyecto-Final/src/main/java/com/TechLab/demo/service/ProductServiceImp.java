package com.TechLab.demo.service;

import com.TechLab.demo.Utils.Utils;
import com.TechLab.demo.model.Product;
import com.TechLab.demo.repository.ProductRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//Reemplazar print por log.info()
//log.info("Producto ingresado: {}", product); ejemplo de uso
//poner a todo mensajes de si se realizo o no la operacion
public class ProductServiceImp implements ProductService {

  //private ProductRepositoryMemory repository;
  private final ProductRepository productRepository;
  private final Utils utilities;

  public ProductServiceImp(ProductRepository productRepository, Utils utilities) {
    this.productRepository = productRepository;
    this.utilities = utilities;
  }


  public Product addProduct(Product product) {
    log.info("-----Creating the product-----");
    return this.productRepository.save(product);
  }

  public List<Product> listProducts(String name, String category) {
    System.out.println("-----Product list-----");
    if (!name.isEmpty() && !category.isEmpty()) {
      return this.productRepository.findByNameContainingAndCategoryContainingIgnoreCase(name,
          category);
    }
    if (!category.isEmpty()) {
      return this.productRepository.findByCategoryContainingIgnoreCase(category);
    }
    if (!name.isEmpty()) {
      return this.productRepository.findByNameContainingIgnoreCase(name);
    }
    return this.productRepository.findAll();
  }

  public Product getProductByID(Integer id) {
    return this.productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product was not found."));
  }

  public Product editProduct(Integer id, Product newData) {
    Product productSelect = this.getProductByID(id);
    //find product
    System.out.println("-----Editing product-----");
    Product product = this.productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product was not found."));

    //validation name
    if (!utilities.isEmpty(newData.getName())) {
      log.info("The name was updated. Last: {}, New: {}.", productSelect.getName(),
          newData.getName());
      product.setName(newData.getName());
    }

    //validation description
    if (!utilities.isEmpty(newData.getDescription())) {
      log.info("The description was updated. Last: {}, New: {}.", productSelect.getDescription(),
          newData.getDescription());
      product.setDescription(newData.getDescription());
    }

    //validation category
    if (!utilities.isEmpty(newData.getCategory())) {
      log.info("The category was updated. Last: {}, New: {}.", productSelect.getCategory(),
          newData.getCategory());
      product.setCategory(newData.getCategory());
    }

    //validation stock
    if (!utilities.integerValidation(newData.getStock())) {
      log.info("The stock was updated. Last: {}, New: {}.", productSelect.getStock(),
          newData.getStock());
      product.setStock(newData.getStock());
    }

    //validation price
    if (!utilities.doubleValidation(newData.getPrice())) {
      log.info("The price was updated. Last: {}, New: {}.", productSelect.getPrice(),
          newData.getPrice());
      product.setPrice(newData.getPrice());
    }

    return this.productRepository.save(product);
  }

  public Product removeProduct(Integer id) {
    Product productSelect = this.productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product was not found."));

    System.out.println("-----Removing product-----");
    productRepository.delete(productSelect);
    return productSelect;
  }
}