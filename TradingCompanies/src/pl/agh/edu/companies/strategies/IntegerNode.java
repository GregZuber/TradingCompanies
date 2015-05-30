package pl.agh.edu.companies.strategies;

import java.util.HashMap;

public class IntegerNode implements Node {
	private Integer value;
	private String comparator;
	private String first;
	private Node positive;
	private Node negative;

	public IntegerNode(String first, String comparator, Integer value) {
		this.first = first;
		this.comparator = comparator;
		this.value = value;
		
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
	public Node getNext(HashMap<String, Object> variables) {
		boolean outcome = false;
		switch (this.comparator) {
			case "<":
				outcome = (int)variables.get(this.first) < this.value;
				break;
			case "<=":
				outcome = (int)variables.get(this.first) <=  this.value;
				break;
			case ">":
				outcome = (int)variables.get(this.first) >  this.value;
				break;
			case ">=":
				outcome = (int)variables.get(this.first) >=  this.value;
				break;
			case "==":
				outcome = (int)variables.get(this.first) ==  this.value;
				break;
		
		}
		
		if (outcome) {
			return positive;
		}
		else {
			return negative;
		}
	}
}
