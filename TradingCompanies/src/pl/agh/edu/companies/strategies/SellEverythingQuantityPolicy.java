package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;

// sprzedawaj wszystko po przekroczeniu wyznaczonego progu
public class SellEverythingQuantityPolicy implements IQuantityPolicy {
	private int level;

	public SellEverythingQuantityPolicy(int level) {
		this.level = level;
	}
	
	@Override
	public int getQuantity(Company company, int productId, Environment env) {
		if (company.getInputWarehouseByProductId(productId).getProductCount() > level) {
			return company.getInputWarehouseByProductId(productId).getProductCount();
		}
		
		return 0;
	}

}