package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutputWarehouse extends Warehouse {
	private int productionDurability;
	private List<SellTransaction> transactions;
	public OutputWarehouse(int productId, int capacity, int productionDelta,
			int productionDurability) {
		super(productId, capacity, productionDelta);
		this.productionDurability = productionDurability;
		this.transactions = new ArrayList<SellTransaction>();
	}

	public void produce() {
		for (int i = 0; i < this.productionDelta; i++) {
			this.addProduct(this.productionDurability);
		}
	}

	public void removeSoldItems(List<Integer> indexes) {
		Collections.sort(indexes);
		Collections.reverse(indexes);
		
		for (int i=0;i<indexes.size();i++) {
			this.productDurabilities.remove(indexes.get(i));
		}
		
		Collections.sort(this.productDurabilities);
	}
	
	public void addHistoricalData(SellTransaction transaction) {
		this.transactions.add(transaction);
	}

	public List<SellTransaction> getTransactions() {
		return transactions;
	}
}
