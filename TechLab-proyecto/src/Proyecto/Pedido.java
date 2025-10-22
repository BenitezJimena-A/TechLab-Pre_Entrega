package Proyecto;

import java.util.ArrayList;

public class Pedido {

  private static int siguienteId = 1;

  private int id = 1;
  private ArrayList<ProductoCarrito> listaProductos = new ArrayList<>();
  private String nombreCliente = "";
  private Double costoTotal = 0.0;

  public Pedido(ArrayList<ProductoCarrito> listaProductos, String nombreCliente) {
    this.id = siguienteId;
    this.listaProductos = listaProductos;
    this.nombreCliente = nombreCliente;
    calcularCostoTotal();
    siguienteId++;
  }

  public Pedido() {
    this.id = siguienteId;
    listaProductos = new ArrayList<>();
    siguienteId++;
  }

  public Double getCostoTotal() {
    return this.costoTotal;
  }

  public String getNombreCliente() {
    return this.nombreCliente;
  }

  public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
  }
  public void agregarProductoCarrito(ProductoCarrito producto) {
    listaProductos.add(producto);
    calcularCostoTotal();
  }


  public void calcularCostoTotal() {
    costoTotal = 0.0;
    for (ProductoCarrito p : listaProductos) {
      costoTotal += p.getProducto().getPrecio() * p.getCantidad();
    }
  }

  public void infoPedido() {
    System.out.println(String.format("""
        [%s] Pedido de %s.
        Lista de productos-----""", id, getNombreCliente()));
    ProductoCarrito.mostrarProductos(listaProductos);
    System.out.println("Costo total: $" + getCostoTotal());
  }

  public static void mostrarTodosLosPedidos(ArrayList<Pedido> pedidos) {
    String texto = "[%s] Pedido de %s. Costo total: $%s\n";
    for (Pedido p : pedidos) {
      System.out.printf(String.format(texto, p.id, p.getNombreCliente(), p.getCostoTotal()));
    }
  }

  public static Pedido buscarPedidoPorID(ArrayList<Pedido> pedidos, int idPedido) {
    for (Pedido p : pedidos) {
      if (p.id == idPedido) {
        return p;
      }
    }
    return null; // no encontrado
  }
}
