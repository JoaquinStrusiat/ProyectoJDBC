package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conection.ConectionDB;
import views.Views;

public class login {
    private static Scanner scanner = new Scanner(System.in);

    public static void logIn() {
        int attempts = 0;  // Contador de intentos fallidos

        while (attempts < 3) {
            System.out.print("Ingrese su DNI: ");
            int dni = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            String query = "SELECT * FROM users WHERE dni = " + dni;
            ConectionDB db = new ConectionDB();
            
            try (ResultSet res = db.executeQuery(query)) {
                if (res.next()) { // Verificar si existe un registro con el DNI ingresado
                    String passStorage = res.getString("password");
                    boolean authenticated = HashUtils.verifyPassword(password, passStorage);
                    if (authenticated) {
                        int userId = res.getInt("id"); // obtengo ID del usuario
                        System.out.println("¡Inicio de sesión exitoso!");
                        Views.showCRUDMenu(userId);
                    } else {
                        attempts++;
                        if (attempts < 3) {
                            System.out.println("DNI o contraseña incorrectos. Intente nuevamente.");
                        } else {
                            System.out.println("Demasiados intentos fallidos. El sistema se cerrará.");
                            System.exit(0);  // Cerrar el sistema después de 3 intentos fallidos
                        }
                    }
                } else {
                   System.out.println("Usuario inexistente.");
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar las credenciales: " + e.getMessage());
            } finally {
                // Cerrar la conexión a la base de datos
                db.closeConectionDB();
            }
        }
    }
}