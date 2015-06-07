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
    
    private ProductPriceQuantity decideWhatToSell(Company company) {
    	ProductPriceQuantity sellOffer = new ProductPriceQuantity(company.getId(), company.getOutputWarehouse().getProductId(), 0,0);
        company.getSellingHistory().addProposedOffer(0, 0);
    	return sellOffer;
    }
    
    private List<ProductPriceQuantity> decideWhatToBuy(Company company) {
    	List<ProductPriceQuantity> offers = new ArrayList<ProductPriceQuantity>();
    	if (company.getInputWarehouses() != null) {
    		for (Warehouse warehouse : company.getInputWarehouses()) {
    			ProductPriceQuantity buyOffer = new ProductPriceQuantity(company.getId(), warehouse.getProductId(), 0,0);
    			company.getBuyingHistoryByProductId(warehouse.getProductId()).addProposedOffer(0, 0);
    			
    			
    			offers.add(buyOffer);
    		    
    		}
    	}
    	
    	return offers;
	}
    
    public void endTour() {
        sellProductsForMarket();
        sellProductsForCompanies();
        
        // if company can produce, it produce
        for (Company company:env.getCompanies()) {
        	company.produce();
        }
        
        updateHistoryOfPrices();
    }

	private void updateHistoryOfPrices() {
		// teraz trzeba dodaæ dodawanie œredniej ceny z ostatniej tury
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
        
        // tutaj s¹ jedynie produkty koñcowe, nie uczestnicz¹ce w wymianie pomiêdzy firmami
        // inaczej mo¿na bêdzie jedn¹ ofert¹ sprzedawaæ dwa razy
        for (Demand demand : demands) {
            List<ProductPriceQuantity> offers = offersService.getProductSellOffersById(demand.getProductId());
            Collections.sort(offers);
            sellProductsToMarket(demand, offers);
        }
    }
    
    // podobne do sellProductsToCompany, tylko nie ma dodawanej transakcji zakupu przez rynek
    // jak ma to miejsce przy zakupie przez firmê
    private void sellProductsToMarket(Demand demand, List<ProductPriceQuantity> offers) {
        for (ProductPriceQuantity sellOffer : offers) {
        	// je¿eli cena jest wy¿sza od tej jak¹ rynek jest w stanie zap³aciæ, to oferta jest odrzucona
        	if (sellOffer.getPrice() > demand.getPrice()) {
        		sellOfferRejected(sellOffer);
        	}
        	else if (sellOffer.getQuantity() == 0) {
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
        
        // i próbujemy po kolei ka¿d¹ spe³niæ
        for (ProductPriceQuantity buyOffer : productBuyOffers) {
            int productsId = buyOffer.getProductsId();
            List<ProductPriceQuantity> sellOffers = offersService.getProductSellOffersById(productsId);
            Collections.sort(sellOffers);
            // matchujemy produkty ze sob¹
            sellProducts(buyOffer, sellOffers);
        }
    }

    private void sellProducts(ProductPriceQuantity buyOffer, List<ProductPriceQuantity> sellOffers) {
        int fullfilledDemand = 0;
        double payedMoney = 0.0;
        
        // sell offers are sorted by price in ascending order
        for (ProductPriceQuantity sellOffer : sellOffers) {
        	// je¿eli cena sprzeda¿y jest wy¿sza od ceny kupna
        	// to nie ma ¿adnej transakcji
        	if (sellOffer.getPrice() > buyOffer.getPrice()) {
        		sellOfferRejected(sellOffer);
        	} else if (sellOffer.getQuantity() == 0) {
        		sellOfferRejected(sellOffer);
        	}
        	// je¿eli nasze potrzeby jeszcze nie s¹ spe³nione
        	// kupujemy wszystko
        	else if (buyOffer.getQuantity() > fullfilledDemand + sellOffer.getQuantity()) {
            	fullfilledDemand += sellOffer.getQuantity();
            	payedMoney += sellOffer.getQuantity() * sellOffer.getPrice();
                serveSellToCompany(buyOffer, sellOffer, sellOffer.getQuantity());
            // je¿eli nasze potrzeby s¹ spe³nione na styk
            // kupujemy wszystko
            } else if (buyOffer.getQuantity() == fullfilledDemand + sellOffer.getQuantity()){
            	serveSellToCompany(buyOffer, sellOffer, sellOffer.getQuantity());
            	payedMoney += sellOffer.getQuantity() * sellOffer.getPrice();
            	fullfilledDemand = buyOffer.getQuantity();
            // je¿eli nasz demand jest ju¿ fullfilled, pozosta³e oferty odrzucamy
            } else if (buyOffer.getQuantity() == fullfilledDemand){
            	sellOfferRejected(sellOffer);
            // je¿eli podana oferta daje nawet wiêcej ni¿ potrzebujemy
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
        // dodaj pieni¹dze, usuñ produkty
    	Company seller = env.getCompanyById(sellOffer.getCompanyId());
        seller.setCapital(seller.getCapital() + quantity * sellOffer.getPrice());
        removeSoldItems(seller, quantity);
        
        // dodaj produkty, usuñ pieni¹dze
        Company buyer = env.getCompanyById(buyOffer.getCompanyId());
        buyer.setCapital(buyer.getCapital() - quantity * sellOffer.getPrice());
        addBoughtItems(buyer, quantity, buyOffer.getProductsId());
        
        // dodaj skoñczone transakcje
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
    	history.addRealTransaction(payedPrice, quantity);
    }
}
