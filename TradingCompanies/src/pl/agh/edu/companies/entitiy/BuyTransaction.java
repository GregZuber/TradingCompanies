package pl.agh.edu.companies.entitiy;

public class BuyTransaction {
	private int demandedQuantity;
	private int boughtQuantity;
	private double declaredMaxPrice;
	private double realBuyPrice;
	
	public int getDemandedQuantity() {
		return demandedQuantity;
	}
	public void setDemandedQuantity(int demandedQuantity) {
		this.demandedQuantity = demandedQuantity;
	}
	public int getBoughtQuantity() {
		return boughtQuantity;
	}
	public void setBoughtQuantity(int boughtQuantity) {
		this.boughtQuantity = boughtQuantity;
	}
	public double getDeclaredMaxPrice() {
		return declaredMaxPrice;
	}
	public void setDeclaredMaxPrice(double declaredMaxPrice) {
		this.declaredMaxPrice = declaredMaxPrice;
	}
	public double getRealBuyPrice() {
		return realBuyPrice;
	}
	public void setRealBuyPrice(double realBuyPrice) {
		this.realBuyPrice = realBuyPrice;
	}
	
}
