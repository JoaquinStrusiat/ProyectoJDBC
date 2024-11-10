package controllers;

import conection.ConectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
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
            
        ConectionDB db = new ConectionDB();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-25s %-15s %-20s %-20s %-40s ", "Id", "| Nombre del Producto", "| Categoria", "| Precio unitario", "| Vendedor",  "| Descripcion");
        System.out.println("");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        String query = "SELECT * FROM products";
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

    //Cambiar todo el modify, tambien nombre.
    //Llamar al read para mostrar que eliminar.
    //Ocupar la logica de update user para modificar algun dato. 
    // Método para modificar (Modify) un producto por nombre
    public static void updateProduct() {
        productControls.readProductsUser(16);
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        ConectionDB db = new ConectionDB();
        System.out.println("\nSeleccione el dato que desea actualizar:");
        System.out.println("1 - Nombre del producto");
        System.out.println("2 - Descripcion");
        System.out.println("3 - Precio");
        System.out.println("4 - Categoria");
        System.out.println("5 - Salir");
        boolean bandera = true;
        while (bandera) {
            int option;
            while (true) {
                
                System.out.print("Ingrese una opción: ");
                try {
                    option = scanner.nextInt();
                    break; // Sale del bucle si la entrada es numérica
                } catch (Exception e) {
                    System.out.println("---Opcion no válida---");
                    scanner.nextLine(); // Limpia el buffer del scanner
                }
            }
            scanner.nextLine();     
            
            switch (option) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre del producto: ");
                    String name = scanner.nextLine();
                    String query = "UPDATE products SET name = ? WHERE id = ?"; 
                    // Elimina la coma y corrige "UPDATE FROM"
                    try (PreparedStatement stmtUser = db.executeChange(query)) {
                        stmtUser.setString(1, name);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error de actualización: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la nueva descripcion del producto: ");
                    String description = scanner.nextLine();

                    String query2 = "UPDATE products SET description = ?";
                    try (PreparedStatement stmtUser = db.executeChange(query2)){ 
                        stmtUser.setString(1, description);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");
                        }
                    } catch (Exception e) {
                        System.out.println("Error de actualización: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el nuevo precio del producto: ");
                    double price = scanner.nextDouble();
                    //scanner.nextLine(); // Limpiar el salto de línea
                    String query3 = "UPDATE products SET price = ?";
                    try (PreparedStatement stmtUser = db.executeChange(query3)){ 
                        stmtUser.setDouble(1, price);

                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");
                        }   
                    } catch (Exception e) {
                        System.out.println("Error de actualización en dni: " + e.getMessage());
                    }
                    break;

                case 4:
                    categoriesControls.readCategories();
                    System.out.println("1 - Seleccionar una Categoria existente");
                    System.out.println("2 - Crear una categoría");
                    int category = 0;

                    boolean pass = true;
                    while (pass) {
                        System.out.print("Ingrese una opción: ");
                        String value = scanner.nextLine();
                        switch (value) {
                            case "1":
                                while (true) {
                                    System.out.print("Ingrese el id de la Categoria: ");
                                    int cat;
                                    try{
                                        cat = scanner.nextInt();
                                        if (categoriesControls.validCategory(cat)) {
                                            category = cat;
                                            pass = false;
                                            break;
                                        } else {
                                            System.out.println("---Categoria no encontrada---");
                                        } 
                                    } catch (Exception e){
                                        System.out.println("---Opcion no válida---");
                                        scanner.nextLine();
                                    }
                                }
                                break;
                            case "2":
                                category = categoriesControls.createCategory();
                                pass = false;
                                break;
                            default:
                                System.out.println("---Opción incorrecta---");
                                break;
                        }
                    }
                    String query5 = "UPDATE products SET category = ?";
                    try (PreparedStatement stmtUser = db.executeChange(query5)){ 
                        stmtUser.setInt(1, category);
                        int row = stmtUser.executeUpdate();
                        System.out.println("------Categoria actualizada exitosamente------");
                    } catch (SQLException e) {
                        System.out.println("Error de actualización: " + e.getMessage());
                    }
                    break;
                case 5:
                    bandera = false;
                    break;
                default:
                    System.out.println("---Opción fuera de rango---");
                    break;
            }
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


public static void main(String[] args) {
    createProduct(16);
    //updateProduct();   
}
}


