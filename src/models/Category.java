package models;

import java.util.Scanner;

public class Category {
    private String name;
    private int category; // clave y clave foránea para conectar con tabla de productos
    private String description;

    private Category(String name, int category, String discription){
        this.name = name;
        this.category = category;
        this.description = discription;
    }
    
    public static Category createCategory(){
        Category cat;
        while(true){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ingrese el nombre de la Categoría a Crear: ");
                String catName = scanner.nextLine();

                System.out.println("Ingrese el ID de la categoría: ");
                int catID = scanner.nextInt();

                System.out.println("Ingrese descripción: ");
                String catDescription = scanner.nextLine();

                scanner.close();
                cat = new Category(catName, catID, catDescription);
                break;
            } catch (Exception e) {
                System.out.println("\nEntrada no valida, ingrese nuevamente los datos: ");
            }
        }
        return cat;
    }
