package models;

import java.util.Scanner;

public class Category {
    private String name;

    private Category(String name){
        this.name = name;
    }

    public static Category createCategory(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
  
        System.out.print("Ingrese el nombre de la Categoría a Crear: ");
        String catName = scanner.nextLine();
        Category cat = new Category(catName);
        return cat;
    }


    public String getName() {
        return name;
    }

}
