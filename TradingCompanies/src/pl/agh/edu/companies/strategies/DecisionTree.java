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

	public double evaluate(HashMap<String, Object> input) {
		
		return 0.0;
	}
	
	
}
