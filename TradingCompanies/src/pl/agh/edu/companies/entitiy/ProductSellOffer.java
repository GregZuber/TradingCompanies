package pl.agh.edu.companies.entitiy;

import java.util.List;

public class ProductSellOffer {
    private int productsId;
    private double productsQuantity;
    private double oneProductPrice;
    
    public int getProductsId() {
        return productsId;
    }
    public void setProductsId(int productsId) {
        this.productsId = productsId;
    }
    public double getProductsQuantity() {
        return productsQuantity;
    }
    public void setProductsQuantity(double productsQuantity) {
        this.productsQuantity = productsQuantity;
    }
    public double getOneProductPrice() {
        return oneProductPrice;
    }
    public void setOneProductPrice(double oneProductPrice) {
        this.oneProductPrice = oneProductPrice;
    }
    
}
