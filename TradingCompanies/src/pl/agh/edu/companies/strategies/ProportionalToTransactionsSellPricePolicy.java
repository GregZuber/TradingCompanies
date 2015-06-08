package pl.agh.edu.companies.strategies;

import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.HistoryOfOneProductPrices;
import pl.agh.edu.companies.entitiy.ProductPriceQuantity;

// podobny scenariusz jak w polityce kupowania
public class ProportionalToTransactionsSellPricePolicy implements IPricePolicy {
	private double successfulBonus;
	private double failedPenalty;
	private int windowSize;

	public ProportionalToTransactionsSellPricePolicy(int windowSize, double successfulBonus, double failedPenalty) {
		this.windowSize = windowSize;
		this.successfulBonus = successfulBonus;
		this.failedPenalty = failedPenalty;
	}
	@Override
	public double getPrice(Company company, int productId, Environment env) {
		HistoryOfOneProductPrices prices = env.getHistory()
				.GetHistoryByProductId(productId);
		List<ProductPriceQuantity> transactions = company.getSellingHistory().getRealTransactions();
		List<ProductPriceQuantity> proposedOffers = company.getSellingHistory().getProposedOffers();
		double lastPrice = prices.getLastNonZero();

		// ostatnia transakcja jest uznana za udan¹, je¿eli cokolwiek kupiliœmy
		// chyba, ¿e nie chcieliœmy nic kupowaæ
		boolean lastSuccesful = false;
		if (transactions.size() > 0) {
			lastSuccesful = transactions.get(transactions.size() - 1)
					.getQuantity() > 0;
			lastSuccesful |= proposedOffers.get(proposedOffers.size() - 1)
					.getQuantity() > 0;
					// bierzemy okno z ostatnich 6 transakcji i liczymy jaki jest stosunek
					// udanych do nieudanych transakcji

					int successfulTransactions = 0;
					int failedTransactions = 0;
					int skippedTransactions = 0;
					int j = 0;
					for (int i = proposedOffers.size() - 1; i < 0; i--) {
						if (transactions.get(i).getQuantity() > 0) {
							successfulTransactions++;
						} else if ((transactions.get(i).getQuantity() == 0)
								&& (proposedOffers.get(i).getQuantity() == 0)) {
							skippedTransactions++;
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
						if (successfulTransactions >= this.windowSize/2) {
							return lastPrice*(1+ (successfulTransactions - 2)*this.successfulBonus);
						}
						else {
							return lastPrice;
						}
					} 
					// podwy¿sz cenê kupna albo zostaw tak¹ jaka jest
					else{
						if (failedTransactions > this.windowSize/2) {
							return lastPrice*(1 - (failedTransactions - this.windowSize/2)*this.failedPenalty);
						}
						else {
							return lastPrice;
						}
					}
		}

		return lastPrice;
	}

}
