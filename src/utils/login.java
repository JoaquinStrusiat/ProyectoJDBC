package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conection.ConectionDB;
import views.Views;

public class login {

    public static void logIn() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int attempts = 0; // Contador de intentos fallidos
    
        while (attempts < 3) {
            System.out.print("Ingrese su DNI: ");
            String dni = scanner.nextLine();
    
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();
    
            String query = "SELECT * FROM users WHERE dni = " + dni;
            ConectionDB db = new ConectionDB();
    
            try (ResultSet res = db.executeQuery(query)) {
                if (res.next()) { // Verificar si existe un registro con el DNI ingresado
                    String passStorage = res.getString("password");
                    boolean authenticated = HashUtils.verifyPassword(password, passStorage);
    
                    if (authenticated) {
                        System.out.println("---¡Inicio de sesión exitoso!---");
                        // Obtengo el ID del usuario
                        int userId = res.getInt("id"); 
                        Views.showCRUDMenu(userId); // Ejecuta la lógica del menú CRUD
                        return; 
                    } else {
                        attempts++;
                        if (attempts < 3) {
                            System.out.println("DNI o contraseña incorrectos. Intente nuevamente\n");
                        } else {
                            System.out.println("---Demasiados intentos fallidos. El sistema se cerrará---");
                            System.exit(0); // Termina la ejecución si se superan los 3 intentos fallidos
                        }
                    }
                } else {
                    System.out.println("---Usuario inexistente---");
                    return; // Termina la ejecución si el usuario no existe
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar las credenciales: " + e.getMessage());
                System.exit(1); // Termina la ejecución en caso de error en la base de datos
            } finally {
                db.closeConectionDB(); // Cierra la conexión a la base de datos
            }
        }
    }
}