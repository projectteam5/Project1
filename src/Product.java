import java.util.ArrayList;

public class Product {
	private String productID;
	private String name;
	private double cost;
	private double markup;
	private Supplier supplier;

	public Product(String productID, String name, double cost, double markup, Supplier supplier) {
		this.productID = productID;
		this.name = name;
		this.cost = cost;
		this.markup = markup;
		this.supplier = supplier;
		
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
	
	public void addProductToList(Product product){
		//Will take in a product and add it to the products list in the driver class
		boolean duplicate = false;
		for(Product p: RetailSystem.getInstance().getProducts()){
			if(p.getProductID() == product.getProductID()){
				System.out.println("Product is already in product list");
				duplicate = true;
				break;
			}else{
				if(!duplicate){
					RetailSystem.getInstance().getProducts().add(product);
					break;
				}
			}
		}
	}
	
	public void removeProductFromList(Product product){
		//Will remove a product from the products list in the driver class
		RetailSystem.getInstance().getProducts().remove(product);
	}
		
	public void viewProduct(Product product){
		//Temp println in place of GUI
		System.out.println("ID: "+product.getProductID()+"\nName: "+product.getName()+"\nCost: "+product.getCost()+"\nMarkup: "+product.getMarkup()+"\nSupplier: "+product.getSupplier());
	}

	public void viewProductList(){
		//Will list products from product list in driver class
		for(Product product: RetailSystem.getInstance().getProducts()){
			System.out.println("ID: "+product.getProductID()+"\nName: "+product.getName()+"\nCost: "+product.getCost()+"\nMarkup: "+product.getMarkup()+"\nSupplier: "+product.getSupplier());
		}
	}
}
