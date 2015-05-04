package pl.agh.edu.companies.entitiy;

import java.util.List;

public class Company {
    private double capital;
    private double fixedCosts;
    private double warehouseSize;
    private List<Product> productsInWarehouse;
    private Production production;
    
    private void addProductsToWarehouse(List<Product> products) {
        
    }
    
    private void removeProductsFromWarehouse(List<Product> products) {
        
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getFixedCosts() {
        return fixedCosts;
    }

    public void setFixedCosts(double fixedCosts) {
        this.fixedCosts = fixedCosts;
    }

    public double getWarehouseSize() {
        return warehouseSize;
    }

    public void setWarehouseSize(double warehouseSize) {
        this.warehouseSize = warehouseSize;
    }

    public List<Product> getProductsInWarehouse() {
        return productsInWarehouse;
    }

    public void setProductsInWarehouse(List<Product> productsInWarehouse) {
        this.productsInWarehouse = productsInWarehouse;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

}
