package pl.agh.edu.companies.entitiy;

public class Demand {
    private int productId;
    private double demandForProduct;

    public double getDemandForProduct() {
        return demandForProduct;
    }
    public void setDemandForProduct(double demandForProduct) {
        this.demandForProduct = demandForProduct;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

}
