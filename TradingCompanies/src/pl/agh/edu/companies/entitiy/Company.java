package pl.agh.edu.companies.entitiy;

import java.util.List;

public class Company {
	private int id;
	private double capital;
    private double fixedCosts;
	private double productionCost;

	private List<InputWarehouse> inputWarehouses;
	private OutputWarehouse outputWarehouse;

	public Company(int id, double capital, double fixedCosts, double productionCost) {
		super();
		this.id = id;
		this.capital = capital;
		this.fixedCosts = fixedCosts;
		this.productionCost = productionCost;
	}

	public double getProductionCost() {
		return productionCost;
	}

	public double getCapital() {
		return capital;
	}
	
	public void setCapital(double capital) {
        this.capital = capital;
    }

	public double getFixedCosts() {
		return fixedCosts;
	}

	public int getId() {
		return id;
	}

	public List<InputWarehouse> getInputWarehouses() {
		return inputWarehouses;
	}

	public void setInputWarehouses(List<InputWarehouse> inputWarehouses) {
		this.inputWarehouses = inputWarehouses;
	}

	public OutputWarehouse getOutputWarehouse() {
		return outputWarehouse;
	}

	public void setOutputWarehouse(OutputWarehouse outputWarehouse) {
		this.outputWarehouse = outputWarehouse;
	}

	public boolean canProduce() {
		boolean success = true;
		for(InputWarehouse warehouse:getInputWarehouses()) {
			success = success  && warehouse.canConsume();
		}
		
		return success;
	}
	
	public void produce() {
		if (canProduce()) {
			for(InputWarehouse warehouse:getInputWarehouses()) {
				warehouse.consume();
			}
			
			getOutputWarehouse().produce();
		}
	}
	
}
