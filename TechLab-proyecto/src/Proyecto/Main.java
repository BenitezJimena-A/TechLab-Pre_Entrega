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
          //eliminarProductos(productosBDD);
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
    System.out.println("""
        Menú:
        1) Agregar producto     //LISTO
        2) Listar productos     //LISTO
        3) Buscar/Actualizar producto     //DEBE ESTAR
        4) Eliminar producto      //DEBE ESTAR
        5) Crear un pedido
        6) Listar pedidos
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
      nombre = formatearTexto(entrada,
          entrada.nextLine());              //NO PERMITIR QUE NO INGRESE NOMBRE
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

    System.out.println("-----Lista de productos-----\n[ID]  Nombre  $precio Stock");
    Producto.mostrarListaPedidos(productosBDD);

    System.out.println("\nPresiona ENTER para volver al menú...");
    entrada.nextLine();
    return;
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
