package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    ArrayList<Producto> productosBDD = new ArrayList<>();

    int opcion;
    boolean continuar = true;

    while (continuar) {

      opcion = mostrarMenu(entrada);
      entrada.nextLine(); //Limpia el enter que quedo en el buffer

      switch (opcion) {
        case 1:
          agregarProductos(entrada, productosBDD);
          break;
        case 2:
          listarProductos(entrada, productosBDD);
          break;
        case 3:
          //buscarActProductos(productosBDD);
          break;
        case 4:
          eliminarProductos(entrada, productosBDD);
          break;
        case 5:
          //crearPedidos(productosBDD);
          break;
        case 6:
          //listarPedidos(productosBDD);
          break;
        case 7:
          System.out.println("¡Hasta luego!.");
          continuar = false;
          break;
        default:
          System.out.println("Opción incorrecta. Intente nuevamente.");
          break;
      }
    }

  }

  public static int mostrarMenu(Scanner entrada) {
    System.out.print("""
        Menú:
        1) Agregar producto     //LISTO
        2) Listar productos     //LISTO
        3) Buscar/Actualizar producto     //EN PROCESO
        4) Eliminar producto por ID      //LISTO
        5) Crear un pedido      //EN PROCESO
        6) Listar pedidos     //EN PROCESO
        7) Salir      //LISTO
        
        Elija una opción:
        """);
    return entrada.nextInt();
  }

  public static void agregarProductos(Scanner entrada, ArrayList<Producto> productos) {
    boolean continuar = true;
    String nombre;
    Double precio;
    int cantStock;

    while (continuar) {
      System.out.println("-----Agregar nuevos productos-----");
      System.out.println("Ingrese el nombre del producto: ");
      nombre = formatearTexto(entrada, entrada.nextLine());
      System.out.println("Ingrese el precio del producto: ");
      precio = mayorACero(entrada, entrada.nextDouble());
      System.out.println("Ingrese la cantidad de stock del producto: ");
      cantStock = mayorACero(entrada, entrada.nextInt());
      Producto nuevoProducto = new Producto(nombre, precio, cantStock);
      productos.add(nuevoProducto);
      entrada.nextLine(); //Limpia el enter que quedo en el buffer

      continuar = deseaContinuar(entrada);
    }
  }

  public static void listarProductos(Scanner entrada, ArrayList<Producto> productosBDD) {
    if (productosBDD.isEmpty()) {
      System.out.println("No hay productos para mostrar.");
      return;
    }

    System.out.print("""
        -----Lista de productos-----
        [ID]  Nombre  $precio Stock
        """);
    Producto.mostrarListaProductos(productosBDD);

    System.out.println("\nPresiona ENTER para volver al menú...");
    entrada.nextLine();
    return;
  }

  public static void eliminarProductos(Scanner entrada, ArrayList<Producto> productosBDD) {
    boolean continuar = true;
    while (continuar) {
      if (productosBDD.isEmpty()) {
        System.out.println("No hay productos para mostrar.");
        return;
      }

      System.out.println("-----Eliminar productos-----");
      Producto productoSeleccionado = solicitarProductoPorID(entrada, productosBDD);
      if (productoSeleccionado == null) {
        return;  //Si no existe el producto se vuelve al menú.
      }

      System.out.print("El producto seleccionado es: " + productoSeleccionado.infoProducto());
      boolean respuesta = deseaContinuar(entrada);
      if (respuesta) {
        Producto.eliminarProducto(productosBDD, productoSeleccionado);
        System.out.println("El producto fue eliminado exitosamente!.");
      } else {
        System.out.println("La eliminación del producto fue cancelada.");
      }

      continuar = deseaContinuar(entrada);
    }
  }

  public static Producto solicitarProductoPorID(Scanner entrada, ArrayList<Producto> productosBDD) {
    System.out.println("Ingrese el ID del producto que desea eliminar: ");
    int idProductoSeleccionado = entrada.nextInt();
    entrada.nextLine(); // limpiar buffer

    if (!Producto.coincideID(productosBDD, idProductoSeleccionado)) {
      System.out.println(
          "El id del producto no existe. Se sugiere buscar el producto en la lista de productos[opción 2] o por nombre[opción 3].");
      return null;
    }
    return Producto.buscarProductoPorID(productosBDD, idProductoSeleccionado);
  }
  public static boolean deseaContinuar(Scanner entrada) {
    System.out.println("Desea continuar?s/n: ");
    String respuesta = entrada.nextLine().toLowerCase();
    while (!(respuesta.equals("s") || respuesta.equals("n"))) {
      System.out.println("Respuesta invalida, intente nuevamente: ");
      respuesta = entrada.nextLine().toLowerCase();
    }
    return respuesta.equals("s");
  }

  public static String formatearTexto(Scanner entrada, String texto) {
    if (texto.isEmpty()) {
      while (texto.trim().isEmpty()) {
        System.out.println("El texto no puede estar vacío. Inténtalo nuevamente:");
        texto = entrada.nextLine();
      }
    }

    texto = texto.toLowerCase().trim();
    String textoFormateado = texto.substring(0, 1).toUpperCase() + texto.substring(1);
    return textoFormateado;
  }

  private static Double mayorACero(Scanner entrada, double num) {
    while (num < 1) {
      System.out.println("Valor invalido. Intente nuevamente: ");
      num = entrada.nextDouble();
    }
    return num;
  }

  private static int mayorACero(Scanner entrada, int num) {
    while (num < 1) {
      System.out.println("Valor invalido. Intente nuevamente: ");
      num = entrada.nextInt();
    }
    return num;
  }

}
