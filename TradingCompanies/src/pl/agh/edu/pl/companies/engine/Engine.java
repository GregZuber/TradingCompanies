package pl.agh.edu.pl.companies.engine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Demand;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.InputWarehouse;
import pl.agh.edu.companies.entitiy.Market;
import pl.agh.edu.companies.entitiy.OutputWarehouse;
import pl.agh.edu.companies.entitiy.ProductBuyOffer;
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
            int productId = demand.getProductId();
            List<ProductSellOffer> offers = offersService.getProductSellOffersById(productId);
            Collections.sort(offers);
            sellProductsToMarket(demand, offers);
        }
    }
    private void sellProductsToMarket(Demand demand, List<ProductSellOffer> offers) {
        int fullfilledDemand = 0;
        for (ProductSellOffer offer : offers) {
            if (demand.getDemandForProduct() > fullfilledDemand + offer.getProductsQuantity()) {
                int fullfilledDemandInThisStep = demand.getDemandForProduct() - fullfilledDemand;
                serveSellToMarket(offer, fullfilledDemandInThisStep);
                break;
            } else if (demand.getDemandForProduct() == fullfilledDemand + offer.getProductsQuantity()){
                break;
            } else {
                serveSellToMarket(offer, offer.getProductsQuantity());
                fullfilledDemand += offer.getProductsQuantity();
            }
        }        
    }


    private void serveSellToMarket(ProductSellOffer offer,
            int quantity) {
        Company company = env.getCompanyById(offer.getCompanyId());
        company.setCapital(company.getCapital() + quantity * offer.getOneProductPrice());
        removeSoldItems(company, quantity);
        addSellTransaction(offer, quantity);
    }

    private List<Integer> removeSoldItems(Company company, int quantity) {
        OutputWarehouse outputWarehouse = company.getOutputWarehouse();
        
        List<Integer> indexes = new LinkedList<Integer>();
        for (int i = 0 ; i < quantity ; i++) {
            indexes.add(i);
        }
        List<Integer> productsDurabilities = outputWarehouse.removeSoldItems(indexes);
        return productsDurabilities;
    }

    private void addSellTransaction(ProductSellOffer offer, int quantity) {
        OutputWarehouse outputWarehouse = env.getCompanyById(offer.getCompanyId()).getOutputWarehouse();
        List<ProductSellOffer> transactions = outputWarehouse.getTransactions();
        ProductSellOffer productSellOffer = transactions.get(transactions.size()-1);
        productSellOffer.setSoldQuantity(quantity);
    }
    

    private void sellProductsForCompanies() {
        List<ProductBuyOffer> productBuyOffers = offersService.getProductBuyOffers();
        Collections.shuffle(productBuyOffers);
        
        for (ProductBuyOffer buyOffer : productBuyOffers) {
            int productsId = buyOffer.getProductsId();
            List<ProductSellOffer> sellOffers = offersService.getProductSellOffersById(productsId);
            Collections.sort(sellOffers);
            sellProducts(buyOffer, sellOffers);
        }
    }


    private void sellProducts(ProductBuyOffer buyOffer,
            List<ProductSellOffer> sellOffers) {
        int fullfilledDemand = 0;
        for (ProductSellOffer sellOffer : sellOffers) {
            if (buyOffer.getProductsQuantity() > fullfilledDemand + sellOffer.getProductsQuantity()) {
                int fullfilledDemandInThisStep = buyOffer.getProductsQuantity() - fullfilledDemand;
                serveSellToCompany(buyOffer, sellOffer, fullfilledDemandInThisStep);
                break;
            } else if (buyOffer.getProductsQuantity() == fullfilledDemand + sellOffer.getProductsQuantity()){
                break;
            } else {
                serveSellToCompany(buyOffer, sellOffer, sellOffer.getProductsQuantity());
                fullfilledDemand += sellOffer.getProductsQuantity();
            }            
        }
    }


    private void serveSellToCompany(ProductBuyOffer buyOffer,
            ProductSellOffer sellOffer, int quantity) {
        Company seller = env.getCompanyById(sellOffer.getCompanyId());
        seller.setCapital(seller.getCapital() + quantity * sellOffer.getOneProductPrice());
        List<Integer> productsDurabilities = removeSoldItems(seller, quantity);
        addSellTransaction(sellOffer, quantity);

        
        Company buyer = env.getCompanyById(buyOffer.getCompanyId());
        buyer.setCapital(buyer.getCapital() - quantity * buyOffer.getRealBuyPrice());
        addBoughtItems(buyer, quantity, buyOffer.getProductsId(), productsDurabilities);
        addBuyTranscation(buyOffer, quantity, buyer);
    }

    private void addBoughtItems(Company company, int quantity, int productId, List<Integer> productsDurabilities) {
        InputWarehouse inputWarehouse = company.getInputWarehouseByProductId(productId);
        inputWarehouse.addBoughtProducts(productsDurabilities);
    }
    
    private void addBuyTranscation(ProductBuyOffer buyOffer, int quantity, Company buyer) {
        InputWarehouse inputWarehouse = buyer.getInputWarehouseByProductId(buyOffer.getProductsId());
        inputWarehouse.addHistoricalData(buyOffer);
    }
}
