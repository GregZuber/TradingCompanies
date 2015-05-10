package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.List;

public class PricesHistory {
	private List<Double> history = new ArrayList<Double>();
private int productId;


	
	public PricesHistory(int productId) {
	super();
	this.productId = productId;
}



	public int getProductId() {
	return productId;
}



	public List<Double> getHistory() {
		return history;
	}

}
