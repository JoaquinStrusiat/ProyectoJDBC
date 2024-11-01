package models;

public class Product {
    private int productID;          // Identificador único del producto
    private String name;            // Nombre del producto
    private String description;     // Descripción del producto
    private double price; 
    private String category;        //Categoria  agregado FK
    
    

    
    public Product(int productID, String name, String description, double price, String category) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    public int getProductID() {
        return productID;
    }
    public String getName() {
        return name;
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

 