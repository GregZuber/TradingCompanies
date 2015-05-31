package pl.agh.edu.companies.strategies;

import java.util.HashMap;

public class FinalNode implements Node {
	
	private boolean isAmountFixed;
	private double amountCoefficient;
	private String amount;
	private double priceCoefficient;
	private String price;
	private int fixedAmount;

	public FinalNode(String price, double priceCoefficient, String amount, double amountCoefficient) {
		this.price = price;
		this.priceCoefficient = priceCoefficient;
		this.amount = amount;
		this.amountCoefficient = amountCoefficient;
		this.isAmountFixed = false;
	}
	
	public FinalNode(String price, double priceCoefficient, int fixedAmount) {
		this.price = price;
		this.priceCoefficient = priceCoefficient;
		this.fixedAmount = fixedAmount;
		this.isAmountFixed = true;
	}

	@Override
	public boolean isLast() {
		return true;
	}

	@Override
	public void setNegative(Node node) {
		
	}

	@Override
	public void setPositive(Node node) {
	}

	@Override
	public Node getNext(HashMap<String, Object> variables) {
		return null;
	}

	@Override
	public double getPrice(HashMap<String, Object> variables) {
		return (double)variables.get(this.price) * this.priceCoefficient;
	}

	@Override
	public int getAmount(HashMap<String, Object> variables) {
		if (this.isAmountFixed) {
			return this.fixedAmount;
		}
		else {
			return (int) Math.round((int)variables.get(this.amount) * this.amountCoefficient);
		}
	}

}
