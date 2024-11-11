package controllers;

import models.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.ConectionDB;

public class categoriesControls {

    //Metodo para crear categoria
    public static int createCategory() {
        // Crear instancia de la categoría
        Category cat = Category.createCategory();  
        
        // Consulta SQL para insertar la nueva categoría
        String query = "INSERT INTO categories (name) VALUES (?)";
        ConectionDB db = new ConectionDB();

        // Ejecutar la inserción usando PreparedStatement
        int idCategory = 0;
        try (PreparedStatement stmt = db.executeChange(query);) {
            stmt.setString(1, cat.getName()); // Obtener el nombre de la categoría
            stmt.executeUpdate();

            try (ResultSet res = stmt.getGeneratedKeys()) {
                if(res.next()){
                    idCategory = res.getInt(1);
                    System.out.println("\tCategoría creada con exito");
                    return idCategory;
                } 
            } catch (SQLException e) {
                System.err.println("Error al obtener el id de la categoría: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar la categoría: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        return idCategory;
    }

    // Método para leer y mostrar todas las categorías
    public static void readCategories() {
        String query = "SELECT * FROM categories";
        ConectionDB db = new ConectionDB();
        System.out.println("--------------------");
        System.out.printf("%-4s %-15s","ID", "Nombre");
        System.out.println("");
        System.out.println("--------------------");
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String row = String.format("%-4s %-15s", id, name);
                System.out.println(row);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer las categorías: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        System.out.println("--------------------");
    }

    public static boolean validCategory(int id) {
        String query = "SELECT 1 FROM categories WHERE id = ?";
        ConectionDB db = new ConectionDB();
        boolean exists = false;
    
        try (PreparedStatement stmt = db.executeChange(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                exists = resultSet.next(); // Será true si hay al menos un registro
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar si la categoría existe: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        return exists;
    }
}
  
