/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suparmarket;

/**
 *
 * @author ASUS
 */
public class productData {
    private String productId;
    private String brand;
    private String productName;
    private String status;
    private Double price;

    public productData(String productId, String brand, String productName, String status, Double price) {
        this.productId = productId;
        this.brand = brand;
        this.productName = productName;
        this.status = status;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getBrand() {
        return brand;
    }

    public String getProductName() {
        return productName;
    }

    public String getStatus() {
        return status;
    }

    public Double getPrice() {
        return price;
    }

 

    
    
}
