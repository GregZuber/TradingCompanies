package pl.agh.edu.companies.entitiy;

import java.util.ArrayList;
import java.util.List;

import pl.agh.edu.companies.strategies.IPricePolicy;
import pl.agh.edu.companies.strategies.IQuantityPolicy;

public class Company {
	private int id;
	private double capital;
	private double turnCost;

	private List<Warehouse> inputWarehouses;
	private List<History> buyingHistory = new ArrayList<History>();
	private Warehouse outputWarehouse;
	private History sellingHistory;
	private IPricePolicy inputPricePolicy;
	private IPricePolicy outputPricePolicy;
	private IQuantityPolicy inputQuantityPolicy;
	private IQuantityPolicy outputQuantityPolicy;
	
	public IPricePolicy getInputPricePolicy() {
		return inputPricePolicy;
	}

	public void setInputPricePolicy(IPricePolicy inputPricePolicy) {
		this.inputPricePolicy = inputPricePolicy;
	}

	public IPricePolicy getOutputPricePolicy() {
		return outputPricePolicy;
	}

	public void setOutputPricePolicy(IPricePolicy outputPricePolicy) {
		this.outputPricePolicy = outputPricePolicy;
	}

	public IQuantityPolicy getInputQuantityPolicy() {
		return inputQuantityPolicy;
	}

	public void setInputQuantityPolicy(IQuantityPolicy inputQuantityPolicy) {
		this.inputQuantityPolicy = inputQuantityPolicy;
	}

	public IQuantityPolicy getOutputQuantityPolicy() {
		return outputQuantityPolicy;
	}

	public void setOutputQuantityPolicy(IQuantityPolicy outputQuantityPolicy) {
		this.outputQuantityPolicy = outputQuantityPolicy;
	}

	public History getSellingHistory() {
		return sellingHistory;
	}

	public void setSellingHistory(History sellingHistory) {
		this.sellingHistory = sellingHistory;
	}

	public List<History> getBuyingHistory() {
		return buyingHistory;
	}

	public Company(int id, double capital, double turnCost) {
		super();
		this.id = id;
		this.capital = capital;
		this.turnCost = turnCost;
	}

	public double getTurnCost() {
		return turnCost;
	}

	public double getCapital() {
		return capital;
	}
	
	public void setCapital(double capital) {
        this.capital = capital;
    }

	public int getId() {
		return id;
	}

	public List<Warehouse> getInputWarehouses() {
		return inputWarehouses;
	}

	public void setInputWarehouses(List<Warehouse> inputWarehouses) {
		this.inputWarehouses = inputWarehouses;
		if (this.inputWarehouses != null) {
			for (Warehouse warehouse : inputWarehouses) {
				this.buyingHistory.add(new History(this.getId(),warehouse.getProductId()));
			}
		}
		
	}
	
	public Warehouse getInputWarehouseByProductId(int productId) {
	    for (Warehouse inputWarehouse : inputWarehouses) {
	        if (inputWarehouse.getProductId() == productId) {
	            return inputWarehouse;
	        }
	    }
	    return null;
    }
	
	public History getBuyingHistoryByProductId(int productId) {
	    for (History single : buyingHistory) {
	        if (single.getProductId() == productId) {
	            return single;
	        }
	    }
	    return null;
    }

	public Warehouse getOutputWarehouse() {
		return outputWarehouse;
	}

	public void setOutputWarehouse(Warehouse outputWarehouse) {
		this.outputWarehouse = outputWarehouse;
		this.sellingHistory = new History(this.getId(),outputWarehouse.getProductId());
	}

	public boolean canProduce() {
		boolean success = true;
		if (getInputWarehouses() != null) {
			for(Warehouse warehouse:getInputWarehouses()) {
				success = success  && !warehouse.isEmpty();
			}
			
		}
		
		
		return success;
	}
	
	public void produce() {
		this.capital -= this.turnCost;
		if (canProduce()) {
			if (getInputWarehouses() != null) {
				for(Warehouse warehouse:getInputWarehouses()) {
					warehouse.removeProducts(1);
				}
			}
			
			getOutputWarehouse().addProducts(1);
		}
	}
	
}
