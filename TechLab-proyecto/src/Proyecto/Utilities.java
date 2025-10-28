package Proyecto;

import java.util.Scanner;

public class Utilities {

  public Utilities() {
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
