package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conection.ConectionDB;

public class productControls {
/*
 * // Método para leer (Read) todos los productos
    public static void readProducts() {
        String query = "SELECT * FROM products";
        ConectionDB db = new ConectionDB();

        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");

                System.out.println("ID: " + productID + " Nombre: " + name + " Descripción: " + description + " Precio: " + price);
            }
        } catch (Exception e) {
            System.err.println("Error al leer los productos: " + e.getMessage());
        }
        db.closeConectionDB();
    }

    // Método para agregar (Add) un nuevo producto
    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese la descripción del producto: ");
        String description = scanner.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double price = scanner.nextDouble();

        String query = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";
        ConectionDB db = new ConectionDB();

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Producto agregado exitosamente!");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        scanner.close();
    }
 */
    

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
    // Método para eliminar (Delete) un producto por nombre
    public static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del producto que desea eliminar: ");
        String name = scanner.nextLine();

        String query = "DELETE FROM products WHERE name = ?";
        ConectionDB db = new ConectionDB();

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, name);

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

   

    // Método principal para ejecutar pruebas de los métodos CRUD
    public static void main(String[] args) {
        // Ejemplo de uso
       // addProduct();
       // readProducts();
       // modifyProduct();
        deleteProduct();
}
}