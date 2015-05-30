package pl.agh.edu.companies.strategies;

import java.util.HashMap;

public class BooleanNode implements Node {
	
	private String name;
	private Node positive;
	private Node negative;

	public BooleanNode(String name) {
		this.name = name;
	}

	@Override
	public boolean isLast() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNegative(Node node) {
		// TODO Auto-generated method stub
		this.negative = node;
	}

	@Override
	public void setPositive(Node node) {
		// TODO Auto-generated method stub
		this.positive = node;
	}

	@Override
	public Node getNext(HashMap<String, Object> variables) {
		// TODO Auto-generated method stub
		if ((boolean)variables.get(name)) {
			return positive;
		}
		else {
			return negative;
		}
	}

}
