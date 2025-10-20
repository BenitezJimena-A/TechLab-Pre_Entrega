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

      opcion = menuPrincipal(entrada);
      entrada.nextLine(); //Limpia el enter que quedo en el buffer

      switch (opcion) {
        case 1:
          agregarProductos(entrada, productosBDD);
          break;
        case 2:
          listarProductos(entrada, productosBDD);
          break;
        case 3:
          buscarProductos(entrada, productosBDD);
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

  public static int menuPrincipal(Scanner entrada) {
    System.out.print("""
        Menú:
        1) Agregar producto     //LISTO
        2) Listar productos     //LISTO
        3) Buscar/Actualizar producto     //LISTO
        4) Eliminar producto por ID      //LISTO
        5) Crear un pedido      //EN PROCESO
        6) Listar pedidos     //EN PROCESO
        7) Salir      //LISTO
        
        Elija una opción:
        """);
    return entrada.nextInt();
  }

  public static int menuBuscarProductos(Scanner entrada) {
    System.out.print("""
        -----Buscar productos-----
        Menú:
        1) Buscar producto por ID
        2) Buscar producto por nombre
        3) Volver al menú principal
        """);
    return entrada.nextInt();
  }

  public static int menuActualizarProductos(Scanner entrada, Producto p) {
    String menu = String.format("""
        -----Actualizar producto-----
        El producto seleccionado es: %s
        Actualizar:
        1) Nombre
        2) Precio
        3) Stock
        4) Volver al menú anterior
        """, p.infoProducto());

    System.out.print(menu);
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

      continuar = deseaContinuar(entrada, "Desea agregar más productos?");
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
  }

  public static void buscarProductos(Scanner entrada, ArrayList<Producto> productosBDD) {
    boolean continuar = true;
    int opcionBusqueda;
    Producto productoSeleccionado;
    ArrayList<Producto> listaProductoSeleccionados;

    while (continuar) {
      opcionBusqueda = menuBuscarProductos(entrada);

      switch (opcionBusqueda) {
        case 1:
          productoSeleccionado = solicitarProductoPorID(entrada, productosBDD);
          if (productoSeleccionado == null) {
            Producto.mostrarMensajeProductoNoEncontrado(0);
            return;
          }
          actualizarProducto(entrada, productoSeleccionado);
          break;
        case 2:
          listaProductoSeleccionados = solicitarProductoPorNombre(entrada, productosBDD);
          if (listaProductoSeleccionados.isEmpty()) {
            Producto.mostrarMensajeProductoNoEncontrado(1);
            return;
          }
          actualizarProducto(entrada, listaProductoSeleccionados);
          break;
        case 3:
          return;
        default:
          System.out.print("Opción incorrecta. Intente nuevamente.");
          break;
      }
      continuar = deseaContinuar(entrada, "Desea continuar con la busqueda de productos?");
    }
  }

  public static void actualizarProducto(Scanner entrada, Producto producto) {
    boolean continuar = true;
    int opcionActualizar;

    while (continuar) {
      opcionActualizar = menuActualizarProductos(entrada, producto);
      entrada.nextLine(); //Limpia el enter que quedo en el buffer

      switch (opcionActualizar) {
        case 1:
          actualizarNombre(entrada, producto);
          break;
        case 2:
          actualizarPrecio(entrada, producto);
          break;
        case 3:
          actualizarStock(entrada, producto);
          break;
        case 4:
          entrada.nextLine(); //Limpia el enter que quedo en el buffer
          return;
        default:
          System.out.print("Opción incorrecta. Intente nuevamente.");
          break;
      }
      entrada.nextLine(); //Limpia el enter que quedo en el buffer
      continuar = deseaContinuar(entrada, "Desea seguir actualizando los datos del producto?");
    }
  }

  public static void actualizarProducto(Scanner entrada, ArrayList<Producto> listaProductos) {
    System.out.println("Se encontraron los siguientes productos:");
    Producto.mostrarListaProductos(listaProductos);
    Producto productoSeleccionado = solicitarProductoPorID(entrada, listaProductos);
    if (productoSeleccionado == null) {
      Producto.mostrarMensajeProductoNoEncontrado(0);
      return;
    }
    actualizarProducto(entrada, productoSeleccionado);
  }

  public static void actualizarNombre(Scanner entrada, Producto p) {
    System.out.println("Ingrese el nuevo nombre del producto:");
    String nuevoNombre = formatearTexto(entrada, entrada.nextLine());
    p.setNombre(nuevoNombre);
    System.out.println("El nombre del nombre fue actualizado exitosamente.");
  }

  public static void actualizarPrecio(Scanner entrada, Producto p) {
    System.out.println("Ingrese el nuevo precio del producto:");
    Double nuevoPrecio = mayorACero(entrada, entrada.nextDouble());
    p.setPrecio(nuevoPrecio);
    System.out.println("El precio del producto fue actualizado exitosamente.");
  }

  public static void actualizarStock(Scanner entrada, Producto p) {
    System.out.println("Ingrese el nuevo nombre del producto:");
    int nuevoStock = mayorACero(entrada, entrada.nextInt());
    p.setCantStock(nuevoStock);
    System.out.println("El Stock del producto fue actualizado exitosamente.");
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
        Producto.mostrarMensajeProductoNoEncontrado(0);
        return;
      }

      System.out.print("El producto seleccionado es: " + productoSeleccionado.infoProducto());
      boolean respuesta = deseaContinuar(entrada, "Desea continuar?");
      if (respuesta) {
        Producto.eliminarProducto(productosBDD, productoSeleccionado);
        System.out.println("El producto fue eliminado exitosamente!.");
      } else {
        System.out.println("La eliminación del producto fue cancelada.");
      }

      continuar = deseaContinuar(entrada, "Desea eliminar otro producto?");
    }
  }

  public static Producto solicitarProductoPorID(Scanner entrada, ArrayList<Producto> productosBDD) {
    System.out.println("Ingrese el ID del producto: ");
    int idProductoSeleccionado = entrada.nextInt();
    entrada.nextLine(); // limpiar buffer
    Producto productoEncontrado = Producto.buscarProductoPorID(productosBDD,
        idProductoSeleccionado);

    return productoEncontrado;
  }

  public static ArrayList<Producto> solicitarProductoPorNombre(Scanner entrada,
      ArrayList<Producto> productosBDD) {
    System.out.println("Ingrese el Nombre del producto: ");
    String nombreProductoSeleccionado = formatearTexto(entrada, entrada.nextLine());
    ArrayList<Producto> productosEncontrados = Producto.buscarProductoPorNombre(productosBDD,
        nombreProductoSeleccionado);

    return productosEncontrados;
  }

  public static boolean deseaContinuar(Scanner entrada, String texto) {
    String mensaje = texto.concat("s/n: ");
    System.out.println(mensaje);
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
    String textoFormateado = "";
    texto = texto.toLowerCase().trim();

    String[] palabras = texto.split(" ");
    for (String palabra : palabras) {
      textoFormateado = textoFormateado.concat(palabra.substring(0, 1).toUpperCase())
          .concat(palabra.substring(1)).concat(" ");
    }
    return textoFormateado;
  }

  public static Double mayorACero(Scanner entrada, double num) {
    while (num < 1) {
      System.out.println("Valor invalido. Intente nuevamente: ");
      num = entrada.nextDouble();
    }
    return num;
  }

  public static int mayorACero(Scanner entrada, int num) {
    while (num < 1) {
      System.out.println("Valor invalido. Intente nuevamente: ");
      num = entrada.nextInt();
    }
    return num;
  }

  public static int numeroEntre(Scanner entrada, int min, int max) {
    int num = entrada.nextInt();
    while (num < min || num > max) {
      System.out.print(
          "Valor invalido. El número se encuentra fuera de rango.Intente nuevamente: ");
      num = entrada.nextInt();
    }
    return num;
  }
}
