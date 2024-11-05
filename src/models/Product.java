package models;
import java.util.Scanner;

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

    public static Product createProduct(){
        Product product;
        while (true) {
            try {
                // Crear un objeto Scanner para leer datos desde el teclado
                Scanner scanner = new Scanner(System.in);
                // Datos ingresado por el usuario x teclado
                System.out.print("Ingrese Nombre del producto: ");
                String nameProduct = scanner.nextLine();

                System.out.print("Ingrese descripcion del producto: ");
                String description = scanner.nextLine();

                System.out.print("Ingrese precio: ");
                Double price = scanner.nextDouble();
                scanner.nextLine(); // Limpia el buffer

                System.out.print("Ingrese categoria del producto: ");
                int category = scanner.nextInt();  
                scanner.close();
                product = new Product(nameProduct, description, price, category);
                break;    
            } catch (Exception e) {
                System.out.println("\nEntrada no valida, ingrese nuevamente los datos: ");
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
}

 