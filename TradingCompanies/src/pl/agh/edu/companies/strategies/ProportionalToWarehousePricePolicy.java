package pl.agh.edu.companies.strategies;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.HistoryOfOneProductPrices;

// scenariusz w którym cena kupna jest proporcjonalna do tego ile mamy towarów w warehouse
// im mniej mamy tym wy¿sza cenê kupna wystawiamy w stosunku do ostatniej œredniej
// w tym wariancie, nie zwracamy uwagê na historiê transakcji

public class ProportionalToWarehousePricePolicy implements IPricePolicy {
	private double comfortBonus;
	private double discomfortPenalty;
	private int comfortLevel;

	public ProportionalToWarehousePricePolicy(int comfortLevel,
			double discomfortPenalty, double comfortBonus) {
		this.comfortLevel = comfortLevel;
		this.discomfortPenalty = discomfortPenalty;
		this.comfortBonus = comfortBonus;
	}

	public double getPrice(Company company, int productId, Environment env) {
		HistoryOfOneProductPrices prices = env.getHistory().GetHistoryByProductId(productId);
		double lastPrice = prices.getLastNonZero();
		int productCount = company.getInputWarehouseByProductId(productId).getProductCount();
		
		int diff = productCount - this.comfortLevel;
		if (diff > 0) {
			return lastPrice * (1 - diff*comfortBonus);
		}
		else if (diff == 0) {
			return lastPrice;
		}
		else {
			return lastPrice * (1 - diff*discomfortPenalty);
		}
	}

}
