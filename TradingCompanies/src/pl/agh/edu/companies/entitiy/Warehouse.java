package pl.agh.edu.companies.entitiy;

public class Warehouse {
	private int productId;
	private int capacity = 10;
	private int productCount = 0;

	public Warehouse(int productId) {
		super();
		this.productId = productId;
	}

	public void addProducts(int quantity) {
		this.productCount+=quantity;
		if (this.productCount > this.capacity) {
			this.productCount = this.capacity;
		}
		
	}

	public void removeProducts(int quantity) {
		this.productCount -= quantity;
		if (this.productCount < 0) {
			this.productCount = 0;
		}
	}
	
	public boolean isEmpty() {
		return productCount == 0;
	}
	
    public int getProductCount() {
		return productCount;
	}

	public int getProductId() {
        return productId;
    }

}
