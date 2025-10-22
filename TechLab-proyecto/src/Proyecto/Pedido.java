package Proyecto;

import java.util.ArrayList;

public class Pedido {

  private static int siguienteId = 1;

  private int id;
  private ArrayList<ProductoCarrito> listaProductos = new ArrayList<>();
  private Double costoTotal = 0.0;

  public Pedido(ArrayList<ProductoCarrito> listaProductos) {
    this.id = siguienteId;
    this.listaProductos = listaProductos;
    //this.costoTotal = calcularCostoTotal(listaProductos);
    siguienteId++;
  }

  public Pedido() {
    this.id = siguienteId;
    listaProductos = new ArrayList<>();
    siguienteId++;
  }

  public void agregarProductoCarrito(ProductoCarrito producto) {
    listaProductos.add(producto);
  }

/*
  public double calcularCostoTotal() {
    if(listaProductos.isEmpty()){}
  }

  public String infoPedido() {
    String mensaje = String.format("""
        Pedido id:%s
        Lista de productos-----
        %s
        Costo total: $%s
        """, id, listaProductos, costoTotal);
    return mensaje;
  }

  public static void mostrarListaPedidos(ArrayList<Pedido> pedidos) {
    for (Pedido p : pedidos) {
      System.out.printf(p.infoPedido());
    }
  }
  */
}
