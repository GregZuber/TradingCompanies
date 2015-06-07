package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.List;

public class History {
	private List<ProductPriceQuantity> proposedOffers = new ArrayList<ProductPriceQuantity>();
	private List<ProductPriceQuantity> realTransactions = new ArrayList<ProductPriceQuantity>();
	
	private int productId;
	private int companyId;
	
	public History(int companyId, int productId) {
		super();
		this.companyId = companyId;
		this.productId = productId;
	}
	public void addProposedOffer(double price, int quantity) {
		this.proposedOffers.add(this.createRealTransaction(price, quantity));
	}

	private ProductPriceQuantity createRealTransaction(double price, int quantity) {
		return new ProductPriceQuantity(companyId, productId, price, quantity);
	}
	
	public void addRealTransaction(double price, int quantity) {
		this.realTransactions.add(this.createRealTransaction(price, quantity));
	}
	public List<ProductPriceQuantity> getProposedOffers() {
		return proposedOffers;
	}
	public List<ProductPriceQuantity> getRealTransactions() {
		return realTransactions;
	}
	public int getProductId() {
		return productId;
	}
	
	public ProductPriceQuantity getLastTransaction() {
		if (realTransactions.size() == 0) {
			return null;
		}
		
		return realTransactions.get(realTransactions.size() - 1);
	}
}
