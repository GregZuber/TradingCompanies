package pl.agh.edu.companies.entitiy;

public class ProductBuyOffer {
    private int productsId;
    private int productsQuantity;
    private double maxPriceForOneProduct;
    private int companyId;

    public int getProductsId() {
        return productsId;
    }
    public void setProductsId(int productsId) {
        this.productsId = productsId;
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
    public int getProductsQuantity() {
        return productsQuantity;
    }
    public void setProductsQuantity(int productsQuantity) {
        this.productsQuantity = productsQuantity;
    }
    
}
