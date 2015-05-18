package pl.agh.edu.companies.entitiy;

import java.util.List;

public class Environment {
	private List<Company> companies;
	private Market market;
	private HistoryOfPrices history;

	public HistoryOfPrices getHistory() {
		return history;
	}

	public void setHistory(HistoryOfPrices history) {
		this.history = history;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}
	
	public Company getCompanyById(int companyId) {
	    for (Company company : companies) {
	        if (company.getId() == companyId) {
	            return company;
	        }
	    }
	    return null;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	public void decay() {
		for (Company company:getCompanies()) {
			company.getOutputWarehouse().durabilityDecay();
			for (InputWarehouse warehouse:company.getInputWarehouses()) {
				warehouse.durabilityDecay();
			}
		}
		
	}
	
}
