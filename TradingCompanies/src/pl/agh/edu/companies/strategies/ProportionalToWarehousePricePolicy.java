package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;


// scenariusz w którym cena kupna jest proporcjonalna do tego ile mamy towarów w warehouse
// im mniej mamy tym wy¿sza cenê kupna wystawiamy w stosunku do ostatniej œredniej
// w tym wariancie, nie zwracamy uwagê na historiê transakcji

public class ProportionalToWarehousePricePolicy implements IPricePolicy {

	@Override
	public double getPrice(Company company, int productId, Environment env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
