package views;
import controllers.productControls;
import controllers.shoppingControls;
import controllers.userControls;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Views {

    // Método para mostrar el menú CRUD
    public static void showCRUDMenu(int userID) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        // Menú CRUD después de la autenticación
        do {
            System.out.println("\n========= Menú de CRUD =========");
            System.out.println("1. Ver productos");
            System.out.println("2. Agregar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Actualizar mis Datos");
            System.out.println("6. Comprar un producto");
            System.out.println("7. Ver historial de compras");
            System.out.println("8. Cerrar Sesion");
            System.out.print("Seleccione una opción: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        productControls.readProducts();
                        break;
                    case 2:
                        productControls.createProduct(userID);
                        break;
                    case 3:
                        productControls.updateProduct(userID);
                        break;
                    case 4:
                        productControls.deleteProduct(userID);
                        break;
                    case 5:
                        userControls.updateUser(userID);
                        break;
                    case 6:
                        shoppingControls.createShop(userID);
                        break;
                    case 7:
                        shoppingControls.readShopHistory(userID);
                        break;
                    case 8:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("---Opción fuera de rango---");
                        break;    
                }

            } catch (InputMismatchException e) {
                System.out.println("---Opcion no válida---");
                scanner.nextLine();
            }

            System.out.println();
        } while (option != 8);
    }
}
