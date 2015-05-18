package pl.agh.edu.companies.strategies;

import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.ProductBuyOffer;

public interface BuyingStrategy {
	public List<ProductBuyOffer> generateBuyOffers(Environment env, Company c);
	
}
