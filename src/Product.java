import javax.swing.JComboBox;

public class Product {
	private String productID;
	private String name;
	private double cost;
	private double markup;
	private Supplier supplier;
	private boolean active;

	public Product(String name, double cost, double markup, Supplier supplier) {
		this.productID = "Product"+(RetailSystem.getInstance().getProducts().size()+1);
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
	public static String addProductToList(Product product){
		//Will take in a product and add it to the products list in the driver class
		boolean duplicate = false;
		String string ="";
		for(Product p: RetailSystem.getInstance().getProducts()){
			if(p.getProductID() == product.getProductID()){
				duplicate = true;
				string =  "existing product";
			}	
			
			}if(!duplicate){
				RetailSystem.getInstance().getProducts().add(product);
				string =  product.getProductID();
			}
			return string;
	}
	//2
	public static boolean changeProductToInactive(Product product){
		//Will remove a product from the products list in the driver class
		product.setActive(false);
		return product.isActive();
	}
	//3
	public static String viewProduct(Product product){
		//Temp println in place of GUI
		String string = "";
		System.out.println("ID: "+product.getProductID()+"\nName: "+product.getName()+"\nCost: "+product.getCost()+"\nMarkup: "+product.getMarkup()+"\nSupplier: "+product.getSupplier());
		string = product.getProductID();
		return string;
	}
	//4
	public static String viewProductList(){
		//Will list products from product list in driver class
		String string = "";
		for(Product product: RetailSystem.getInstance().getProducts()){
			System.out.println("ID: "+product.getProductID()+"\nName: "+product.getName()+"\nCost: "+product.getCost()+"\nMarkup: "+product.getMarkup()+"\nSupplier: "+product.getSupplier());
			string = product.getProductID();
			return string;
		}
		return string;

	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	//used in DataBase
	public static Product findProductWithID(String id) {
		Product product = null;
		for (Product product_1 : RetailSystem.getInstance().getProducts()) {
			if (product_1.getProductID().equals(id)){
				product = product_1;
				break; // I need to exit the for
			}
		}
		return product;
	}
	
	//used in sale GUI
	public static Product findProductWithName(String name) {
		Product product = null;
		for (Product product_1 : RetailSystem.getInstance().getProducts()) {
			if (product_1.getName().equals(name)) {
				product = product_1;
				break; // I need to exit the for
			}
		}
		return product;
	}

	
	//used in sale gui
	public static void compileProductNames(JComboBox<String> productDropDown){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());
			}
		}
	}
	
}
