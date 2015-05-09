package pl.agh.edu.companies.entitiy;

import java.util.List;

public class HistoryOfOneProductPrices {
    private int productId;
    private List<Double> averagePrices;
    
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public List<Double> getAveragePrices() {
        return averagePrices;
    }
    public void setAveragePrices(List<Double> averagePrices) {
        this.averagePrices = averagePrices;
    }
    
}
