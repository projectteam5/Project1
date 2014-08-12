
public class Stock {
	private int units;
	private Product product;
	
	public Stock(int units, Product product) {
		this.units = units;
		this.product = product;
	}
	public void viewStockList(){
		System.out.println("Product: "+product.getName+"\n Units: "+this.units);
	}
	public void addStockToList(Product product, int units){
		
	}
	public void removeStockFromList(Product product, int units){
		
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
