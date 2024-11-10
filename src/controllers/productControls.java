package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conection.ConectionDB;
import models.Product;

public class productControls {

    // Declaramos los metodos del CRUD 
    public static void createProduct(int idUser) {
        System.out.println("\nCreacion un Producto: ");
        Product newProduct = Product.createProduct();
        //// Consulta SQL para insertar el usuario
        String query = "INSERT INTO products (name, description, price, category, id_user) VALUES (?, ?, ?, ?, ?)";

        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();

        // Ejecutar la inserción usando PreparedStatement
        try (PreparedStatement stmt = db.executeChange(query);) {
            stmt.setString(1, newProduct.getnameProduct());
            stmt.setString(2, newProduct.getDescription());
            stmt.setDouble(3, newProduct.getPrice());
            stmt.setInt(4, newProduct.getCategory());
            stmt.setInt(5, idUser);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("---¡Producto agregado exitosamente!---");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
    }

    // Método READ (todos los productos)
    public static void readProducts() {
        String query = "SELECT * FROM product_details";
        ConectionDB db = new ConectionDB();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-25s %-15s %-20s %-20s %-40s ", "Id", "| Nombre del Producto", "| Categoria", "| Precio unitario", "| Vendedor",  "| Descripcion");
        System.out.println("");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int productID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category_name = resultSet.getString("category_name");
                double price = resultSet.getDouble("price");
                String user_name = resultSet.getString("user_name");
                String description = resultSet.getString("description");
                //String category = resultSet.getString("category")
                String output = String.format("%-4s | %-23s | %-13s | $ %-16s | %-18s | %-38s", productID, name, category_name, price, user_name, description);
                System.out.println(output);
            }
        } catch (Exception e) {
            System.err.println("Error al leer los productos: " + e.getMessage());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        db.closeConectionDB();
    }

    // Método READ productos del usuario
    public static boolean readProductsUser(int idUser) {
        String query = "SELECT * FROM product_details WHERE user_name = (SELECT name FROM users WHERE id = " + idUser + ")" ;
        ConectionDB db = new ConectionDB();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-25s %-15s %-20s %-20s %-40s ", "Id", "| Nombre del Producto", "| Categoria", "| Precio unitario", "| Vendedor",  "| Descripcion");
        System.out.println("");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        boolean data = false;
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int productID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category_name = resultSet.getString("category_name");
                double price = resultSet.getDouble("price");
                String user_name = resultSet.getString("user_name");
                String description = resultSet.getString("description");
    
                String output = String.format("%-4s | %-23s | %-13s | $ %-16s | %-18s | %-38s", productID, name, category_name, price, user_name, description);
                System.out.println(output);
                data = true;
            }
            if (!data) {
                System.out.println("------------------------------------------------ LISTA VACIA ---------------------------------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error al leer los productos: " + e.getMessage());
        } finally {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
            db.closeConectionDB();   
        }
       return data;
    }
    
    // Método para eliminar productos segun el usuario
    public static void deleteProduct(int idUser) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        if (productControls.readProductsUser(idUser)) {
            System.out.println("1 - Eliminar un producto");
            System.out.println("2 - Salir");
            boolean ban = true;
            while (ban) {
                System.out.print("Opcion: ");
                String op = scanner.nextLine();
                switch (op) {
                    case "1":
                        int idProduct;
                        while (true) {
                            System.out.print("Ingrese el id del producto a eliminar: ");
                            try {
                                idProduct = scanner.nextInt();  
                                scanner.nextLine();     
                                break;
                            } catch (Exception e) {
                                System.out.println("---Opcion no válida---");
                                scanner.nextLine();
                            }
                        }
                        String query = "DELETE FROM products WHERE id = ? AND id_user = ?";
                        ConectionDB db = new ConectionDB();
                        try (PreparedStatement stmt = db.executeChange(query)) {
                            stmt.setInt(1, idProduct);
                            stmt.setInt(2, idUser);
                            int rowsDeleted = stmt.executeUpdate();
                            if (rowsDeleted > 0) {
                                System.out.println("---¡Producto eliminado exitosamente!---");
                            } else {
                                System.out.println("---Producto no encontrado---");
                            }
                        } catch (SQLException e) {
                            System.err.println("Error al eliminar el producto: " + e.getMessage());
                        } finally {
                            db.closeConectionDB();
                        }
                        break;
                    case "2":
                        ban = false;
                        break;
                    default:
                        System.out.println("---Opcion no válida---");
                        break;
                }
            }
        }
    }

    // Método para modificar (Modify) un producto por nombre
    public static void modifyProduct() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre actual del producto que desea modificar: ");
        String currentName = scanner.nextLine();

        System.out.print("Ingrese el nuevo nombre del producto: ");
        String newName = scanner.nextLine();

        System.out.print("Ingrese la nueva descripción del producto: ");
        String newDescription = scanner.nextLine();

        System.out.print("Ingrese el nuevo precio del producto: ");
        double newPrice = scanner.nextDouble();

        // Consulta SQL para actualizar el producto por nombre
        String query = "UPDATE products SET name = ?, description = ?, price = ? WHERE name = ?";
        ConectionDB db = new ConectionDB();

        // Ejecutar la actualización usando PreparedStatement
        try (PreparedStatement stmt = db.executeChange(query)) {
            stmt.setString(1, newName);
            stmt.setString(2, newDescription);
            stmt.setDouble(3, newPrice);
            stmt.setString(4, currentName);

            // Ejecutar la actualización
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
        
    }

    public static boolean validProduct(int id) {
        String query = "SELECT 1 FROM products WHERE id = ?";
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