package pl.agh.edu.pl.companies.engine;

import java.util.List;

import pl.agh.edu.companies.entitiy.ProductBuyOffer;
import pl.agh.edu.companies.entitiy.ProductSellOffer;

public class OffersService {
    private List<ProductBuyOffer> productBuyOffers;
    private List<ProductSellOffer> productSellOffers;
    
    public List<ProductBuyOffer> getProductBuyOffers() {
        return productBuyOffers;
    }
    public void setProductBuyOffers(List<ProductBuyOffer> productBuyOffers) {
        this.productBuyOffers = productBuyOffers;
    }
    public List<ProductSellOffer> getProductSellOffers() {
        return productSellOffers;
    }
    public void setProductSellOffers(List<ProductSellOffer> productSellOffers) {
        this.productSellOffers = productSellOffers;
    }
    
}
