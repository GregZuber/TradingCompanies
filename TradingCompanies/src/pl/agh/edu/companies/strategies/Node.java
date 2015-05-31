package pl.agh.edu.companies.strategies;

import java.util.HashMap;

public interface Node {
	public boolean isLast();
	public void setNegative(Node node);
	public void setPositive(Node node);
	public Node getNext(HashMap<String, Object> variables);
	public double getPrice(HashMap<String, Object> variables);
	public int getAmount(HashMap<String, Object> variables);
	
}
