package conection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import utils.env;

public class ConectionDB {
    //Cambiar esta línea para definir que variables usar
    private static String fileEnv = "dev";
    //Linea de arriba
    

    public static Statement createConection(){
        HashMap<String, String> varList = env.getEnvVar(fileEnv);
        try (
            Connection conect = DriverManager.getConnection(varList.get("dbUrl"), varList.get("dbUser"), varList.get("dbPassword"));
            Statement statement = conect.createStatement();
            ) {
                System.out.println("Conexion establecida: " + conect);
                return statement;  
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) {

        String query = "SELECT * FROM user";

        try (
            Statement stc = ConectionDB.createConection();
            ResultSet resultSet = stc.executeQuery(query);
            ) {
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
