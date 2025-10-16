import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    ArrayList<String> productosBDD;

    int opcion;
    boolean continuar = true;

    while (continuar) {

      opcion = mostrarMenu();

      switch (opcion) {
        case 1:
          productosBDD.agregarProductos();
          break;
        case 2:
          productosBDD.listarProductos();
          break;
        case 3:
          productosBDD.buscarActProductos();
          break;
        case 4:
          productosBDD.eliminarProductos();
          break;
        case 5:
          productosBDD.crearPedidos();
          break;
        case 6:
          productosBDD.listarPedidos();
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

  public static int mostrarMenu() {
    Scanner entrada = new Scanner(System.in);
    System.out.println("""
        Menú:
        1) Agregar producto
        2) Listar productos
        3) Buscar/Actualizar producto
        4) Eliminar producto
        5) Crear un pedido
        6) Listar pedidos
        7) Salir
        
        Elija una opción:
        """);
    int opcion = entrada.nextInt();
    return opcion;
  }

  public static void agregarProductos() {
    Scanner entrada = new Scanner(System.in);
    boolean continuar = true;
    String nombre;
    Double precio;
    int cantStock;

    while (continuar) {
      System.out.println("Ingrese el nombre del producto");
      nombre = formatearTexto(entrada.nextLine());
      System.out.println("Ingrese el precio del producto");
      precio = mayorACero(entrada.nextDouble());
    }

    int opcion = entrada.nextInt();
  }

  public static String formatearTexto(String texto) {
    String textoFormateado = "";

    if (!texto.isEmpty()) {
      texto = texto.toLowerCase().trim();//Todas las letras en minuscula + eliminar espacios extra
      textoFormateado = texto.substring(0, 1).toUpperCase() + texto.substring(
          1); //Primera letra mayuscula, el resto se mantiene en minus
    }
    return textoFormateado;
  }

  private static Double mayorACero(double num) {
    Scanner entrada = new Scanner(System.in);
    while (num < 1) {
      System.out.println("");
    }
    return num;
  }
}
