package controllers;

import models.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.ConectionDB;

public class categoriesControls {
//Metodo para crear categoria
    public static void createCategory() {
        // Crear instancia de la categoría
        Category cat = Category.createCategory();  // Asumiendo que existe este método en Category
        
        // Consulta SQL para insertar la nueva categoría
        String query = "INSERT INTO categories (name) VALUES (?)";
        ConectionDB db = new ConectionDB();

        // Ejecutar la inserción usando PreparedStatement
        try (PreparedStatement stmt = db.executeChange(query)) {
            stmt.setString(1, cat.getName()); // Obtener el nombre de la categoría
            int rowsInserted = stmt.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("¡Categoría agregada exitosamente!");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar la categoría: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
    }

    // Método para leer y mostrar todas las categorías
    public static void readCategories() {
        String query = "SELECT * FROM categories";
        ConectionDB db = new ConectionDB();

        System.out.println("Categorías:");
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + " - Nombre: " + name);
                //System.out.printf("%-4d %-15s%n", id, name);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer las categorías: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
    }

public static void main(String[] args) {
    //categoriesControls.createCategory();
    categoriesControls.readCategories();
}
}

/*
 *  public static int getId(String nameCategor){
        return 1;
    }
 */    

