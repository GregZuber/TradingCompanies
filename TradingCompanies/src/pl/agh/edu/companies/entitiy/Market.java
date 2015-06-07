package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.List;

public class Market {
	private List<Demand> demands = new ArrayList<Demand>();
	
	public List<Demand> getDemands() {
		return demands;
	}
	
	public void addDemand(Demand demand) {
		demands.add(demand);	
	}
	
	public void update() {
		for (int i =0;i<demands.size();i++) {
			this.demands.get(i).update();
		}
	}

}
