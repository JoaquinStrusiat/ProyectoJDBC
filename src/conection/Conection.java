package conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import utils.env;

public class Conection {
    public static void main(String[] args) {

        //Importo las variables de configuración
        HashMap<String, String> varList = env.getEnvVar("dev");

        /*
        Esta parte es solo para entendér el código, luego hay que eliminarla y pasar
        las variables directamente al conector
        */ 
        String url = varList.get("dbUrl");  
        String username = varList.get("dbUsername"); 
        String password = varList.get("dbPassword");

        System.out.println(url);
        System.out.println(username);
        System.out.println(password);

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
