package models;
import java.util.Scanner;

public class Product {
    private String nameProduct;            // Nombre del producto
    private String description;     // Descripción del producto
    private double price; 
    private String category;        //Categoria  agregado FK 

    public Product(String nameProduct, String description, double price, String category) {
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public static Product getProduct(){
        // Crear un objeto Scanner para leer datos desde el teclado
        Scanner scanner = new Scanner(System.in);
        // Datos ingresado por el usuario x teclado
        System.out.println("Ingrese Nombre del producto");
        String nameProduct = scanner.nextLine();

        System.out.print("Ingrese descripcion del producto");
        String description = scanner.nextLine();

        scanner.next(); // Limpia el buffer
        System.out.print("Ingrese precio ");
        Double price = scanner.nextDouble();

        System.out.print("Ingrese categoria del producto");
        String category = scanner.nextLine();  
        
        scanner.close();

        Product product = new Product(nameProduct, description, price, category);
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
        public String getCategory() {
            return category;
        }
        
}

 