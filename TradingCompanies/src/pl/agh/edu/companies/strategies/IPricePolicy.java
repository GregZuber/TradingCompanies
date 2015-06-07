package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;

public interface IPricePolicy {
	public double getPrice(Company company, int productId, Environment env);
}
