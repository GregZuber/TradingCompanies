package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.List;

public class Market {
	private List<Demand> demands = new ArrayList<Demand>();
	private List<PricesHistory> histories = new ArrayList<PricesHistory>();
	
	public List<Demand> getDemands() {
		return demands;
	}
	
	public void addDemand(Demand demand) {
		demands.add(demand);	
		PricesHistory history = new PricesHistory(demand.getProductId());
		// add three copies of price to the history
		history.getHistory().add(demand.getPrice());
		history.getHistory().add(demand.getPrice());
		history.getHistory().add(demand.getPrice());
		this.histories.add(history);
	}
	
	public double sellToMarket(int productId, int quantity) {
		for (int i =0;i<demands.size();i++) {
			Demand demand = this.demands.get(i);
			if (demand.getProductId() == productId) {
				return demand.sell(quantity);
			}
		}
		
		return 0.0;
	}
	
	public void update() {
		for (int i =0;i<demands.size();i++) {
			Demand demand = this.demands.get(i);
			
			// after each update of the market we add price do historical record
			for (int j =0;j<this.histories.size();j++) {
				if (this.histories.get(j).getProductId() == demand.getProductId()) {
					this.histories.get(j).getHistory().add(demand.getPrice());
				}
			}
			demand.update();
		}
	}

	
}
