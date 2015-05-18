package pl.agh.edu.companies.strategies;

import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.InputWarehouse;
import pl.agh.edu.companies.entitiy.ProductBuyOffer;

public class ExampleBuyingStrategy implements BuyingStrategy {

	// je¿eli produkcja jest zablokowana to podwy¿szaj maksymaln¹ cenê kupna o 5% co turê
	@Override
	public List<ProductBuyOffer> generateBuyOffers(Environment env, Company c) {		
		for (int i=0;i<c.getInputWarehouses().size();i++) {
			InputWarehouse warehouse = c.getInputWarehouses().get(i);
			ProductBuyOffer transaction = new ProductBuyOffer();
			// a bit magic
			
			
			
			warehouse.addHistoricalData(transaction);
		}
		
		
		return null;
	}

}
