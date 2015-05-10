package pl.agh.edu.pl.companies.engine;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Environment;

import pl.agh.edu.companies.initialdata.Environments;

public class Engine {
    private int turnNumber;
    private int totalTurns;
    private Environment env;
    
    
    public Engine(Environment env, int turns) {
		super();
		this.env = env;
		this.turnNumber = 0;
		this.totalTurns = turns;
	}


	public static void main(String[] args) {
        Engine engine = new Engine(Environments.getSampleEnvironment(), 1000);
        engine.simulate();
    }
    
    
    private void simulate() {
        for (turnNumber = 0 ; turnNumber < totalTurns; turnNumber++){
            beginTour();
            
            endTour();
        }
    }
    public void beginTour() {
        for (Company company: env.getCompanies()) {
            decideWhatToSell(company);
            decideWhatToBuy(company);
        }
        
        env.getMarket().update();
    }
    
    private void decideWhatToSell(Company company) {
        
    }
    private void decideWhatToBuy(Company company) {
        
    }
    
    
    public void endTour() {
        sellProductsForMarket();
        sellProductsForCompanies();
        
        // if company can produce, it produce
        for (Company company:env.getCompanies()) {
        	company.produce();
        }
        
        env.decay(); // durability of all items goes down
    }
    
    private void sellProductsForMarket() {
        
    }
    private void sellProductsForCompanies() {
        
    }
    
    
}
