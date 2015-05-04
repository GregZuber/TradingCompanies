package pl.agh.edu.companies.entitiy;

public class Demand {
    private Product product;
    private double demandForProduct;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public double getDemandForProduct() {
        return demandForProduct;
    }
    public void setDemandForProduct(double demandForProduct) {
        this.demandForProduct = demandForProduct;
    }

}
