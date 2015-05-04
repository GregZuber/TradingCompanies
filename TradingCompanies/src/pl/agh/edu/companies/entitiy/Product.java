package pl.agh.edu.companies.entitiy;

public class Product {
    private double price;
    private int durability;
    private int type;
    private double takenWarehouseSpace;
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getDurability() {
        return durability;
    }
    public void setDurability(int durability) {
        this.durability = durability;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public double getTakenWarehouseSpace() {
        return takenWarehouseSpace;
    }
    public void setTakenWarehouseSpace(double takenWarehouseSpace) {
        this.takenWarehouseSpace = takenWarehouseSpace;
    }
    
}
