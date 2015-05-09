package pl.agh.edu.pl.companies.engine;

import java.util.List;

import pl.agh.edu.companies.entitiy.Company;
import pl.agh.edu.companies.entitiy.CompanyType;
import pl.agh.edu.companies.entitiy.HistoryOfPrices;
import pl.agh.edu.companies.entitiy.Market;
import pl.agh.edu.companies.initialdata.InitialDataGenerator;

public class Engine {
    private int tourNumber;
    private int numberOfLastTour;
    
    private InitialDataGenerator initialDataGenerator = new InitialDataGenerator();
    private List<Company> miningCompanies = initialDataGenerator.generateCompanies(CompanyType.MINING);
    private List<Company> processingCompanies = initialDataGenerator.generateCompanies(CompanyType.PROCESSING);
    private Market market = initialDataGenerator.prepareMarket();
    private HistoryOfPrices historyOfPrices = initialDataGenerator.prepareHistoryOfPrices();
    
    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.simulate();
    }
    
    
    private void simulate() {
        beginTour();
    }
    public void beginTour() {
        
    }
    public void endTour() {
        
    }
    public int getTourNumber() {
        return tourNumber;
    }
    public void setTourNumber(int tourNumber) {
        this.tourNumber = tourNumber;
    }
    public int getNumberOfLastTour() {
        return numberOfLastTour;
    }
    public void setNumberOfLastTour(int numberOfLastTour) {
        this.numberOfLastTour = numberOfLastTour;
    }
    
}
