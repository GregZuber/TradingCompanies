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
    
    
}
