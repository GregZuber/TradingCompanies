package pl.agh.edu.companies.strategies;

import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.InputWarehouse;
import pl.agh.edu.companies.entitiy.OutputWarehouse;
import pl.agh.edu.companies.entitiy.ProductSellOffer;

public class ExampleSellingStrategy implements SellingStrategy {

	@Override
	public List<ProductSellOffer> generateSellOffers(Environment env, Company c) {
		// TODO Auto-generated method stub
		OutputWarehouse warehouse = c.getOutputWarehouse();
		ProductSellOffer transaction = new ProductSellOffer();
		// a bit magic
		
		
		
		warehouse.addHistoricalData(transaction);
		return null;
	}

}
