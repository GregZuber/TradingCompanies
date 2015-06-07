package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;

// polityka wyznaczania ceny opieraj¹ca siê wy³¹cznie na historii naszych transakcji
// nieudane transakcje powoduj¹ podnoszenie max ceny zakupu, udane transakcje powoduj¹ obni¿anie lub utrzymanie
// tej ceny w stosunku do ostatniej ceny œredniej

// potrzebne trzy parametry: startowa cena jako procent œredniej ceny ostatniej
// o ile procent podnosimy cenê  je¿eli nie uda nam siê zakupiæ niczego co potrzebowaliœmy
// o ile procent obni¿amy cenê je¿eli uda³o siê nam zakupiæ
// + ewentualnie jakiœ 4 który bêdzie regulowa³ czy po udanej transakcji obni¿amy cenê, czy zostawiamy jak jest
public class ProportionalToTransactionsBuyPricePolicy implements IPricePolicy {

	@Override
	public double getPrice(Company company, int productId, Environment env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
