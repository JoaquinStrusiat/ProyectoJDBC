package controllers;

import conection.ConectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class historyControls {

    public static boolean createHistory() {
        Scanner scanner = new Scanner(System.in);
        ConectionDB db = new ConectionDB();
        boolean success = false;
        
        try {
            String query = "INSERT INTO history (product, price, unit, purchaseDate) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.executeChange(query);
            
            System.out.print("Ingrese el nombre del producto: ");
            String product = scanner.nextLine();
            
            System.out.print("Ingrese el precio: ");
            double price = scanner.nextDouble();
            
            System.out.print("Ingrese las unidades: ");
            int units = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            System.out.print("Ingrese la fecha del dia de la compra (YYYY/MM/DD): ");
            String dateStr = scanner.nextLine();
            // Convertir el formato de fecha con barras a formato con guiones
            dateStr = dateStr.replace("/", "-");
            java.sql.Date purchaseDate = java.sql.Date.valueOf(dateStr);
            
            stmt.setString(1, product);
            stmt.setDouble(2, price);
            stmt.setInt(3, units);
            stmt.setDate(4, purchaseDate);
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Registro creado con éxito");
                success = true;
            } else {
                System.out.println("No se pudo crear el registro");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al crear el registro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error en el formato de la fecha. Use el formato YYYY/MM/DD");
        } finally {
            db.closeConectionDB();
        }
        
        return success;
    }

// Método para leer todo el historial
public static void readHistory() {
    String query = "SELECT * FROM history";
    ConectionDB db = new ConectionDB();
    
    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-4s %-25s %-15s %-15s %-40s", 
                     "ID", "| Nombre del Producto", "| Precio", "| Unidades", "| Fecha de Compra");
    System.out.println("");
    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    
    try (ResultSet resultSet = db.executeQuery(query)) {
        while (resultSet.next()) {
            String output = String.format("%-4d | %-23s | $ %-12.2f | %-13d | %-38s",
                resultSet.getInt("idhistory"),
                resultSet.getString("product"),
                resultSet.getDouble("price"),
                resultSet.getInt("unit"),
                resultSet.getTimestamp("purchaseDate"));
            System.out.println(output);
        }
    } catch (SQLException e) {
        System.err.println("Error al leer el historial: " + e.getMessage());
    }
    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    db.closeConectionDB();
}


public static boolean updateHistory() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el ID del registro a modificar: ");
    int id = scanner.nextInt();
    
    System.out.println("\nSeleccione el campo a modificar:");
    System.out.println("1. Producto");
    System.out.println("2. Precio");
    System.out.println("3. Unidades");
    System.out.print("Opción: ");
    int option = scanner.nextInt();
    ConectionDB db = new ConectionDB();
    boolean success = false;
    
    try (PreparedStatement stmt = db.executeChange(buildUpdateQuery(option))) {
        switch (option) {
            case 1:
                System.out.print("Ingrese el nuevo producto: ");
                scanner.nextLine(); // Limpiar buffer
                String newProduct = scanner.nextLine();
                stmt.setString(1, newProduct);
                break;
            case 2:
                System.out.print("Ingrese el nuevo precio: ");
                double newPrice = scanner.nextDouble();
                stmt.setDouble(1, newPrice);
                break;
            case 3:
                System.out.print("Ingrese las nuevas unidades: ");
                int newUnits = scanner.nextInt();
                stmt.setInt(1, newUnits);
                break;
            default:
                System.out.println("Opción no válida");
                return false;
        }
        scanner.close();
        
        stmt.setInt(2, id);
        int rowsAffected = stmt.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Registro actualizado con éxito");
            success = true;
        } else {
            System.out.println("No se encontró el registro a actualizar");
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar el registro: " + e.getMessage());
    } finally {
        db.closeConectionDB();
    }
    return success;
    
}

private static String buildUpdateQuery(int option) {
    switch (option) {
        case 1:
            return "UPDATE history SET product = ? WHERE idhistory = ?";
        case 2:
            return "UPDATE history SET price = ? WHERE idhistory = ?";
        case 3:
            return "UPDATE history SET unit = ? WHERE idhistory = ?";
        default:
            return "";
    }
}
    // Método para eliminar un registro del historial
    public static boolean deleteHistory(int id) {
        String query = "DELETE FROM history WHERE id = ?";
        ConectionDB db = new ConectionDB();
        boolean success = false;

        try (PreparedStatement stmt = db.executeChange(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro eliminado con éxito");
                success = true;
            } else {
                System.out.println("No se encontró el registro a eliminar");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el registro: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        return success;
    }

    // Método para verificar si existe un registro
    public static boolean validHistory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del historial a verificar: ");
        int id = scanner.nextInt();
        
        String query = "SELECT 1 FROM history WHERE idhistory = ?";
        ConectionDB db = new ConectionDB();
        boolean exists = false;
    
        try (PreparedStatement stmt = db.executeChange(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                exists = resultSet.next();
                if (exists) {
                    System.out.println("El registro con ID " + id + " existe en el historial.");
                } else {
                    System.out.println("El registro con ID " + id + " no existe en el historial.");
                }
                scanner.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar si el registro existe: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        return exists;
    }
    
    public static void main(String[] args) {
        //createHistory();
        readHistory();
        //validHistory();
        //updateHistory();
        //deleteHistory(1);   

    }
}