package pl.agh.edu.companies.entitiy;

import java.util.List;

public class Warehouse {
    private int productId;
    private double warehouseSize;
    private List<Product> productsInWarehouse;
    
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
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
    
    
}
