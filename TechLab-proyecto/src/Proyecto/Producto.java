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


  public static Producto buscarProductoPorID(ArrayList<Producto> productos, int id) {
    for (Producto p : productos) {
      if (p.id == id) {
        return p;
      }
    }
    return null; // no encontrado
  }

  public static ArrayList buscarProductoPorNombre(ArrayList<Producto> productos, String nombre) {
    ArrayList<Producto> productosEncontrados = new ArrayList<>();
    for (Producto p : productos) {
      if (p.getNombre().contains(nombre)) {
        productosEncontrados.add(p);
      }
    }
    return productosEncontrados;
  }

  public static void mostrarMensajeProductoNoEncontrado(int opcion) {
    int busquedaPorID = 0;
    int busquedaPorNombre = 1;

    String texto = "No se encontraron productos con ese %s. Puede ver los productos existentes en la lista de productos[opción 2] o buscar por %s[opción 3].";
    String mensaje;
    if (opcion == busquedaPorID) { //Busqueda por ID
      mensaje = texto.formatted("ID", "Nombre");
    } else if (opcion == busquedaPorNombre) { // Búsqueda por Nombre
      mensaje = texto.formatted("Nombre", "ID");
    } else {
      mensaje = "Opción no válida.";
    }

    System.out.println(mensaje);
  }

  public static void eliminarProducto(ArrayList<Producto> productos, Producto p) {
    productos.remove(p);
  }

  public static void mostrarListaProductosDetallada(ArrayList<Producto> productos) {
    for (Producto p : productos) {
      System.out.printf(p.infoProducto());
    }
  }

  public static void mostrarListaProductos(ArrayList<Producto> productos) {
    for (Producto p : productos) {
      System.out.println("[" + p.id + "] " + p.getNombre() + " $" + p.getPrecio());
    }
  }

}
