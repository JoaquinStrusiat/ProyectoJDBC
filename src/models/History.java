package models;
import java.util.Date;

public class History {

    public static History createHistory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private int idhistory;
    private int product;
    private double price;   // para manejar decimal(10,2)
    private int unit;
    private Date purchaseDate;

    // Constructor
    public History(int idhistory, int product, double price, int unit, Date purchaseDate) {
        this.idhistory = idhistory;
        this.product = product;
        this.price = price;
        this.unit = unit;
        this.purchaseDate = purchaseDate;
    }

    // Getters y Setters
    public int getIdhistory() {
        return idhistory;
    }

    public void setIdhistory(int idhistory) {
        this.idhistory = idhistory;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // Método toString para facilitar la visualización de datos
    @Override
    public String toString() {
        return "History{" +
                "idhistory=" + idhistory +
                ", product=" + product +
                ", price=" + price +
                ", unit=" + unit +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
