package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Warehouse {
	protected int productId;
	protected int productionDelta;
	protected int productionDurability;
	protected int capacity;
	protected List<Integer> productDurabilities;

	public Warehouse(int productId, int capacity, int productionDelta) {
		super();
		this.productId = productId;
		this.productionDelta = productionDelta;
		this.capacity = capacity;
		this.productDurabilities = new ArrayList<Integer>();
	}

	// if adding product when full then replace product with lowest durability
	protected void addProduct(int newProductDurability) {
		Collections.sort(this.productDurabilities);
		if (this.productDurabilities.size() >= this.capacity) {
			this.productDurabilities.set(0, newProductDurability);
		} else {
			this.productDurabilities.add(newProductDurability);
		}

		Collections.sort(this.productDurabilities);
	}

	public void durabilityDecay() {
		Integer itemToRemove = 1;
		// remove all items with one durability
		while (this.productDurabilities.remove(itemToRemove)) {

		}
		// remove one durability from all other items
		for (int i = 0; i < this.productDurabilities.size(); i++) {
			this.productDurabilities
					.set(i, this.productDurabilities.get(i) - 1);
		}

		Collections.sort(this.productDurabilities);
	}

	public List<Integer> getProductDurabilities() {
		return productDurabilities;
	}

	
	public int getCapacity() {
		return capacity;
	}
	
	public int getProductionDelta() {
		return productionDelta;
	}

	public int getNumberOfProducts() {
		return this.productDurabilities.size();
	}

	
}
