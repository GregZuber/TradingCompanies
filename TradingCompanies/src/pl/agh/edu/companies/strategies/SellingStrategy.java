package pl.agh.edu.companies.strategies;

import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.ProductSellOffer;

public interface SellingStrategy {
	public List<ProductSellOffer> generateSellOffers(Environment env, Company c);
}
