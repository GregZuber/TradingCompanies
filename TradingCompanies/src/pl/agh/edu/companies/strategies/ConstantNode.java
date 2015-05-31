package pl.agh.edu.companies.strategies;

import java.util.HashMap;

public class ConstantNode implements Node {
	private Object value;
	private String comparator;
	private String first;
	private Node positive;
	private Node negative;

	public ConstantNode(String first, String comparator, Object value) {
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
		if (this.value instanceof Double) {
			switch (this.comparator) {
			case "<":
				outcome = (double) variables.get(this.first) < (double) this.value;
				break;
			case "<=":
				outcome = (double) variables.get(this.first) <= (double) this.value;
				break;
			case ">":
				outcome = (double) variables.get(this.first) > (double) this.value;
				break;
			case ">=":
				outcome = (double) variables.get(this.first) >= (double) this.value;
				break;
			case "==":
				outcome = (double) variables.get(this.first) == (double) this.value;
				break;

			}
		} else if (this.value instanceof Integer) {
			switch (this.comparator) {
			case "<":
				outcome = (int) variables.get(this.first) < (int) this.value;
				break;
			case "<=":
				outcome = (int) variables.get(this.first) <= (int) this.value;
				break;
			case ">":
				outcome = (int) variables.get(this.first) > (int) this.value;
				break;
			case ">=":
				outcome = (int) variables.get(this.first) >= (int) this.value;
				break;
			case "==":
				outcome = (int) variables.get(this.first) == (int) this.value;
				break;

			}
		} else { // bool
			switch (this.comparator) {
			case "!=":
				outcome = (boolean) variables.get(this.first) != (boolean) this.value;
				break;
			case "==":
				outcome = (boolean) variables.get(this.first) == (boolean) this.value;
				break;

			}
		}

		if (outcome) {
			return positive;
		} else {
			return negative;
		}
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
