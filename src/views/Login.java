package views;

import controllers.userControls;
import java.util.Scanner;

public class Login {
    Scanner scanner = new Scanner(System.in);
    userControls userController = new userControls();
    private boolean isAuthenticated = false;  // Variable para verificar si el usuario está logueado

    public void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("===== Bienvenido =====");

            // Si el usuario está logueado, mostrar opción de logout
            if (isAuthenticated) {
                System.out.println("4. Cerrar sesión");
            }

            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    // Llamar al método para iniciar sesión
                    logIn();
                    break;
                case 2:
                    // Llamar al método para registrarse
                    signUp();
                    break;
                case 3:
                    // Salir del programa
                    exit = true;
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;
                case 4:
                    if (isAuthenticated) {
                        // Llamar al método para cerrar sesión
                        logOut();
                    } else {
                        System.out.println("No estás logueado.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
                    break;
            }
        }
    }

    private void logIn() {
        boolean authenticated = false;
        int attempts = 0;  // Contador de intentos fallidos

        while (!authenticated && attempts < 3) {
            System.out.print("Ingrese su DNI: ");
            int dni = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            // Intentar iniciar sesión sin especificar cuál es el error
            authenticated = userController.logIn(dni, password);

            if (authenticated) {
                isAuthenticated = true;  // El usuario se ha autenticado
                System.out.println("¡Inicio de sesión exitoso!");
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("DNI o contraseña incorrectos. Intente nuevamente.");
                } else {
                    System.out.println("Demasiados intentos fallidos. El sistema se cerrará.");
                    System.exit(0);  // Cerrar el sistema después de 3 intentos fallidos
                }
            }
        }
    }

    private void logOut() {
        isAuthenticated = false;  // Marcar al usuario como no autenticado
        System.out.println("Has cerrado sesión exitosamente.");
    }

    private void signUp() {
        System.out.println("Registro de nuevo usuario");
        userControls.createUser();
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.start();
    }
}

