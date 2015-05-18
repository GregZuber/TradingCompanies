package pl.agh.edu.pl.companies.engine;

import java.util.Collections;
import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Demand;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.Market;
import pl.agh.edu.companies.entitiy.ProductSellOffer;
import pl.agh.edu.companies.initialdata.Environments;

public class Engine {
    private int turnNumber;
    private int totalTurns;
    private Environment env;
    private OffersService offersService;
    
    
    public Engine(Environment env, int turns, OffersService offersService) {
		super();
		this.env = env;
		this.turnNumber = 0;
		this.totalTurns = turns;
		this.offersService = offersService;
	}


	public static void main(String[] args) {
        Engine engine = new Engine(Environments.getSampleEnvironment(), 1000, OffersService.getOffersServiceInstance());
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
        if (company.getInputWarehouses().size() == 0) {
        	return;
        }
        
        
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
        Market market = env.getMarket();
        List<Demand> demands = market.getDemands();
        
        for (Demand demand : demands) {
            int fullfilledDemand = 0;
            int productId = demand.getProductId();
            List<ProductSellOffer> offers = offersService.getProductSellOffersById(productId);
            Collections.sort(offers);
            for (ProductSellOffer offer : offers) {
                if (demand.getDemandForProduct() > fullfilledDemand + offer.getProductsQuantity()) {
                    int fullfilledDemandInThisStep = demand.getDemandForProduct() - fullfilledDemand;
                    serveSell(offer.getCompanyId(), productId, fullfilledDemandInThisStep);
                    break;
                } else if (demand.getDemandForProduct() == fullfilledDemand + offer.getProductsQuantity()){
                    break;
                } else {
                    serveSell(offer.getCompanyId(), productId, offer.getProductsQuantity());
                    fullfilledDemand += offer.getProductsQuantity();
                }
                
            }
        }
    }
    private void serveSell(int companyId, int productId, int quantityOfProduct) {
        Company company = env.getCompanyById(companyId);
    }


    private void sellProductsForCompanies() {
        
    }
    
    
}
