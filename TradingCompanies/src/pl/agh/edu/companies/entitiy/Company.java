package pl.agh.edu.companies.entitiy;

import java.util.List;

public class Company {
    private int id;
    private double capital;
    private double fixedCosts;
    private List<Warehouse> inputWarehouses;
    private Warehouse outputWarehouse;
    private Production production;
    
    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getFixedCosts() {
        return fixedCosts;
    }

    public void setFixedCosts(double fixedCosts) {
        this.fixedCosts = fixedCosts;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Warehouse> getInputWarehouses() {
        return inputWarehouses;
    }

    public void setInputWarehouses(List<Warehouse> inputWarehouses) {
        this.inputWarehouses = inputWarehouses;
    }

    public Warehouse getOutputWarehouse() {
        return outputWarehouse;
    }

    public void setOutputWarehouse(Warehouse outputWarehouse) {
        this.outputWarehouse = outputWarehouse;
    }
    
    
}
