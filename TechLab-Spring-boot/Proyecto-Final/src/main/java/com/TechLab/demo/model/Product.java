package com.TechLab.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String description;
  private String category;
  private Integer stock;
  private Double price;

  public Product() {
  }

  public Product(String name, String description, Double price,
      String category,
      Integer stock) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.category = category;
    this.stock = stock;
  }


}
