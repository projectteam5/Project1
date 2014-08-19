import java.util.ArrayList;

public class Product {
	private String productID;
	private String name;
	private double cost;
	private double markup;
	private Supplier supplier;
	private boolean active;

	public Product(String productID, String name, double cost, double markup, Supplier supplier) {
		this.productID = productID;
		this.name = name;
		this.cost = cost;
		this.markup = markup;
		this.supplier = supplier;	
		this.active = true;
	}
	
	public Product(String productID, String name, double cost, double markup, Supplier supplier, boolean active){
		this.productID = productID;
		this.name = name;
		this.cost = cost;
		this.markup = markup;
		this.supplier = supplier;
		this.active = active;
	}
	

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getMarkup() {
		return markup;
	}

	public void setMarkup(double markup) {
		this.markup = markup;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	//1
	public String addProductToList(Product product){
		//Will take in a product and add it to the products list in the driver class
		boolean duplicate = false;
		for(Product p: RetailSystem.getInstance().getProducts()){
			if(p.getProductID() == product.getProductID()){
				duplicate = true;
				return product.getProductID();
			}	
			
			}if(!duplicate){
				RetailSystem.getInstance().getProducts().add(product);
				return product.getProductID();
			}
			return "Function to add Product to list has run";
	}
	//2
	public boolean changeProductToInactive(Product product){
		//Will remove a product from the products list in the driver class
		product.setActive(false);
		return product.isActive();
	}
	//3
	public String viewProduct(Product product){
		//Temp println in place of GUI
		System.out.println("ID: "+product.getProductID()+"\nName: "+product.getName()+"\nCost: "+product.getCost()+"\nMarkup: "+product.getMarkup()+"\nSupplier: "+product.getSupplier());
		return product.getProductID();
	}
	//4
	public String viewProductList(){
		//Will list products from product list in driver class
		for(Product product: RetailSystem.getInstance().getProducts()){
			System.out.println("ID: "+product.getProductID()+"\nName: "+product.getName()+"\nCost: "+product.getCost()+"\nMarkup: "+product.getMarkup()+"\nSupplier: "+product.getSupplier());
			product.getProductID();
		}
		return "Function to view all products has run";

	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
