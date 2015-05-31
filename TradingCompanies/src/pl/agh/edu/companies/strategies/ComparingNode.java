package pl.agh.edu.companies.strategies;

import java.util.HashMap;

public class ComparingNode implements Node {
	private Node negative;
	private Node positive;
	private String first;
	private String comparator;
	private String second;
	private Double param;
	
	public ComparingNode(String first, String comparator, String second, Double param) {
		this.first = first;
		this.comparator = comparator;
		this.second = second;
		this.param = param;
	}
	
	public ComparingNode(String first, String comparator, String second) {
		this(first, comparator, second, 1.0);
	}
	
	// should work both for integer and double variables
	public Node getNext(HashMap<String, Object> variables) {
		boolean outcome = false;
		switch (this.comparator) {
			case "<":
				outcome = (double)variables.get(this.first) < param * (double)variables.get(this.second);
				break;
			case "<=":
				outcome = (double)variables.get(this.first) <= param * (double)variables.get(this.second);
				break;
			case ">":
				outcome = (double)variables.get(this.first) > param * (double)variables.get(this.second);
				break;
			case ">=":
				outcome = (double)variables.get(this.first) >= param * (double)variables.get(this.second);
				break;
			case "==":
				outcome = (double)variables.get(this.first) == param * (double)variables.get(this.second);
				break;
		
		}

		if (outcome) {
			return positive;
		}
		else {
			return negative;
		}
	}

	@Override
	public boolean isLast() {
		return false;
	}

	@Override
	public void setNegative(Node node) {
		this.negative = node;
	}

	@Override
	public void setPositive(Node node) {
		this.positive = node;
		
	}

	@Override
	public double getPrice(HashMap<String, Object> variables) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAmount(HashMap<String, Object> variables) {
		// TODO Auto-generated method stub
		return 0;
	}
}
