package pl.agh.edu.companies.entitiy;

import java.util.List;

public class Production {
    private double tourPartNeededToProductOneProduct;
    private List<Product> neededProducts;
    private double productionCost;
    public List<Product> getNeededProducts() {
        return neededProducts;
    }
    public void setNeededProducts(List<Product> neededProducts) {
        this.neededProducts = neededProducts;
    }
    public double getProductionCost() {
        return productionCost;
    }
    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }
    public double getTourPartNeededToProductOneProduct() {
        return tourPartNeededToProductOneProduct;
    }
    public void setTourPartNeededToProductOneProduct(
            double tourPartNeededToProductOneProduct) {
        this.tourPartNeededToProductOneProduct = tourPartNeededToProductOneProduct;
    }
    
}
