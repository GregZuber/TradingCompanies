package pl.agh.edu.companies.strategies;

import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.History;
import pl.agh.edu.companies.entitiy.HistoryOfOneProductPrices;
import pl.agh.edu.companies.entitiy.ProductPriceQuantity;

// polityka wyznaczania ceny opieraj¹ca siê wy³¹cznie na historii naszych transakcji
// nieudane transakcje powoduj¹ podnoszenie max ceny zakupu, udane transakcje powoduj¹ obni¿anie lub utrzymanie
// tej ceny w stosunku do ostatniej ceny œredniej

// potrzebne trzy parametry: startowa cena jako procent œredniej ceny ostatniej
// o ile procent podnosimy cenê  je¿eli nie uda nam siê zakupiæ niczego co potrzebowaliœmy
// o ile procent obni¿amy cenê je¿eli uda³o siê nam zakupiæ
// + ewentualnie jakiœ 4 który bêdzie regulowa³ czy po udanej transakcji obni¿amy cenê, czy zostawiamy jak jest
public class ProportionalToTransactionsBuyPricePolicy implements IPricePolicy {
	private double successfulBonus;
	private double failedPenalty;
	private int windowSize;

	public ProportionalToTransactionsBuyPricePolicy(int windowSize, double successfulBonus, double failedPenalty) {
		this.windowSize = windowSize;
		this.successfulBonus = successfulBonus;
		this.failedPenalty = failedPenalty;
	}

	@Override
	public double getPrice(Company company, int productId, Environment env) {
		HistoryOfOneProductPrices prices = env.getHistory()
				.GetHistoryByProductId(productId);
		List<ProductPriceQuantity> transactions = company
				.getBuyingHistoryByProductId(productId).getRealTransactions();
		List<ProductPriceQuantity> proposedOffers = company
				.getBuyingHistoryByProductId(productId).getProposedOffers();
		double lastPrice = prices.getLastNonZero();

		// ostatnia transakcja jest uznana za udan¹, je¿eli cokolwiek kupiliœmy
		// chyba, ¿e nie chcieliœmy nic kupowaæ
		boolean lastSuccesful = false;
		if (transactions.size() > 0) {
			lastSuccesful = transactions.get(transactions.size() - 1)
					.getQuantity() > 0;
			lastSuccesful |= proposedOffers.get(proposedOffers.size() - 1)
					.getQuantity() > 0;
		}

		// bierzemy okno z ostatnich 6 transakcji i liczymy jaki jest stosunek
		// udanych do nieudanych transakcji

		int successfulTransactions = 0;
		int failedTransactions = 0;
		int j = 0;
		for (int i = proposedOffers.size() - 1; i >= 0; i--) {
			if (transactions.get(i).getQuantity() > 0) {
				successfulTransactions++;
			} else if (transactions.get(i).getQuantity() == proposedOffers.get(i).getQuantity()) {
				successfulTransactions++;
			} else {
				failedTransactions++;
			}
			
			// bierzemy maksymalnie windowSize elementowe okno
			j++;
			if (j == this.windowSize) {
				break;
			}
		}
		
		// pozostaw cenê kupna taka jak jest albo obni¿
		if (lastSuccesful) {
			if (successfulTransactions > 1) {
				return lastPrice*(1- (successfulTransactions - 2)*this.successfulBonus);
			}
			else {
				return lastPrice;
			}
		} 
		// podwy¿sz cenê kupna albo zostaw tak¹ jaka jest
		else{
			if (failedTransactions > 1) {
				return lastPrice*(1 + (failedTransactions - this.windowSize/2)*this.failedPenalty);
			}
			else {
				return lastPrice;
			}
		}
		
	}
}
