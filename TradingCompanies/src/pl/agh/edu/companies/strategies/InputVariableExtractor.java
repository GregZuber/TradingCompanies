package pl.agh.edu.companies.strategies;

import java.util.HashMap;
import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.HistoryOfOneProductPrices;
import pl.agh.edu.companies.entitiy.InputWarehouse;
import pl.agh.edu.companies.entitiy.ProductBuyOffer;
import pl.agh.edu.companies.entitiy.ProductSellOffer;

public class InputVariableExtractor {
	public static HashMap<String, Object> buyingVariableExtractor(
			Environment env, Company c, int productId) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("canProduce", c.canProduce());
		variables.put("capital", c.getCapital());
		InputWarehouse warehouse = c.getInputWarehouseByProductId(productId);
		variables.put("consumedPerTurn", warehouse.getProductionDelta());
		variables.put("capacity", warehouse.getCapacity());
		variables.put("productsInStock", warehouse.getNumberOfProducts());

		List<ProductBuyOffer> offers = warehouse.getTransactions();
		variables.put("privateHistoryLength", offers.size());
		for (int i = 0; i < Math.min(3, offers.size()); i++) {
			variables.put("privatePriceOffered" + i,
					offers.get(offers.size() - (i + 1))
							.getMaxPriceForOneProduct());
			variables.put("privatePriceBought" + i,
					offers.get(offers.size() - (i + 1)).getRealBuyPrice());
			variables.put("privateAmountOffered" + i,
					offers.get(offers.size() - (i + 1)).getProductsQuantity());
			variables.put("privateAmountBought" + i,
					offers.get(offers.size() - (i + 1)).getBoughtQuantity());
		}

		List<HistoryOfOneProductPrices> histories = env.getHistory()
				.getProductsPrices();
		for (HistoryOfOneProductPrices hist : histories) {
			if (hist.getProductId() == productId) {
				List<Double> prices = hist.getAveragePrices();
				variables.put("publicHistoryLength", prices.size());
				for (int i = 0; i < Math.min(3, prices.size()); i++) {
					variables.put("publicPrice" + i,
							prices.get(prices.size() - (i + 1)));
				}
			}
		}

		return variables;
	}

	public static HashMap<String, Object> sellingVariableExtractor(
			Environment env, Company c) {
		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("canProduce", c.canProduce());
		variables.put("capital", c.getCapital());

		variables.put("productsInStock", c.getOutputWarehouse()
				.getNumberOfProducts());
		variables.put("capacity", c.getOutputWarehouse().getCapacity());

		List<ProductSellOffer> offers = c.getOutputWarehouse()
				.getTransactions();
		variables.put("privateHistoryLength", offers.size());
		for (int i = 0; i < Math.min(3, offers.size()); i++) {
			variables.put("privatePrice" + i,
					offers.get(offers.size() - (i + 1)).getOneProductPrice());
			variables.put("privateAmountOffered" + i,
					offers.get(offers.size() - (i + 1)).getProductsQuantity());
			variables.put("privateAmountSold" + i,
					offers.get(offers.size() - (i + 1)).getSoldQuantity());
		}

		List<HistoryOfOneProductPrices> histories = env.getHistory()
				.getProductsPrices();
		for (HistoryOfOneProductPrices hist : histories) {
			if (hist.getProductId() == c.getOutputWarehouse().getProductId()) {
				List<Double> prices = hist.getAveragePrices();
				variables.put("publicHistoryLength", prices.size());
				for (int i = 0; i < Math.min(3, prices.size()); i++) {
					variables.put("publicPrice" + i,
							prices.get(prices.size() - (i + 1)));
				}
			}
		}

		return variables;
	}
}
