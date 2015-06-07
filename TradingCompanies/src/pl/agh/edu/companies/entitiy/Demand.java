package pl.agh.edu.companies.entitiy;

import java.util.Random;

public class Demand {
	private int productId;
	private double price;
	private int maxPriceChange;

	public Demand(int productId) {
		super();
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setMaxPriceChange(int maxPriceChange) {
		this.maxPriceChange = maxPriceChange;
	}
	
	public int getMaxPriceChange() {
		return maxPriceChange;
	}

	public int getProductId() {
		return productId;
	}
	
	public void update() {
	    Random r = new Random();
	    this.price += r.nextInt(2*maxPriceChange+1) - maxPriceChange;
	}

}
