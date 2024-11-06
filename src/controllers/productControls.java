package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conection.ConectionDB;
import models.Product;
import models.User;

public class productControls {

    // Declaramos los metodos del CRUD 

    // METODO CREATE (POST)

    // Método para agregar (Add) un nuevo producto
    public static void createProduct(User user) {
        Product newProduct = Product.createProduct();
        int dni = user.getDni();
        //// Consulta SQL para insertar el usuario
        String query = "INSERT INTO product (name, description, price, category, id_user) VALUES (?, ?, ?, ?, ?)";

        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();

        // Ejecutar la inserción usando PreparedStatement
        try (
            //obtenemos el id del usuario
            ResultSet res = db.executeQuery("SELECT id FROM railway.user WHERE dni = " + dni);
            PreparedStatement stmt = db.executeChange(query);
            ) {
            stmt.setString(1, newProduct.getnameProduct());
            stmt.setString(2, newProduct.getDescription());
            stmt.setDouble(3, newProduct.getPrice());
            //printeo las categorias con un -- categoryControls.readCategories()
            // switch crean las dos primeras variables... 
                //1- selecionar un id categoria existente
                //stmt.setInt(4, id_category);
                //2- crear una categoría nueva -- String nameCategor = categoriesControls.createCategory()
                // Agregarla a la base de datos --  int id_category = categoriesControls.getId(nameCategor)
                //stmt.setInt(4, id_category);
            stmt.setInt(5, res.getInt("id"));


            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Producto agregado exitosamente!");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
    }

    // Método READ (todos los productos)
    public static void readProducts() {
        String query = "SELECT * FROM products";
        ConectionDB db = new ConectionDB();

        System.out.printf("%-4s %-15s %-17s %-40s ", "Id", "Nombre", "Precio", "Descripcion");
        System.out.println("");
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                //String category = resultSet.getString("category")
                String output = String.format("%-4s %-15s $ %-15s %-40s", productID, name, price, description);
                System.out.println(output);
            }
        } catch (Exception e) {
            System.err.println("Error al leer los productos: " + e.getMessage());
        }
        db.closeConectionDB();
    }
    
    // Método para eliminar (Delete) un producto por NOMBRE
    public static void deleteProduct() {
        // Crear un objeto Scanner para leer el DNI desde el teclado
        Scanner scanner = new Scanner(System.in);

        // Pedir el nombre del producto a eliminar
        System.out.print("Ingrese el nombre del producto que desea eliminar: ");
        String name = scanner.nextLine();

        // Consulta SQL para eliminar el producto por nombre
        String query = "DELETE FROM products WHERE name = ?";

        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();

        // Ejecutar la eliminación usando PreparedStatement
        try (PreparedStatement stmt = db.executeChange(query)) {
            
            // Asignar el valor del parámetro
            stmt.setString(1, name);

            // Ejecutar la eliminación
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("¡Producto eliminado exitosamente!");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        scanner.close();
    }

   
    /* 
    
     *  // Método para modificar (Modify) un producto por nombre
    public static void modifyProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del producto que desea modificar: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese la nueva descripción del producto: ");
        String newDescription = scanner.nextLine();

        System.out.print("Ingrese el nuevo precio del producto: ");
        double newPrice = scanner.nextDouble();

        String query = "UPDATE products SET description = ?, price = ? WHERE name = ?";
        ConectionDB db = new ConectionDB();

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, newDescription);
            stmt.setDouble(2, newPrice);
            stmt.setString(3, name);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("¡Producto actualizado exitosamente!");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        scanner.close();
    }
   */

    public static void main(String[] args) {
        productControls.readProducts();
    }
}