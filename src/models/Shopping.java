package models;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import conection.ConectionDB;
import controllers.productControls;

public class Shopping{
    private int id_user ;
    private String product_name;
    private int unit;
    private double unit_price ; 
    private double total_price;
    private LocalDate date;
    
    public Shopping(int id_user, String product_name, int unit, double unit_price) {
        this.id_user = id_user;
        this.product_name = product_name;
        this.unit = unit;
        this.unit_price = unit_price;
        this.total_price = unit * unit_price;
        this.date = LocalDate.now();
    }

    public static Shopping createShop(int idUser) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in); 
        productControls.readProducts();
        Shopping buyProduct = null;
        boolean val = true;
        while (val) {
            int idProd;
            while (true) {
                try {
                    System.out.print("\nIngrese el id del producto a comprar: ");
                    idProd = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("---Opción no válida---");
                    scanner.nextLine();
                }
            }
            if (productControls.validProduct(idProd)) {
                String query = "SELECT name, price FROM products WHERE id = " + idProd;
                ConectionDB db = new ConectionDB(); 
                try (ResultSet res = db.executeQuery(query)) {
                    int unit;
                    while (true) {
                        System.out.print("\nIngresa el número de unidades a comprar: ");
                        try {
                            unit = scanner.nextInt();  
                            scanner.nextLine();     
                            break;
                        } catch (Exception e) {
                            System.out.println("---Opción no válida---");
                            scanner.nextLine();
                        }
                    }
                    if (res.next()) { 
                        String productName = res.getString("name");
                        double price = res.getDouble("price");
                        buyProduct = new Shopping(idUser, productName, unit, price);
                        val = false;
                    } else {
                        System.out.println("---Producto no encontrado en la base de datos---");
                    }
                } catch (Exception e) {
                    System.out.println("---Error al obtener el producto---");
                } finally {
                    db.closeConectionDB();
                }
            } else {
                System.out.println("---Producto no encontrado---");
            }
        }
        return buyProduct;
    }
    
    public int getId_user() {
        return id_user;
    }

    public String getProduct_name() {
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

    public String getDate() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaStr = date.format(formato);
        return fechaStr;
    }
}

