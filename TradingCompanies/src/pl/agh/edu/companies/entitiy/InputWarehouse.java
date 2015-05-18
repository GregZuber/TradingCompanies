package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputWarehouse extends Warehouse {
	private List<BuyTransaction> transactions;
	
	public InputWarehouse(int productId, int capacity, int productionDelta) {
		super(productId, capacity, productionDelta);

		this.transactions = new ArrayList<BuyTransaction>();
	}
	
	public boolean canConsume() {
		return this.productDurabilities.size() >= this.productionDelta;
	}
	
	public void consume() {
		if (this.canConsume()) {
			int itemsToConsume = productionDelta;
			while (itemsToConsume-- > 0) {
				this.productDurabilities.remove(0);
			}
		}
		
		Collections.sort(this.productDurabilities);
	}
	
	public void addBoughtProducts(List<Integer> productDurabilities) {
		for (int i=0;i<productDurabilities.size();i++) {
			this.addProduct(productDurabilities.get(i));
		}
	}
	
	public void addHistoricalData(BuyTransaction transaction) {
		transactions.add(transaction);
	}

}
