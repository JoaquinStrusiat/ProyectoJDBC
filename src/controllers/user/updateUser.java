package controllers.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conection.ConectionDB;

public class updateUser {

    // Método para actualizar los datos de un usuario
    public boolean updateUsuario(int id, String name, String lastName, String dni, String email) {

        String sql = "UPDATE FROM user SET name = ?, last_name = ?, dni = ?, email = ? WHERE id = ?";
        ConectionDB db = new ConectionDB();

        try (Connection connection = db.getConnection(); // Get a connection
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Asignamos los valores a los parámetros de la consulta
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, dni);
            statement.setString(4, email);
            statement.setInt(5, id);

            // Ejecutamos la actualización
            int filasActualizadas = statement.executeUpdate();
            return filasActualizadas > 0; // Retorna true si se actualizó al menos una fila

        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }

    }

}