
public class Supplier {
	private String supplierID;
	private String name;
	private String phoneNumber;

	public Supplier(String suppplierID,String name,String phoneNumber) {
		this.supplierID=suppplierID;
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	
	public void addSupplierToList(Supplier supplier){
		// Takes a supplier and adds to supplier list in driver class
		RetailSystem.getInstance().getSuppliers().add(supplier);
	}
	
	public void removeSupplierFromList(Supplier supplier){
		// Takes in a supplier and removes supplier from list in driver class
		RetailSystem.getInstance().getSuppliers().remove(supplier);
	}
	
	public void viewSupplier(Product product){
		// Takes a product and searches for the supplier name in the product list
		for(Product pro: RetailSystem.getInstance().getProducts()){
			if(pro.getProductID()==product.getProductID()){
				System.out.println("Supplier of product: "+pro.getSupplier().getName());
			}
		}
	}
	
	public void viewSupplierList(){	
		// Loops through the supplier list printing name of each supplier
		for(Supplier sup:RetailSystem.getInstance().getSuppliers()){
			System.out.println("Supplier name: "+sup.getName());
		}
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}	
}
