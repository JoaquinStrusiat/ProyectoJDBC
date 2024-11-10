package models;
import java.util.Scanner;
import controllers.categoriesControls;

public class Product {
    private String nameProduct;            
    private String description;     
    private double price; 
    private int category;        

    private Product(String nameProduct, String description, double price, int category) {
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public static Product createProduct() {
        Product product;
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in); 
        
        while (true) {
            try {
                // Datos ingresados por el usuario
                System.out.print("Ingrese Nombre del producto: ");
                String nameProduct = scanner.nextLine();

                System.out.print("Ingrese descripcion del producto: ");
                String description = scanner.nextLine();

                System.out.print("Ingrese precio: ");
                Double price = scanner.nextDouble();
                scanner.nextLine(); 

                categoriesControls.readCategories();
                System.out.println("1 - Seleccionar una Categoria existente");
                System.out.println("2 - Crear una categoría");

                int category = 0;
                boolean pass = true;
                while (pass) {
                    System.out.print("Ingrese una opción: ");
                    String value = scanner.nextLine();
                    switch (value) {
                        case "1":
                            while (true) {
                                System.out.print("Ingrese el id de la Categoria: ");
                                int cat;
                                try{
                                    cat = scanner.nextInt();
                                    if (categoriesControls.validCategory(cat)) {
                                        category = cat;
                                        pass = false;
                                        break;
                                    } else {
                                        System.out.println("---Categoria no encontrada---");
                                    } 
                                } catch (Exception e){
                                    System.out.println("---Opcion no válida---");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        case "2":
                            category = categoriesControls.createCategory();
                            pass = false;
                            break;
                        default:
                            System.out.println("---Opción incorrecta---");
                            break;
                    }
                }
                product = new Product(nameProduct, description, price, category);
                break; 
            } catch (Exception e) {
                System.out.println("\nEntrada no válida, ingrese nuevamente los datos.");
                scanner.nextLine(); // Limpia el buffer en caso de excepción
            }
        }
        return product;
    }
    
    public String getnameProduct(){
        return nameProduct;
    }

    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public int getCategory() {
        return category;
    }

    public static void main(String[] args) {
        
    }
}

 