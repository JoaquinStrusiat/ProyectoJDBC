package views;

import java.util.InputMismatchException;
import java.util.Scanner;
import controllers.productControls;

public class Views {
//falta login y menu de tablas
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        do {
            System.out.println("========= Menú de CRUD =========");
            System.out.println("1. Ver productos");
            System.out.println("2. Modificar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Modificar producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); 

                // verifico que la opción esté en el rango de 1 a 5
                if (option < 1 || option > 5) {
                    System.out.println("Opción fuera de rango. Por favor, ingrese un número entre 1 y 4.");
                    option = -1;
                }

                switch (option) {
                    case 1:
                        productControls.readProducts();
                        break;

                    case 2:
                        productControls.modifyProduct();
                        break;

                    case 3:
                        productControls.deleteProduct();
                        break;

                    case 4:
                        productControls.modifyProduct();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        break;
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); 
            }

            System.out.println();
        } while (option != 5);

        scanner.close();
    }
}
