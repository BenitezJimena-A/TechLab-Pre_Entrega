package Proyecto;

import java.util.ArrayList;

public class ProductoCarrito {

  private Producto producto;
  private int cantidad;
  private Double precioTotal;

  public ProductoCarrito(Producto p, int cantidad) {
    setProducto(p);
    setCantidad(cantidad);
    precioTotal = p.getPrecio() * cantidad;
  }

  public Producto getProducto() {
    return this.producto;
  }

  public int getCantidad() {
    return this.cantidad;
  }

  public Double getPrecioTotal() {
    return this.precioTotal;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public static void mostrarProductos(ArrayList<ProductoCarrito> listProductos) {
    for (ProductoCarrito p : listProductos) {
      System.out.println(
          p.producto.getNombre() + " X " + p.getCantidad() + " unidad/es    $"
              + p.getPrecioTotal());
    }
  }
}
