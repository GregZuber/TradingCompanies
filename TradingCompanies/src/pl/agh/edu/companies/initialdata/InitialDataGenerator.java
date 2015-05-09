package pl.agh.edu.companies.initialdata;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.CompanyType;
import pl.agh.edu.companies.entitiy.Demand;
import pl.agh.edu.companies.entitiy.HistoryOfOneProductPrices;
import pl.agh.edu.companies.entitiy.HistoryOfPrices;
import pl.agh.edu.companies.entitiy.Market;
import pl.agh.edu.companies.entitiy.Product;
import pl.agh.edu.companies.entitiy.Production;

public class InitialDataGenerator {
    private static final int NUMBER_OF_COMPANIES_OF_ONE_TYPE = 5;
    
    /*public static void main(String[] args) {
        InitialDataGenerator initialDataGenerator = new InitialDataGenerator();
        initialDataGenerator.generateInitialData();
    }*/

    public HistoryOfPrices prepareHistoryOfPrices() {
        HistoryOfPrices historyOfPrices = new HistoryOfPrices();
        
        HistoryOfOneProductPrices historyOfOneProductPrices = new HistoryOfOneProductPrices();
        historyOfOneProductPrices.setProductId(0);
        List<Double> averagePrices = new LinkedList<Double>();
        averagePrices.add(400.0);
        averagePrices.add(450.0);
        averagePrices.add(425.0);
        historyOfOneProductPrices.setAveragePrices(averagePrices);
        return historyOfPrices;
    }

    public List<Company> generateCompanies(CompanyType companyType) {
        List<Company> companies = new LinkedList<Company>();
        for (int i = 0 ; i < NUMBER_OF_COMPANIES_OF_ONE_TYPE ; i++) {
            Company company = generateCompany(companyType);
            companies.add(company);
        }
        return companies;
    }

    private Company generateCompany(CompanyType companyType) {
        Company company = new Company();
        company.setCapital(20000);
        company.setFixedCosts(4000);
        Production production = generateProduction(companyType);
        company.setProduction(production);
        List<Product> productsInWarehouse = new LinkedList<Product>();
        company.setProductsInWarehouse(productsInWarehouse);
        company.setWarehouseSize(20);
        return company;
    }

    private Production generateProduction(CompanyType companyType) {
        Production production = new Production();
        List<Product> neededProducts = prepareNeededProducts(companyType);
        production.setNeededProducts(neededProducts);
        production.setProductionCost(100);
        production.setTourPartNeededToProductOneProduct(0.2);
        return production;
    }

    private List<Product> prepareNeededProducts(CompanyType companyType) {
        List<Product> neededProducts = new LinkedList<Product>();
        if (companyType == CompanyType.PROCESSING) {
            Product product = new Product();
            product.setDurability(10);
            product.setTakenWarehouseSpace(1);
            product.setType(1);
            neededProducts.add(product);
        }
        
        return neededProducts;
    }
    
    public Market prepareMarket() {
        Market market = new Market();
        market.setDemands(prepareDemands());
        return market;
    }

    private List<Demand> prepareDemands() {
        List<Demand> demands = new LinkedList<Demand>();
        Demand demand = new Demand();
        demand.setDemandForProduct(20);
        demand.setProductId(1);
        return demands;
    }
}
