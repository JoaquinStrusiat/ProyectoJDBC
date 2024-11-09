package views;

import utils.login;
import controllers.userControls;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private static Scanner scanner = new Scanner(System.in);

    public static void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("===== Bienvenido =====");

            // Opciones del menú principal
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");

            int option = 0;

            // Validar la entrada del usuario con try-catch
            while (true) {
                try {
                    System.out.print("Seleccione una opción: ");
                    option = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    // Verificar si la opción está en el rango permitido
                    if (option >= 1 && option <= 3) {
                        break;
                    } else {
                        System.out.println("Por favor, ingrese un número entre 1 y 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    scanner.nextLine(); // Limpiar el buffer de entrada
                }
            }

            switch (option) {
                case 1:
                    // Llamar al método para iniciar sesión
                    login.logIn();
                    break;
                case 2:
                    // Llamar al método para registrarse
                    System.out.println("Registro de nuevo usuario");
                    userControls.createUser();
                    break;
                case 3:
                    // Salir del programa
                    exit = true;
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;
                default:
                    // Este caso nunca debería suceder debido a la verificación del rango
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
                    break;
            }
        }
        System.out.println("Programa finalizado.");
    }
    public static void main(String[] args) {
        start();
    }
}