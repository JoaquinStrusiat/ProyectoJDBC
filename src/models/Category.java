package models;

import java.util.Scanner;

public class Category {
    private String name;

    private Category(String name){
        this.name = name;
    }

    public static Category createCategory(){
        Category cat;
        while(true){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese el nombre de la Categoría a Crear: ");
                String catName = scanner.nextLine();

                scanner.close();
                cat = new Category(catName);
                break;
            } catch (Exception e) {
                System.out.println("\nEntrada no valida, ingrese nuevamente los datos: ");
            }
        }
        return cat;
    }

    public String getName() {
        return name;
    }

}
