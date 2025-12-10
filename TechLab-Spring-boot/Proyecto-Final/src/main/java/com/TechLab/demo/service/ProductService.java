package com.TechLab.demo.service;

import com.TechLab.demo.exception.ProyectException;
import com.TechLab.demo.model.Product;
import java.util.List;

public interface ProductService {

  Product addProduct(Product product);

  List<Product> listProducts(String name, String category);

  Product getProductByID(Integer id);

  Product editProduct(Integer id, Product newData);

  Product removeProduct(Integer id);

}
