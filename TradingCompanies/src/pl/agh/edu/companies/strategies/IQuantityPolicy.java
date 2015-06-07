package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;

public interface IQuantityPolicy {
	public int getQuantity(Company company, int productId, Environment env);
}