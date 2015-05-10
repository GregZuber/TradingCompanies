package pl.agh.edu.companies.entitiy;

import java.util.Random;

public class Demand {
	private int productId;
	private int demandForProduct;
	private double price;
	private int lowerDemandBound;
	private int upperDemandBound;
	private double highDemandBonus;
	private double lowDemandBonus;
	private int maxDemandChange;

	public Demand(int productId) {
		super();
		this.productId = productId;
	}

	public double sell(int quantity) {
		this.demandForProduct -= quantity;
		this.demandForProduct = Math.max(this.demandForProduct, 0);
		return quantity * this.price;
	}
	
	public int getDemandForProduct() {
		return demandForProduct;
	}

	public void setDemandForProduct(int demandForProduct) {
		this.demandForProduct = demandForProduct;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getLowerDemandBound() {
		return lowerDemandBound;
	}

	public void setLowerDemandBound(int lowerDemandBound) {
		this.lowerDemandBound = lowerDemandBound;
	}

	public int getUpperDemandBound() {
		return upperDemandBound;
	}

	public void setUpperDemandBound(int upperDemandBound) {
		this.upperDemandBound = upperDemandBound;
	}

	public double getHighDemandBonus() {
		return highDemandBonus;
	}

	public void setHighDemandBonus(double highDemandBonus) {
		this.highDemandBonus = highDemandBonus;
	}

	public double getLowDemandBonus() {
		return lowDemandBonus;
	}

	public void setLowDemandBonus(double lowDemandBonus) {
		this.lowDemandBonus = lowDemandBonus;
	}

	public int getMaxDemandChange() {
		return maxDemandChange;
	}

	public void setMaxDemandChange(int maxDemandChange) {
		this.maxDemandChange = maxDemandChange;
	}

	// if demand is lower than some value then price goes down if it is higher then price goes up
	// after that we randomly change demand
	public void update() {
		if (this.demandForProduct < this.lowerDemandBound) {
			this.price -= this.price * this.lowDemandBonus;
		}
		else if (this.demandForProduct > this.upperDemandBound) {
			this.price += this.price * this.highDemandBonus;
		}
		
	    Random r = new Random();
	    // demand on average gets larger by maxDeamndChange/2
	    this.demandForProduct += r.nextInt(2*maxDemandChange+1) - maxDemandChange/2;
	}


	
	public int getProductId() {
		return productId;
	}

}
