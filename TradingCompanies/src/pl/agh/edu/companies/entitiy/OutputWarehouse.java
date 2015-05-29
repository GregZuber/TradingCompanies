package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class OutputWarehouse extends Warehouse {
	private int productionDurability;
	private List<ProductSellOffer> transactions;
	public OutputWarehouse(int productId, int capacity, int productionDelta,
			int productionDurability) {
		super(productId, capacity, productionDelta);
		this.productionDurability = productionDurability;
		this.transactions = new ArrayList<ProductSellOffer>();
	}

	public void produce() {
		for (int i = 0; i < this.productionDelta; i++) {
			this.addProduct(this.productionDurability);
		}
	}

	public List<Integer> removeSoldItems(List<Integer> indexes) {
		Collections.sort(indexes);
		Collections.reverse(indexes);

		List<Integer> productsDurabilities = new LinkedList<Integer>();

		for (int i=0;i<indexes.size();i++) {
			productsDurabilities.add(indexes.get(i));
			this.productDurabilities.remove(indexes.get(i));
		}
		
		Collections.sort(this.productDurabilities);

		return productsDurabilities;
	}
	
	public void addHistoricalData(ProductSellOffer transaction) {
		this.transactions.add(transaction);
	}

	public List<ProductSellOffer> getTransactions() {
		return transactions;
	}
}
