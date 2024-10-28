package conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://autorack.proxy.rlwy.net:40787/railway";  
        String username = "cliente"; 
        String password = "1234";

        String query = "SELECT * FROM user";

        try (
            Connection conect = DriverManager.getConnection(url, username, password);
            Statement statement = conect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {
            System.out.println("Conexion establecida: " + conect);

            while (resultSet.next()) {
                String nombre = resultSet.getString("name");
                String dni = resultSet.getString("dni");
                String email = resultSet.getString("email");
                System.out.println("Nombre: " + nombre + " DNI: " + dni  + " Email: " + email);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }     
    }
}
