package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class Producto{
  private static int siguienteId = 1;

  private int id;
  private String nombre;
  private Double precio;
  private int cantStock;

  public Producto(String nombreProducto, Double precioProducto, int cantStockProducto) {//Constructor para crear el producto con sus atributos
    id = siguienteId;
    setNombre(nombreProducto);
    setPrecio(precioProducto);
    setCantStock(cantStockProducto);
    siguienteId ++;
  }

  public String getNombre(){
    return this.nombre;
  }

  public Double getPrecio() {
    return this.precio;
  }

  public int getCantStock() {
    return this.cantStock;
  }

  public String infoProducto(){
    return String.format("[%s] %s $%s Stock: %s \n", id, nombre, precio, cantStock);
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public void setCantStock(int cantStock) {
    this.cantStock = cantStock;
  }

  public static boolean coincideID(ArrayList<Producto> productos, int id) {
    for (Producto p : productos) {
      if (p.id == id) {
        return true;
      }
    }
    return false;
  }

  public static Producto buscarProductoPorID(ArrayList<Producto> productos, int id) {
    for (Producto p : productos) {
      if (p.id == id) {
        return p;
      }
    }
    return null; // no encontrado
  }

  public static void eliminarProducto(ArrayList<Producto> productos, Producto p) {
    productos.remove(p);
  }

  public static void mostrarListaProductos(ArrayList<Producto> productos) {
    for (Producto p : productos) {
      System.out.printf(p.infoProducto());
    }
  }

}
