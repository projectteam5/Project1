import java.io.FileWriter;
import java.util.ArrayList;


public class Supplier {
	private String supplierID;
	private String name;
	private String phoneNumber;
	private boolean active;

	public Supplier(String name,String phoneNumber) {
		this.supplierID="Supplier "+(Supplier.getSupplierList().size()+1);
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.active=true;
	}
	
	public Supplier(String supplierID,String name,String phoneNumber,boolean active) {
		this.supplierID=supplierID;
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.active=active;
	}
	
	public void addSupplierToList(Supplier supplier){
		// Takes a supplier and adds to supplier list in driver class
		RetailSystem.getInstance().getSuppliers().add(supplier);
	}
	
	public void removeSupplierFromList(Supplier supplier){
		// Takes in a supplier and removes supplier from list in driver class
		RetailSystem.getInstance().getSuppliers().remove(supplier);
	}
	
	
	public static ArrayList<Supplier> getSupplierList(){
		// Returns the active list of suppliers
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
	
	public static void saveSupplier(){
	  	 try {
	  		 FileWriter userFile;
	  		 userFile = new FileWriter("suppliers.txt");
	  		 DataBase.writeSuppliers(RetailSystem.getInstance().getSuppliers(),userFile);
	  		 userFile.close();// close the user file
	  	 } catch (Exception exception) {
	  		 exception.printStackTrace();
	  	 }
	}
	
	//used in DataBase
	public static Supplier findSupplierWithID(String id) {
		Supplier supplier = null;
		// before to construct the object order it is necessary retrieve
		// the object product
		for (Supplier supplier_1 : RetailSystem.getInstance().getSuppliers()) {
			if (supplier_1.getSupplierID().equals(id)) {
				supplier = supplier_1;
				break; // I need to exit the for
			}

		}
		return supplier;
	}
}
