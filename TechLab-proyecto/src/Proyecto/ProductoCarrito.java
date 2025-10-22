package Proyecto;

public class ProductoCarrito {

  private Producto producto;
  private int cantidad;

  public ProductoCarrito(Producto p, int cantidad) {
    setProducto(p);
    setCantidad(cantidad);
  }

  public Producto getProducto() {
    return this.producto;
  }

  public int getCantidad() {
    return this.cantidad;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }
}
