package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conection.ConectionDB;

public class updateUser {

    // Método para actualizar los datos de un usuario
    public boolean updateUsuario(int id, String name, String lastName, String dni, String email) {
        String query = "UPDATE usuarios SET name = ?, last_name = ?, dni = ?, email = ? WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            // Asignamos los valores a los parámetros de la consulta
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setString(3, dni);
            stmt.setString(4, email);
            stmt.setInt(5, id);

            // Ejecutamos la actualización
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0; // Retorna true si se actualizó al menos una fila

        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }
public static void updateUser(String[] args) {
    // Llamar al método para agregar o eliminar un usuario
    updateUser();
}

}

