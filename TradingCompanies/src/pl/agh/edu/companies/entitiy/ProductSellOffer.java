package pl.agh.edu.companies.entitiy;

public class ProductSellOffer implements Comparable<ProductSellOffer>{
    private int productsId;
    private double productsQuantity;
    private double oneProductPrice;
    private int companyId;
    
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
    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public int compareTo(ProductSellOffer offer)
    {
        if (oneProductPrice > offer.getOneProductPrice()) {
            return 1;
        } else if (oneProductPrice == offer.getOneProductPrice()) {
            return 0;
        } else {
            return -1;
        }
    }
    
}
