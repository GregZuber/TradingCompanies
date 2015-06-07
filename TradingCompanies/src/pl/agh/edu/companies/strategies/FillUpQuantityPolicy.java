package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;

// policy which determines how much items will be bought
// 
public class FillUpQuantityPolicy implements IQuantityPolicy {
	private int level;

	public FillUpQuantityPolicy(int level) {
		this.level = level;
	}
	
	public int getQuantity(Company company, int productId, Environment env) {
		int required = level - company.getInputWarehouseByProductId(productId).getProductCount();
		if (required > 0) {
			return required;
		}
		
		return 0;
	}
}
