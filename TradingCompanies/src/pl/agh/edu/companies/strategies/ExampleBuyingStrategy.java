package pl.agh.edu.companies.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.InputWarehouse;
import pl.agh.edu.companies.entitiy.ProductBuyOffer;

public class ExampleBuyingStrategy implements BuyingStrategy {

	// je¿eli produkcja jest zablokowana to podwy¿szaj maksymaln¹ cenê kupna o 5% co turê
	@Override
	public List<ProductBuyOffer> generateBuyOffers(Environment env, Company c) {
		List<ProductBuyOffer> offers = new ArrayList<ProductBuyOffer>();
		
		for (InputWarehouse warehouse: c.getInputWarehouses()) {
			HashMap<String, Object> variables = InputVariableExtractor.buyingVariableExtractor(env, c, warehouse.getProductId());
		}
		
		
		return null;
	}

}
