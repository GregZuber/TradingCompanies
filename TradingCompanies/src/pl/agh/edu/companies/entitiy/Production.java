package pl.agh.edu.companies.entitiy;

import java.util.List;

public class Production {
    private double productionTime;
    private List<Product> neededProducts;
    private double productionCost;
    private double tourPartWhenProducted;
    public double getProductionTime() {
        return productionTime;
    }
    public void setProductionTime(double productionTime) {
        this.productionTime = productionTime;
    }
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
    public double getTourPartWhenProducted() {
        return tourPartWhenProducted;
    }
    public void setTourPartWhenProducted(double tourPartWhenProducted) {
        this.tourPartWhenProducted = tourPartWhenProducted;
    }
    
}
