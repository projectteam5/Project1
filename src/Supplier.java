import java.io.FileWriter;
import java.util.ArrayList;


public class Supplier {
	private String supplierID;
	private String name;
	private String phoneNumber;
	private boolean active;
	private int i;

	public Supplier(String suppplierID,String name,String phoneNumber) {
		this.supplierID=suppplierID;
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.active=true;
	}
	
	public Supplier(String suppplierID,String name,String phoneNumber,boolean active) {
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
	
	public static ArrayList<Supplier> getSupplierList(){
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		for(Supplier sup:RetailSystem.getInstance().getSuppliers()){
			if(sup.isActive()){
			suppliers.add(sup);
			}
		}
		return suppliers;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
	
	public static void saveUser(){
	  	 try {
	  		 FileWriter userFile;
	  		 userFile = new FileWriter("suppliers.txt");
	  		 DataBase.writeSuppliers(RetailSystem.getInstance().getSuppliers(),userFile);
	  		 userFile.close();// close the user file
	  	 } catch (Exception exception) {
	  		 exception.printStackTrace();
	  	 }
	}
}
