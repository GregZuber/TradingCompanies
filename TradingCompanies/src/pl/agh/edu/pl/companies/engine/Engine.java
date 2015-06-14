package pl.agh.edu.pl.companies.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.Demand;
import pl.agh.edu.companies.entitiy.Environment;
import pl.agh.edu.companies.entitiy.History;
import pl.agh.edu.companies.entitiy.HistoryOfOneProductPrices;
import pl.agh.edu.companies.entitiy.Market;
import pl.agh.edu.companies.entitiy.ProductPriceQuantity;
import pl.agh.edu.companies.entitiy.Warehouse;
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
        Engine engine = new Engine(Environments.getSampleEnvironment(), 20, OffersService.getOffersServiceInstance());
        engine.simulate();
    }
    
    private void simulate() {
        for (turnNumber = 0 ; turnNumber < totalTurns; turnNumber++){
            beginTour();
            endTour();
            System.out.println("Turn number: " + turnNumber);
            System.out.println("===============");
            for (HistoryOfOneProductPrices history : this.env.getHistory().getProductsPrices()) {
            	System.out.println(history.getProductId() + " " + history.getLastNonZero());
            	System.out.println("======buys=========");
            	for (ProductPriceQuantity s : this.offersService.getProductBuyOffers()) {
            		System.out.println(s.getCompanyId() + " " + s.getProductsId() + " " + s.getPrice());
            	}
            	System.out.println("=======sells========");
            	for (ProductPriceQuantity s : this.offersService.getProductSellOffers()) {
            		System.out.println(s.getCompanyId() + " " +s.getProductsId() + " " + s.getPrice());
            	}
            }
            
            System.out.println("===============");
            for (Company company : this.env.getCompanies()) {
            	printCompanyState(company);
            }
            
            this.offersService.clearOffers();
        }
    }
    
    private void printCompanyState(Company company) {
		System.out.println("Company id:" + company.getId());
		System.out.println("Company capital: " + company.getCapital());
		List<Warehouse> inputWarehouses = company.getInputWarehouses();
		if (inputWarehouses != null) {
			for (Warehouse warehouse: inputWarehouses) {
				System.out.println("Company input " +warehouse.getProductId()  + " count: " + warehouse.getProductCount());
			}
		}
		
		System.out.println("Company output count:" + company.getOutputWarehouse().getProductCount());
		System.out.println("===============");
		
	}

	public void beginTour() {
    	List<ProductPriceQuantity> productBuyOffers = new ArrayList<ProductPriceQuantity>();
    	List<ProductPriceQuantity> productSellOffers = new ArrayList<ProductPriceQuantity>();
    	
    	
        for (Company company: env.getCompanies()) {
        	productSellOffers.add(decideWhatToSell(company));
        	productBuyOffers.addAll(decideWhatToBuy(company));
        }
        
        this.offersService.setProductBuyOffers(productBuyOffers);
        this.offersService.setProductSellOffers(productSellOffers);
        
        env.getMarket().update();
    }
    
    // TODO: this won't work until we add body to some strategies and assing them in Environments class
    private ProductPriceQuantity decideWhatToSell(Company company) {
    	int productId =  company.getOutputWarehouse().getProductId();
    	int quantity = company.getOutputQuantityPolicy().getQuantity(company, productId, env);
    	double price = company.getOutputPricePolicy().getPrice(company, productId, env);
    	
    	
    	ProductPriceQuantity sellOffer = new ProductPriceQuantity(company.getId(), company.getOutputWarehouse().getProductId(), price,quantity);
        company.getSellingHistory().addProposedOffer(price,quantity);
    	return sellOffer;
    }

    // TODO: this won't work until we add body to some strategies and assing them in Environments class
    private List<ProductPriceQuantity> decideWhatToBuy(Company company) {
    	List<ProductPriceQuantity> offers = new ArrayList<ProductPriceQuantity>();
    	if (company.getInputWarehouses() != null) {
    		for (Warehouse warehouse : company.getInputWarehouses()) {
    			int productId = warehouse.getProductId();
    			double price = company.getInputPricePolicy().getPrice(company, productId, env);
    			int quantity = company.getInputQuantityPolicy().getQuantity(company, productId, env);
    			ProductPriceQuantity buyOffer = new ProductPriceQuantity(company.getId(), productId, price,quantity);
    			company.getBuyingHistoryByProductId(warehouse.getProductId()).addProposedOffer(price, quantity);
    			
    			offers.add(buyOffer);
    		}
    	}
    	
    	return offers;
	}
    
    public void endTour() {
    	List<ProductPriceQuantity> productSellOffers = new ArrayList<ProductPriceQuantity>();
        for (ProductPriceQuantity sellOffer : offersService.getProductSellOffers()) {
        	if (sellOffer.getQuantity() == 0) {
        		sellOfferRejected(sellOffer);
        	} else {
        		productSellOffers.add(sellOffer);
        	}
        }
        offersService.setProductSellOffers(productSellOffers);
        
        sellProductsForMarket();
        sellProductsForCompanies();
        
        // if company can produce, it produce
        for (Company company:env.getCompanies()) {
        	company.produce();
        }
        
        updateHistoryOfPrices();
    }

	private void updateHistoryOfPrices() {
		// teraz trzeba doda� dodawanie �redniej ceny z ostatniej tury
        for (HistoryOfOneProductPrices hist:env.getHistory().getProductsPrices()) {
        	int productId = hist.getProductId();
        	double accumulatedPrice = 0.0;
        	int accumulatedTransactions = 0;
        	int nonZeroTransactions = 0;
        	
        	for (Company company: env.getCompanies()) {
        		// check output
        		if (company.getOutputWarehouse().getProductId() == productId) {
        			ProductPriceQuantity transaction = company.getSellingHistory().getLastTransaction();
        			if (transaction != null) {
        				if (transaction.getQuantity() != 0) {
        					nonZeroTransactions++;
        					accumulatedPrice += transaction.getPrice();
        				} 
        				
        				accumulatedTransactions++;
        			}
        		}
        		// check input
        		if (company.getInputWarehouses() != null) {
        			for (Warehouse warehouse :company.getInputWarehouses() ) {
        				if (warehouse.getProductId() == productId) {
                			ProductPriceQuantity transaction = company.getBuyingHistoryByProductId(warehouse.getProductId()).getLastTransaction();
                			if (transaction != null) {
                				if (transaction.getQuantity() != 0) {
                					nonZeroTransactions++;
                					accumulatedPrice += transaction.getPrice();
                				} 
                				
                				accumulatedTransactions++;
                			}
                		}
        			}
        		}
        	}
        	
        	if (nonZeroTransactions > 0) {
        		hist.addPrice(accumulatedPrice / nonZeroTransactions);
        	}
        	else {
        		hist.addPrice(0.0);
        	}
        	
        }
	}
    
    private void sellProductsForMarket() {
        Market market = env.getMarket();
        List<Demand> demands = market.getDemands();
        
        // tutaj s� jedynie produkty ko�cowe, nie uczestnicz�ce w wymianie pomi�dzy firmami
        // inaczej mo�na b�dzie jedn� ofert� sprzedawa� dwa razy
        for (Demand demand : demands) {
            List<ProductPriceQuantity> offers = offersService.getProductSellOffersById(demand.getProductId());
            Collections.sort(offers);
            sellProductsToMarket(demand, offers);
        }
    }
    
    // podobne do sellProductsToCompany, tylko nie ma dodawanej transakcji zakupu przez rynek
    // jak ma to miejsce przy zakupie przez firm�
    private void sellProductsToMarket(Demand demand, List<ProductPriceQuantity> offers) {
        for (ProductPriceQuantity sellOffer : offers) {
        	// je�eli cena jest wy�sza od tej jak� rynek jest w stanie zap�aci�, to oferta jest odrzucona
        	if (sellOffer.getPrice() > demand.getPrice()) {
        		sellOfferRejected(sellOffer);
        	}
        	else {
                serveSellToMarket(sellOffer, sellOffer.getQuantity());
        	}
        }    
    }
    
    private void sellProductsForCompanies() {
    	// randomizujemy oferty kupna
        List<ProductPriceQuantity> productBuyOffers = offersService.getProductBuyOffers();
        Collections.shuffle(productBuyOffers);
        
        
        
        
        
        
        
        // i pr�bujemy po kolei ka�d� spe�ni�
        for (ProductPriceQuantity buyOffer : productBuyOffers) {
            int productsId = buyOffer.getProductsId();
            List<ProductPriceQuantity> sellOffers = offersService.getProductSellOffersById(productsId);
            Collections.sort(sellOffers);
            // matchujemy produkty ze sob�
            sellProducts(buyOffer, sellOffers);
        }
    }

    private void sellProducts(ProductPriceQuantity buyOffer, List<ProductPriceQuantity> sellOffers) {
        int fullfilledDemand = 0;
        double payedMoney = 0.0;
        
        // sell offers are sorted by price in ascending order
        for (ProductPriceQuantity sellOffer : sellOffers) {
        	// je�eli oferta sprzeda�y zosta�a wykorzystana przez inna ofert� kupna
        	if (sellOffer.isUsed()) {
        		continue;
        	}
        	
        	// je�eli cena sprzeda�y jest wy�sza od ceny kupna
        	// to nie ma �adnej transakcji
        	
        	
        	if (sellOffer.getPrice() > buyOffer.getPrice()) {
        		sellOfferRejected(sellOffer);
        	}
        	// je�eli nasze potrzeby jeszcze nie s� spe�nione
        	// kupujemy wszystko
        	else if (buyOffer.getQuantity() > fullfilledDemand + sellOffer.getQuantity()) {
            	fullfilledDemand += sellOffer.getQuantity();
            	payedMoney += sellOffer.getQuantity() * sellOffer.getPrice();
                serveSellToCompany(buyOffer, sellOffer, sellOffer.getQuantity());
            // je�eli nasze potrzeby s� spe�nione na styk
            // kupujemy wszystko
            } else if (buyOffer.getQuantity() == fullfilledDemand + sellOffer.getQuantity()){
            	serveSellToCompany(buyOffer, sellOffer, sellOffer.getQuantity());
            	payedMoney += sellOffer.getQuantity() * sellOffer.getPrice();
            	fullfilledDemand = buyOffer.getQuantity();
            // je�eli nasz demand jest ju� fullfilled, pozosta�e oferty odrzucamy
            } else if (buyOffer.getQuantity() == fullfilledDemand){
            	sellOfferRejected(sellOffer);
            // je�eli podana oferta daje nawet wi�cej ni� potrzebujemy
            // bierzemy tylko tyle ile potrzebujemy
            } else {
            	int fullfilledThisTurn = buyOffer.getQuantity() - fullfilledDemand;
                serveSellToCompany(buyOffer, sellOffer, fullfilledThisTurn);
                payedMoney += fullfilledThisTurn * sellOffer.getPrice();
                fullfilledDemand = buyOffer.getQuantity();
            }            
        }
        
        
        addBuyTranscation(buyOffer, fullfilledDemand, payedMoney); 
    }

    private void removeSoldItems(Company company, int quantity) {
        Warehouse outputWarehouse = company.getOutputWarehouse();
        
        List<Integer> indexes = new LinkedList<Integer>();
        for (int i = 0 ; i < quantity ; i++) {
            indexes.add(i);
        }
        
        outputWarehouse.removeProducts(quantity);
    }

    private void serveSellToMarket(ProductPriceQuantity offer,int quantity) {
        Company company = env.getCompanyById(offer.getCompanyId());
        company.setCapital(company.getCapital() + quantity * offer.getPrice());
        removeSoldItems(company, quantity);
        addSellTransaction(offer, quantity);
    }
    
    private void serveSellToCompany(ProductPriceQuantity buyOffer, ProductPriceQuantity sellOffer, int quantity) {
        // dodaj pieni�dze, usu� produkty
    	sellOffer.setUsed(true);
    	Company seller = env.getCompanyById(sellOffer.getCompanyId());
        seller.setCapital(seller.getCapital() + quantity * sellOffer.getPrice());
        removeSoldItems(seller, quantity);
        
        // dodaj produkty, usu� pieni�dze
        Company buyer = env.getCompanyById(buyOffer.getCompanyId());
        buyer.setCapital(buyer.getCapital() - quantity * sellOffer.getPrice());
        addBoughtItems(buyer, quantity, buyOffer.getProductsId());
        
        // dodaj sko�czone transakcje
        addSellTransaction(sellOffer, quantity);
    }
    
    // added when nothing was bought
    private void sellOfferRejected(ProductPriceQuantity sellOffer) {
    	History history = env.getCompanyById(sellOffer.getCompanyId()).getSellingHistory();
    	history.addRealTransaction(0, 0);
	}

    private void addBoughtItems(Company company, int quantity, int productId) {
        Warehouse inputWarehouse = company.getInputWarehouseByProductId(productId);
        inputWarehouse.addProducts(quantity);
    }
    
    private void addSellTransaction(ProductPriceQuantity offer, int quantity) {
        History history = env.getCompanyById(offer.getCompanyId()).getSellingHistory();
        history.addRealTransaction(offer.getPrice(), quantity);
    }
    
    private void addBuyTranscation(ProductPriceQuantity buyOffer, int quantity, double payedPrice) {
    	Company company = env.getCompanyById(buyOffer.getCompanyId());
    	History history = company.getBuyingHistoryByProductId(buyOffer.getProductsId());
    	history.addRealTransaction(payedPrice/quantity, quantity); // mean price we payed for everything
    }
}
