package com.TechLab.demo.Controller;

import com.TechLab.demo.model.Product;
import com.TechLab.demo.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private final ProductService productservice;

  public ProductController(ProductService productServiceImp) {
    this.productservice = productServiceImp;
  }

  @PostMapping("/productos")
  public Product addProducts(@RequestBody Product product) {
    return this.productservice.addProduct(product);
  }

  @GetMapping("/productos")
  public List<Product> listProducts(
      @RequestParam(required = false, defaultValue = "") String name,
      @RequestParam(required = false, defaultValue = "") String category) {
    return this.productservice.listProducts(name, category);
  }

  @GetMapping("/productos/{id}")
  public Product findProductByID(@PathVariable Integer id) {
    return this.productservice.getProductByID(id);
  }

  @PutMapping("/productos/{id}")
  public Product editProduct(@PathVariable Integer id, @RequestBody Product product) {
    return this.productservice.editProduct(id, product);
  }

  @DeleteMapping("/productos/{id}")
  public Product removeProduct(@PathVariable Integer id) {
    return this.productservice.removeProduct(id);
  }

}
