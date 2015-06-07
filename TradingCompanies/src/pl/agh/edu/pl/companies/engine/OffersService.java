package pl.agh.edu.pl.companies.engine;

import java.util.LinkedList;
import java.util.List;

import pl.agh.edu.companies.entitiy.ProductPriceQuantity;

public class OffersService {
	private List<ProductPriceQuantity> productBuyOffers;
	private List<ProductPriceQuantity> productSellOffers;
	private static OffersService instance = new OffersService();

	private OffersService() {
	}

	public static OffersService getOffersServiceInstance() {
		return instance;
	}

	public List<ProductPriceQuantity> getProductBuyOffers() {
		return productBuyOffers;
	}

	public void setProductBuyOffers(
			List<ProductPriceQuantity> productBuyOffersParam) {
		productBuyOffers = productBuyOffersParam;
	}

	public List<ProductPriceQuantity> getProductSellOffers() {
		return productSellOffers;
	}

	public void setProductSellOffers(
			List<ProductPriceQuantity> productSellOffersParam) {
		productSellOffers = productSellOffersParam;
	}

	public List<ProductPriceQuantity> getProductSellOffersById(int productId) {
		List<ProductPriceQuantity> offersToReturn = new LinkedList<ProductPriceQuantity>();
		for (ProductPriceQuantity offer : productSellOffers) {
			if (offer.getProductsId() == productId) {
				offersToReturn.add(offer);
			}
		}
		return offersToReturn;
	}

}
