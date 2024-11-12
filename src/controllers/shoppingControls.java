package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conection.ConectionDB;
import models.Shopping;

public class shoppingControls {

    public static void createShop(int idUser){
        Shopping shop = Shopping.createShop(idUser);
        String query = "INSERT INTO shopping (id_user, product_name, unit, unit_price, total_price, date) VALUES (?, ?, ?, ?, ?, ?)";
        ConectionDB db = new ConectionDB();
        try (PreparedStatement stmt = db.executeChange(query);) {
            stmt.setInt(1, idUser);
            stmt.setString(2, shop.getProduct_name());
            stmt.setInt(3, shop.getUnit());
            stmt.setDouble(4, shop.getUnit_price());
            stmt.setDouble(5, shop.getTotal_price());
            stmt.setString(6, shop.getDate());
            int row = stmt.executeUpdate();
            if (row > 0) {
                System.out.println("---¡Compra realizada con exito!---");
            } else {
                System.out.println("---¡Compra no realizada!---");
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la compra: " + e.getMessage());
        } finally {
            db.closeConectionDB();
        }
        

    }

    public static void readShopHistory(int idUser){
        String query = "SELECT * FROM shopping WHERE id_user = " + idUser;
        ConectionDB db = new ConectionDB();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-35s %-24s %-20s %-20s %-20s", "Id", "| Nombre del Producto", "| Unidades Compradas", "| Precio unitario", "| Precio total",  "| Fecha de Compra");
        System.out.println("");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        boolean data = false;
        try (ResultSet resultSet = db.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String product_name = resultSet.getString("product_name");
                int unit = resultSet.getInt("unit");
                double unit_price = resultSet.getDouble("unit_price");
                double total_price = resultSet.getDouble("total_price");
                String date = resultSet.getString("date");
    
                String output = String.format("%-4s | %-33s | %-22s | %-18s | %-18s | %-18s", id, product_name, unit, unit_price, total_price, date);
                System.out.println(output);
                data = true;
            }
            if (!data) {
                System.out.println("-------------------------------------------------------- LISTA VACIA --------------------------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error al leer los productos: " + e.getMessage());
        } finally {
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            db.closeConectionDB();   
        }
    }
}
