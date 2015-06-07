package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;


// scenariusz w kt�rym cena kupna jest proporcjonalna do tego ile mamy towar�w w warehouse
// im mniej mamy tym wy�sza cen� kupna wystawiamy w stosunku do ostatniej �redniej
// w tym wariancie, nie zwracamy uwag� na histori� transakcji

public class ProportionalToWarehousePricePolicy implements IPricePolicy {

	@Override
	public double getPrice(Company company, int productId, Environment env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
