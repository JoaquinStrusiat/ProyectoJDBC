package views;
import utils.login;
import controllers.userControls;
import java.util.Scanner;

public class Login {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
 
        while (!exit) {
            System.out.println("\n===== Bienvenido =====");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            
            System.out.print("Ingrese una opción: ");  
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    // Llamar al método para iniciar sesión
                    login.logIn();
                    break;
                case "2":
                    // Llamar al método para registrarse
                    System.out.println("\nRegistro de nuevo usuario: ");
                    userControls.createUser();
                    break;
                case "3":
                    // Salir del programa
                    exit = true;
                    System.out.println("\n==== ¡Hasta luego! ====\n");
                    break;
                default:
                    System.out.println("---Opcion no válida---");
                    break;
            }  
        }
        scanner.close();
    }
}