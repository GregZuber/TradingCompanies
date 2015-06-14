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
		double newPrice = prices.getLastNonZero();

		// ostatnia transakcja jest uznana za udan¹, je¿eli cokolwiek kupiliœmy
		// chyba, ¿e nie chcieliœmy nic kupowaæ
		boolean lastSuccesful = false;
		
		if (transactions.size() > 0) {
			if (transactions.size() <= this.windowSize) {
				newPrice = this.getPriceFromWindow(transactions,proposedOffers, newPrice);
			}
			else {
				newPrice = this.getPriceFromWindow(
						transactions.subList(transactions.size() - windowSize, transactions.size()),
						proposedOffers.subList(proposedOffers.size() - windowSize, proposedOffers.size())
						, newPrice);
				
			}
		}

		return newPrice;
	}
	
	private double getPriceFromWindow(
			List<ProductPriceQuantity> transactions, 
			List<ProductPriceQuantity> offers, 
			double lastPrice) {
		
		int okTransactions = 0;
		int failedTransactions = 0;
		
		for (int i = 0;i<transactions.size();i++) {
			if (transactions.get(i).getQuantity() == offers.get(i).getProductsId()) {
				okTransactions++;
			} else {
				failedTransactions++;
			}
		}
		
		boolean lastOk = transactions.get(transactions.size()-1).getQuantity() == offers.get(transactions.size()-1).getQuantity();
		
		if (lastOk) {
			if (okTransactions > 1) {
				return lastPrice * (1 + successfulBonus);
			}
			else {
				return lastPrice;
			}
		}
		else {
			if (failedTransactions > 1) {
				return lastPrice * (1 - failedPenalty);
			}
			else {
				return lastPrice;
			}
		}
	}

}
