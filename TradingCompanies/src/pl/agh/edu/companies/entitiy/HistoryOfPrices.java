package pl.agh.edu.companies.entitiy;

import java.util.List;

public class HistoryOfPrices {
    private List<HistoryOfOneProductPrices> productsPrices;

    public List<HistoryOfOneProductPrices> getProductsPrices() {
        return productsPrices;
    }

    public void setProductsPrices(List<HistoryOfOneProductPrices> productsPrices) {
        this.productsPrices = productsPrices;
    }
    
    public HistoryOfOneProductPrices GetHistoryByProductId(int id) {
    	for (HistoryOfOneProductPrices history : getProductsPrices()) {
    		if (history.getProductId() == id) {
    			return history;
    		}
    	}
    	
    	return null;
    }
    
    
}
