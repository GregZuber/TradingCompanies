package pl.agh.edu.companies.entitiy;

public class ProductBuyOffer {
    private int productsId;
    private double productsQuantity;
    private double maxPriceForOneProduct;
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
    public double getMaxPriceForOneProduct() {
        return maxPriceForOneProduct;
    }
    public void setMaxPriceForOneProduct(double maxPriceForOneProduct) {
        this.maxPriceForOneProduct = maxPriceForOneProduct;
    }
    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    
}
