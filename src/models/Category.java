package models;

import java.util.Scanner;

public class Category {
    private String name;

    private Category(String name){
        this.name = name;
    }
    
    public static Category createCategory(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la Categoría a Crear: ");
        String catName = scanner.nextLine();
        Category cat = new Category(catName);
        scanner.close();
        return cat;
    }

    public String getName() {
        return name;
    }
}
