package pl.agh.edu.companies.strategies;

import java.util.HashMap;
import java.util.List;

public class DecisionTree {
	private List<Double> parameters;
	private Node rootNode;
	
	public DecisionTree(List<Double> parameters, Node rootNode) {
		super();
		this.parameters = parameters;
		this.rootNode = rootNode;
	}

	public List<Double> getParameters() {
		return parameters;
	}

	public void setParameters(List<Double> parameters) {
		this.parameters = parameters;
	}

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	public double getPrice(HashMap<String, Object> input) {
		Node node = this.rootNode;
		while (!node.isLast()) {
			node = node.getNext(input);
		}
		
		return node.getPrice(input);
	}
	public int getAmount(HashMap<String, Object> input) {
		
		Node node = this.rootNode;
		while (!node.isLast()) {
			node = node.getNext(input);
		}
		
		return node.getAmount(input);
	}
	
}
