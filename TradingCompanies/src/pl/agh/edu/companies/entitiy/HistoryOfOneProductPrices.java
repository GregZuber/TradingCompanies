package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.List;

public class HistoryOfOneProductPrices {
    private int productId;
    private List<Double> averagePrices = new ArrayList<Double>();
    
    public HistoryOfOneProductPrices(int productId) {
		super();
		this.productId = productId;
	}
    
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
    
    public void addPrice(Double price) {
    	this.averagePrices.add(price);
    	
    }
    
    public Double getLastNonZero() {
    	for (int i = averagePrices.size()-1;i<0;i--) {
    		if (averagePrices.get(i) > 0.0) {
    			return averagePrices.get(i);
    		}
    	}
    	return null;
    
    }
	
    
}
