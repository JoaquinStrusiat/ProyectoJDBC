import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        //datos de conección
        String url = "jdbc:mysql://autorack.proxy.rlwy.net:40787/railway";  
        String username = "root"; 
        String password = "FKzXlOxTSRDTjfQvgglaYIFrDgWNzrfi";
        //Consulta a realizar
        String query = "SELECT * FROM user";

        try (
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query); 
            ){
            System.out.println("Conexion establecida: " + connection);

            
            while (resultSet.next()) {
                System.out.println(resultSet.getType());
                /* 
                // Obtener los datos de cada columna
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String dni = resultSet.getString("dni");

                // Mostrar los datos
                System.out.println("ID: " + id + 
                                   ", Name: " + name + 
                                   ", Last Name: " + lastName + 
                                   ", Email: " + email + 
                                   ", Country: " + country + 
                                   ", City: " + city + 
                                   ", DNI: " + dni);
                */ 
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}