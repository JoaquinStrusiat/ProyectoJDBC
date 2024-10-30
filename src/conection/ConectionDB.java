package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import utils.env;

public class ConectionDB {
    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ConectionDB(String file){
        //Obtengo por parámetros las variables a usar
        HashMap<String, String> varList = env.getEnvVar(file);
        this.url = varList.get("dbUrl");
        this.user = varList.get("dbUsername");
        this.password = varList.get("dbPassword");
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e);
        }
    }

    public void closeConectionDB() {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }  
    }

    public ResultSet executeQuery(String query){
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error de consulta: " + e);
        }
        return resultSet;
    }

    public String executeUpdate(String query){
        try {
            int update = statement.executeUpdate(query);
            String updatetoString = Integer.toString(update);
            return "Número de actualizaciones: " + updatetoString;
        } catch (Exception e) {
            return "Error de actualizacion: " + e;
        }
    }


    public static void main(String[] args) {
        //Declaro la ruta de variables
        String routeEnv = "dev";
        //Declaro la consulta sql
        String query = "SELECT * FROM user";
        //Inicializo una conección con la ruta de variables a implementar
        ConectionDB db = new ConectionDB(routeEnv);
        //Manejo de la respuesta
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                String nombre = resultSet.getString("name");
                String dni = resultSet.getString("dni");
                String email = resultSet.getString("email");
                System.out.println("Nombre: " + nombre + " DNI: " + dni  + " Email: " + email);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        db.closeConectionDB();        
    }
}
