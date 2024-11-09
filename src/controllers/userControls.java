package controllers;
import conection.ConectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import models.User;

public class userControls {
    
    // Metodo Create
    public static void createUser() {
        User newUser = User.createUser();
    
        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();

        // Declaro la query
        String query = "INSERT INTO users (name, last_name, email, country, city, dni, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        // Ejecutar la inserción usando PreparedStatement
        try (PreparedStatement stmtUser = db.executeChange(query)) {
            // guardo al usuario
            stmtUser.setString(1, newUser.getName());
            stmtUser.setString(2, newUser.getLast_name());
            stmtUser.setString(3, newUser.getEmail());
            stmtUser.setString(4, newUser.getCountry());
            stmtUser.setString(5, newUser.getCity());
            stmtUser.setInt(6, newUser.getDni());
            stmtUser.setString(7, newUser.getPassword());
            int row = stmtUser.executeUpdate();    
            if (row > 0){
                System.out.println("------Usuario creado correctamente------");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
    }
    

    //Metodo Read
    public static void readUsers(){
        //Declaro la query
        String query = "SELECT * FROM users";
        //Inicializo una conección con la ruta de variables a implementar
        ConectionDB db = new ConectionDB();
        //Head de la tabla
        System.out.printf("%-3s %-20s %-15s %-15s %-20s %-30s %-20s", "ID","Nombre", "Apellido", "DNI", "Pais", "Ciudad","Email");
        System.out.println("");
        //Manejo de la respuesta
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String dni = resultSet.getString("dni");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String email = resultSet.getString("email");
                String output = String.format("%-3s %-20s %-15s %-15s %-20s %-30s %-20s", id, name, lastName, dni, country, city ,email);
                System.out.println(output);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        db.closeConectionDB();
    }

    //Metodo delete
    public static void deleteUserById() {
        //Muestro por pantalla los usuarios para selecionar por id el que se quiera eliminar
        userControls.readUsers();

        int id;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("\nIngrese el ID del usuario a eliminar: ");
                id = scanner.nextInt();
                scanner.close();
                break;
            } catch (Exception e) {
                System.out.println("\nEntrada no valida: ");
            }
        }

        // Consulta SQL para eliminar el usuario por ID
        String query = "DELETE FROM users WHERE id = ?";

        // Crear una instancia de la conexión
        ConectionDB db = new ConectionDB();

        // Ejecutar la eliminación usando PreparedStatement
        try (PreparedStatement stmt = db.executeChange(query)) {
            // Asignar el valor del parámetro
            stmt.setInt(1, id);

            // Ejecutar la eliminación
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("¡Usuario eliminado exitosamente!");
            } else {
                System.out.println("No se encontró un usuario con el ID especificado");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
    }

    public static void updateUser(int id){
        Scanner scanner = new Scanner(System.in);
        
        
        ConectionDB db = new ConectionDB();
        boolean bandera = true;
        while (bandera) {
            
            System.out.println("\nSeleccione el dato que desea actualizar:");
            System.out.println("1 - Nombre");
            System.out.println("2 - Apellido");
            System.out.println("3 - DNI");
            System.out.println("4 - Email");
            System.out.println("5 - País");
            System.out.println("6 - Ciudad");
            System.out.println("7 - Contraseña");
            System.out.println("8 - Salir");

            int option;
            while (true) {
                System.out.print("Ingrese una opción: ");
                try {
                    option = scanner.nextInt();
                    break; // Sale del bucle si la entrada es numérica
                } catch (Exception e) {
                    System.out.println("Ingrese un valor numérico [1 - 8]");
                    scanner.nextLine(); // Limpia el buffer del scanner
                }
            }
            scanner.nextLine();     
            
            switch (option) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nombre = scanner.nextLine();
                    String query = "UPDATE users SET name = ? WHERE id = ?"; 
                    // Elimina la coma y corrige "UPDATE FROM"
                    try (PreparedStatement stmtUser = db.executeChange(query)) {
                        stmtUser.setString(1, nombre);
                        stmtUser.setInt(2, id);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");}
                    } catch (SQLException e) {
                        System.out.println("Error de actualización: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el nuevo apellido: ");
                    String apellido = scanner.nextLine();
                    String query2 = "UPDATE users SET last_name = ? WHERE id = ?";
                    try (PreparedStatement stmtUser = db.executeChange(query2)){ 
                        stmtUser.setString(1, apellido);
                        stmtUser.setInt(2, id);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");}
                        
                    } catch (Exception e) {
                        System.out.println("Error de actualización: " + e.getMessage());
                    }

                    break;
                case 3:
                    System.out.print("Ingrese el nuevo DNI: ");
                    int dni = scanner.nextInt();
                    //scanner.nextLine(); // Limpiar el salto de línea
                    String query3 = "UPDATE users SET dni = ? WHERE id = ?";
                    try (PreparedStatement stmtUser = db.executeChange(query3)){ 
                        stmtUser.setInt(1, dni);
                        stmtUser.setInt(2, id);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");}
                        
                    } catch (Exception e) {
                        System.out.println("Error de actualización en dni: " + e.getMessage());
                    }

                    
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo email: ");
                    String email = scanner.nextLine();
                    String query4 = "UPDATE users SET email = ? WHERE id = ?"; 
                    // Elimina la coma y corrige "UPDATE FROM"
                    try (PreparedStatement stmtUser = db.executeChange(query4)) {
                        stmtUser.setString(1, email);
                        stmtUser.setInt(2, id);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");}
                    } catch (SQLException e) {
                        System.out.println("Error de actualización de email: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el nuevo país: ");
                    String country = scanner.nextLine();
                    
                    String query5 = "UPDATE users SET country = ? WHERE id = ?"; 
                    // Elimina la coma y corrige "UPDATE FROM"
                    try (PreparedStatement stmtUser = db.executeChange(query5)) {
                        stmtUser.setString(1, country );
                        stmtUser.setInt(2, id);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");}
                    } catch (SQLException e) {
                        System.out.println("Error de actualización de pais: " + e.getMessage());
                    }

                    break;
                    
                case 6:
                    System.out.print("Ingrese la nueva ciudad: ");
                    String city = scanner.nextLine();

                    String query6 = "UPDATE users SET city = ? WHERE id = ?"; 
                    // Elimina la coma y corrige "UPDATE FROM"
                    try (PreparedStatement stmtUser = db.executeChange(query6)) {
                        stmtUser.setString(1, city);
                        stmtUser.setInt(2, id);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");}
                    } catch (SQLException e) {
                        System.out.println("Error de actualización de ciudad: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("Ingrese la nueva contraseña: ");
                    String contrasena = scanner.nextLine();
                    
                    String query7 = "UPDATE users SET password = ? WHERE id = ?"; 
                    // Elimina la coma y corrige "UPDATE FROM"
                    try (PreparedStatement stmtUser = db.executeChange(query7)) {
                        stmtUser.setString(1, contrasena);
                        stmtUser.setInt(2, id);
                        int row = stmtUser.executeUpdate();
                        if (row > 0 ){
                            System.out.println("------Actualización exitosa------");}
                    } catch (SQLException e) {
                        System.out.println("Error de actualización de la contrasena: " + e.getMessage());
                    }


                    break;

                case 8:
                    bandera = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        userControls.updateUser(11);
    }
} 