
public class LineItem {

	private Product product;
	private int quantity;
	private double totalCost;

	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		totalCost = (product.getMarkup()+product.getCost())*quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	

}
