package controllers;
import java.util.Scanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conection.ConectionDB;
import models.User;
import utils.HashUtils;

public class userControls {
    
    // Metodo Create
    public static void createUser() {
        User newUser = User.createUser();
    
        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();
    
        // Ejecutar la inserción usando PreparedStatement
        try (
            PreparedStatement stmtUser = db.executeChange(
                "INSERT INTO users (name, last_name, email, country, city, dni) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS // Permite obtener las claves generadas automáticamente
            );
            PreparedStatement stmPws = db.executeChange("INSERT INTO passwords (id_usuario, password) VALUES (?, ?)")
        ) {
            // guardo al usuario
            stmtUser.setString(1, newUser.getName());
            stmtUser.setString(2, newUser.getLast_name());
            stmtUser.setString(3, newUser.getEmail());
            stmtUser.setString(4, newUser.getCountry());
            stmtUser.setString(5, newUser.getCity());
            stmtUser.setInt(6, newUser.getDni());
            int rowsInsertedUser = stmtUser.executeUpdate();
    
            // Obtener el ID generado automáticamente
            try (ResultSet resUser = stmtUser.getGeneratedKeys()) {
                if (resUser.next()) {
                    int userId = resUser.getInt(1); // Obtiene el ID generado
    
                    // Inserta la contraseña con el ID de usuario
                    stmPws.setInt(1, userId);
                    stmPws.setString(2, newUser.getPassword());
                    int rowsInsertedPws = stmPws.executeUpdate();
    
                    if (rowsInsertedUser > 0 && rowsInsertedPws > 0) {
                        System.out.println("¡Usuario creado exitosamente!");
                    }
                } else {
                    throw new SQLException("Error al obtener el ID del usuario insertado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar los datos: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
    }
    

    //Metodo Read
    public static void readUsers(){
        //Declaro la query
        String query = "SELECT * FROM users";
        //Inicializo una conección con la ruta de variables a implementar
        ConectionDB db = new ConectionDB();
        //Head de la tabla
        System.out.printf("%-3s %-20s %-15s %-15s %-20s", "ID","Nombre", "Apellido", "DNI", "Email");
        System.out.println("");
        //Manejo de la respuesta
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String dni = resultSet.getString("dni");
                String email = resultSet.getString("email");
                String output = String.format("%-3s %-20s %-15s %-15s %-20s", id, name, lastName, dni, email);
                System.out.println(output);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        db.closeConectionDB();
    }

    //Metodo delete
    public static void deleteUserById() {
        //Muestro por pantalla los usuarios para selecionar por id el que se quiera eliminar
        userControls.readUsers();

        int id;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("\nIngrese el ID del usuario a eliminar: ");
                id = scanner.nextInt();
                scanner.close();
                break;
            } catch (Exception e) {
                System.out.println("\nEntrada no valida: ");
            }
        }

        // Consulta SQL para eliminar el usuario por ID
        String query = "DELETE FROM users WHERE id = ?";

        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();

        // Ejecutar la eliminación usando PreparedStatement
        try (PreparedStatement stmt = db.executeChange(query)) {
            // Asignar el valor del parámetro
            stmt.setInt(1, id);

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
    }

    // 
    //consulta a la base de datos para ver si dni y constraseña existen y coinciden
    public boolean logIn(int dni, String password) {
        String query = "SELECT password FROM users WHERE dni = ?";
        
        // Crear una instancia de la conexión a la base de datos
        ConectionDB db = new ConectionDB();
        
        try (PreparedStatement statement = db.executeChange(query)) {
            // Asignar los valores de DNI y contraseña a la consulta
            statement.setInt(1, dni);
            
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
            String varr = resultSet.getString("password");
            System.out.println(varr);
            boolean verificacion = HashUtils.verifyPassword(password,varr);
            
            // Si se encuentra un registro que coincide, devolver true
            return verificacion;
        } catch (SQLException e) {
            System.err.println("Error al verificar las credenciales: " + e.getMessage());
        } finally {
            // Cerrar la conexión a la base de datos
            db.closeConectionDB();
        }
        
        return false;
    }
        // 

    public static void main(String[] args) {
        userControls.createUser();
    }
} 