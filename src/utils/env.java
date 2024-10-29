package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class env {
  public static HashMap<String, String> getEnvVar(String file) {
    //Creo la ruta en función de la carpeta que se pase por parámetros
    File env = new File("env" + File.separator + file + File.separator + ".env");

    // Creo el registro
    HashMap<String, String> envVar = new HashMap<>();

    //Leo el contenido dentro del .env y lo almaceno como clave valor en el HashMap
    try (Scanner sc = new Scanner(env)){
      while (sc.hasNextLine()) {
          String data = sc.nextLine();
          // Separo clave y valor en función del igual "="
          String[] parts = data.split("=", 2);
          String key = parts[0].trim();
          String value = parts[1].trim();
          envVar.put(key, value);
        }
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
    return  envVar;
  }

  public static void main(String[] args) {

    /* Test de Prueba */

    //Defino mi carpeta a leer
    String carpeta = "dev";

    //Obtengo la lista HasMap
    HashMap<String, String> VarList = env.getEnvVar(carpeta);

    //Obtengo el valor de la variable que nececito pasando por parámetro su nombre
    String urlDataBAse = VarList.get("dbUrl");

    //Imprimo por pantalla o utilizo la variable obtenida
    System.out.println("URL de mi Base de Datos: " + urlDataBAse);  
  }
}