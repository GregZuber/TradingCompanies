package pl.agh.edu.companies.initialdata;

import java.util.ArrayList;
import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Demand;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.HistoryOfOneProductPrices;
import pl.agh.edu.companies.entitiy.HistoryOfPrices;
import pl.agh.edu.companies.entitiy.Market;
import pl.agh.edu.companies.entitiy.Warehouse;

public class Environments {
	private static List<Company> getProducers() {
		// id, capital, fixedCost
		Company producer1 = new Company(0, 10000.0, 100.0);
		producer1.setOutputWarehouse(new Warehouse(0));
		producer1.setInputWarehouses(null); // none required
		
		Company producer2 = new Company(1,8000.0,40.0);
		producer2.setOutputWarehouse(new Warehouse(0));
		producer2.setInputWarehouses(null); // none required

		Company producer3 = new Company(2,20000.0,20.0);
		producer3.setOutputWarehouse(new Warehouse(1));
		producer3.setInputWarehouses(null); // none required

		
		Company producer4 = new Company(3,16000.0,80.0);
		producer4.setOutputWarehouse(new Warehouse(1));
		producer4.setInputWarehouses(null); // none required
		
		ArrayList<Company> producers = new ArrayList<Company>();
		producers.add(producer1);
		producers.add(producer2);
		producers.add(producer3);
		producers.add(producer4);
		
		return producers;
		
	}
	
	private static List<Company> getConverters() {
		int productId = 2;
		// id, capital, fixedCost, productionCost
		Company converter1 = new Company(4,16000.0,20.0);
		converter1.setOutputWarehouse(new Warehouse(productId));
		
		ArrayList<Warehouse> firstInputWarehouses = new ArrayList<Warehouse>();
		firstInputWarehouses.add(new Warehouse(0));
		firstInputWarehouses.add(new Warehouse(1));
		converter1.setInputWarehouses(firstInputWarehouses);
		
		// id, capital, fixedCost, productionCost
		Company converter2 = new Company(5,16000.0,20.0);
		converter2.setOutputWarehouse(new Warehouse(productId));
		
		ArrayList<Warehouse> secondInputWarehouses = new ArrayList<Warehouse>();
		secondInputWarehouses.add(new Warehouse(0));
		secondInputWarehouses.add(new Warehouse(1));
		converter2.setInputWarehouses(secondInputWarehouses);

		ArrayList<Company> producers = new ArrayList<Company>();
		producers.add(converter1);
		producers.add(converter2);
		
		return producers;
	}
	
	private static Market getMarket() {
		// Market with demand only for product 2
		Market market = new Market();
		
		Demand demand = new Demand(2);
		demand.setPrice(300.0);		
		demand.setMaxPriceChange(10);

		market.addDemand(demand);
		
		return market;
	}
	
	public static Environment getSampleEnvironment() {
		Environment env = new Environment();
		// generate companies
		ArrayList<Company> companies = new ArrayList<Company>();
		companies.addAll(Environments.getProducers());
		companies.addAll(Environments.getConverters());
		env.setCompanies(companies);
		// generate market
		env.setMarket(Environments.getMarket());
		
		
		HistoryOfOneProductPrices firstPrices = new HistoryOfOneProductPrices(0);
		firstPrices.addPrice(100.0);
		firstPrices.addPrice(102.0);
		firstPrices.addPrice(101.0);
		

		HistoryOfOneProductPrices secondPrices = new HistoryOfOneProductPrices(1);
		secondPrices.addPrice(150.0);
		secondPrices.addPrice(154.0);
		secondPrices.addPrice(152.0);
		
		HistoryOfOneProductPrices threePrices = new HistoryOfOneProductPrices(2);
		threePrices.addPrice(450.0);
		threePrices.addPrice(454.0);
		threePrices.addPrice(452.0);
		HistoryOfPrices pricesHistory = new HistoryOfPrices();
		ArrayList<HistoryOfOneProductPrices> pricesHistoryList = new ArrayList<HistoryOfOneProductPrices>();
		pricesHistoryList.add(firstPrices);
		pricesHistoryList.add(secondPrices);
		pricesHistoryList.add(threePrices);
		pricesHistory.setProductsPrices(pricesHistoryList);
		
		env.setHistory(pricesHistory);
		
		return env;
		
	}
}
