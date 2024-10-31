package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conection.ConectionDB;

public class userControls {
    //Declaramos los metodos del CRUD

    //Metodo Read
    public static void read(){
        //Declaro la query
        String query = "SELECT * FROM user";
        //Inicializo una conección con la ruta de variables a implementar
        ConectionDB db = new ConectionDB();
        //Manejo de la respuesta
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String dni = resultSet.getString("dni");
                String email = resultSet.getString("email");
                System.out.println("Nombre: " + name + " Apellido: " + lastName + " DNI: " + dni  + " Email: " + email);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        db.closeConectionDB();
    }


    public static void addUser() {
    // Crear un objeto Scanner para leer datos desde el teclado
    Scanner scanner = new Scanner(System.in);

    // Pedir datos al usuario
    System.out.print("Ingrese el nombre: ");
    String name = scanner.nextLine();

    System.out.print("Ingrese el apellido: ");
    String lastName = scanner.nextLine();

    System.out.print("Ingrese el DNI: ");
    String dni = scanner.nextLine();

    System.out.print("Ingrese el email: ");
    String email = scanner.nextLine();

    System.out.print("Ingrese el pais:");
    String country = scanner.nextLine();

    System.out.print("Ingrese su ciudad:");
    String city = scanner.nextLine();

    // Consulta SQL para insertar el usuario
    String query = "INSERT INTO user (name, last_name, dni, email) VALUES (?, ?, ?, ?)";

    // Crear una instancia de la conexión
    ConectionDB db = new ConectionDB();

    // Ejecutar la inserción usando PreparedStatement
    try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
        // Asignar los valores de los parámetros
        stmt.setString(1, name);
        stmt.setString(2, lastName);
        stmt.setString(3, dni);
        stmt.setString(4, email);
        stmt.setString(5, country);
        stmt.setString(6, city);

        // Ejecutar la inserción
        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("¡Usuario agregado exitosamente!");
        }
    } catch (SQLException e) {
        System.err.println("Error al insertar los datos: " + e.getMessage());
    } finally {
        db.closeConectionDB();
    }
    scanner.close();
}

public static void main(String[] args) {
    // Llamar al método para agregar un usuario
    addUser();
}
}
