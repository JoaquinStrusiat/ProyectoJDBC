package conection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;


import utils.env;

public class ConectionDB {
    //En la linea de abajo defino la carpeta con las variables de entorno a utilizar
    private String route = "dev";
    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement prepareStatement;


    public ConectionDB(){
        //Obtengo por parámetros las variables a usar
        HashMap<String, String> varList = env.getEnvVar(route);
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

    public PreparedStatement executeChange(String query) {
        try {
            prepareStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Error de consulta: " + e);
        }
        return prepareStatement;
    }
}
