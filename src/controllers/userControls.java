package controllers;
import java.util.Scanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.ConectionDB;
import models.User;

public class userControls {
    //Declaramos los metodos del CRUD
    
    // Metodo Create
    public static void create() {
        User newUser = User.getUser();

        // Consulta SQL para insertar el usuario
        String query = "INSERT INTO user (name, last_name, email, country, city, dni) VALUES (?, ?, ?, ?, ?, ?)";

        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();

        // Ejecutar la inserción usando PreparedStatement
        try (PreparedStatement stmt = db.executeChange(query)) {
            // Asignar los valores de los parámetros
            stmt.setString(1, newUser.getName());
            stmt.setString(2, newUser.getLast_name());
            stmt.setString(3, newUser.getEmail());
            stmt.setString(4, newUser.getCountry());
            stmt.setString(5, newUser.getCity());
            stmt.setInt(6, newUser.getDni());

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
    }

    //Metodo Read
    public static void read(){
        //Declaro la query
        String query = "SELECT * FROM user";
        //Inicializo una conección con la ruta de variables a implementar
        ConectionDB db = new ConectionDB();
        //Head de la tabla
        System.out.printf("%-20s %-15s %-15s %-20s", "Nombre", "Apellido", "DNI", "Email");
        System.out.println("");
        //Manejo de la respuesta
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String dni = resultSet.getString("dni");
                String email = resultSet.getString("email");
                String output = String.format("%-20s %-15s %-15s %-20s", name, lastName, dni, email);
                System.out.println(output);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        db.closeConectionDB();
    }

    //Metodo delete
    public static void deleteByDni() {
        // Crear un objeto Scanner para leer el DNI desde el teclado
        Scanner scanner = new Scanner(System.in);

        // Pedir el DNI del usuario a eliminar
        System.out.print("Ingrese el DNI del usuario a eliminar: ");
        String dni = scanner.nextLine();

        // Consulta SQL para eliminar el usuario por DNI
        String query = "DELETE FROM user WHERE dni = ?";

        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();

        // Ejecutar la eliminación usando PreparedStatement
        try (PreparedStatement stmt = db.executeChange(query)) {
            // Asignar el valor del parámetro
            stmt.setString(1, dni);

            // Ejecutar la eliminación
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("¡Usuario eliminado exitosamente!");
            } else {
                System.out.println("No se encontró un usuario con el DNI especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        userControls.read();
    }
}
 





