package models;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;
import javax.print.DocFlavor.STRING;
import javax.swing.plaf.synth.SynthSeparatorUI;

import conection.ConectionDB;
import controllers.productControls;
import controllers.shopingHistoryControls;

public class ShopingHistory{

    private int id_user ;
    private STRING product_name; //FK
    private int unit;
    private double unit_price ; 
    private double total_price;
    private Date purchaseDate;
    
    public ShopingHistory(int id_user, STRING product_name, int unit, double unit_price, double total_price, Date purchaseDate) {
        this.id_user = id_user;
        this.product_name = product_name;
        this.unit = unit;
        this.unit_price = unit_price;
        this.total_price = total_price;
        this.purchaseDate = purchaseDate;
    }
    
    public int getId_user() {
        return id_user;
    }

    public STRING getProduct_name() {
        return product_name;
    }

    public int getUnit() {
        return unit;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public static ShopingHistory createshoppingHistory(int id_user){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in); 
        productControls.readProducts();
        ShopingHistory buyProduct ;  
        while (true){
            System.out.print("ingrese el id del producto a comprar: ");
            int opt ; 
            try { 
                opt = scanner.nextInt()
                if (productControls.validProduct(opt)){
                
                 String query = "SELECT * FROM products WHERE id = " + opt;
                ConectionDB db = new ConectionDB();   
                try (ResultSet res = db.executeQuery(query)) {

                    String query = "SELECT * FROM products WHERE id = (SELECT name FROM product WHERE id = " + idUser + ")" ;
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%-4s %-25s %-15s %-20s %-20s %-40s %-20s  ", "Id", "| Nombre del Producto", "| Precio", "| Unidad", "| P/Unitario",  "| Precio Total", "| Fecha");
                    System.out.println("");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                    try (ResultSet resultSet = db.executeQuery(query)) {

                    while (res.next()) {
                        String id_user = resultSet.getString("id_user");
                        String product_name = resultSet.getString("product_name");
                        double price = resultSet.getDouble("price");
                        int unit = resultSet.getInt("unit");
                        double unit_price = resultSet.getDouble("unit_price");
                        double total_price = resultSet.getDouble("total_price");
                        Date Date = resultSet.getDate("date");
                        
                        
  

                    }
                    // elegir cantidad ,calcular precio y descontar de la bd y mostar todo 
                    if (res = next()){

                        res.getString(1,"id_user");
                        res.getString(2,"product_name");

                    }
                        //importar de views app  while trie catch 



                } catch (Exception e) {
                    System.out.println("---Error al obtener el producto---");

                }


                } else {
                    System.out.println("---Producto no encontrado---");

                }
            } catch (Exception e) {
                System.out.println("---Dato no Valido---");
            }       
        }
        return buyProduct;
    }


    

    

}

