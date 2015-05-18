package pl.agh.edu.pl.companies.engine;

import java.util.LinkedList;
import java.util.List;

import pl.agh.edu.companies.entitiy.ProductBuyOffer;
import pl.agh.edu.companies.entitiy.ProductSellOffer;

public class OffersService {
    private List<ProductBuyOffer> productBuyOffers;
    private List<ProductSellOffer> productSellOffers;
    private static OffersService instance = new OffersService();
    
    private OffersService(){}
    public static OffersService getOffersServiceInstance() {
        return instance;
    }
    public List<ProductBuyOffer> getProductBuyOffers() {
        return productBuyOffers;
    }
    public void setProductBuyOffers(List<ProductBuyOffer> productBuyOffersParam) {
        productBuyOffers = productBuyOffersParam;
    }
    public List<ProductSellOffer> getProductSellOffers() {
        return productSellOffers;
    }
    public void setProductSellOffers(List<ProductSellOffer> productSellOffersParam) {
        productSellOffers = productSellOffersParam;
    }
    public List<ProductSellOffer> getProductSellOffersById(int productId) {
        List<ProductSellOffer> offersToReturn = new LinkedList<ProductSellOffer>();
        for (ProductSellOffer offer : productSellOffers) {
            if (offer.getProductsId() == productId) {
                offersToReturn.add(offer);
            }
        }
        return offersToReturn;
    }
}
