package pl.agh.edu.companies.entitiy;

public class ProductPriceQuantity implements Comparable<ProductPriceQuantity>{
	private int companyId;
    private int productsId;
    private double price;
    private int quantity;
    private boolean used = false;
    
    
    public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public ProductPriceQuantity(int companyId, int productsId, double price,
			int quantity) {
		super();
		this.companyId = companyId;
		this.productsId = productsId;
		this.price = price;
		this.quantity = quantity;
	}
    
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getProductsId() {
		return productsId;
	}
	public void setProductsId(int productsId) {
		this.productsId = productsId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compareTo(ProductPriceQuantity arg0) {
		// TODO Auto-generated method stub
		return Double.compare(this.getPrice(), arg0.getPrice());
	}
	
   
	
}
