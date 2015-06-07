package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;

// polityka wyznaczania ceny opieraj�ca si� wy��cznie na historii naszych transakcji
// nieudane transakcje powoduj� podnoszenie max ceny zakupu, udane transakcje powoduj� obni�anie lub utrzymanie
// tej ceny w stosunku do ostatniej ceny �redniej

// potrzebne trzy parametry: startowa cena jako procent �redniej ceny ostatniej
// o ile procent podnosimy cen�  je�eli nie uda nam si� zakupi� niczego co potrzebowali�my
// o ile procent obni�amy cen� je�eli uda�o si� nam zakupi�
// + ewentualnie jaki� 4 kt�ry b�dzie regulowa� czy po udanej transakcji obni�amy cen�, czy zostawiamy jak jest
public class ProportionalToTransactionsBuyPricePolicy implements IPricePolicy {

	@Override
	public double getPrice(Company company, int productId, Environment env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
