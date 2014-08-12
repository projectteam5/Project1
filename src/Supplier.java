
public class Supplier {
	private String supplierID;
	private String name;
	private String phoneNumber;

	public Supplier(String suppplierID,String name,String phoneNumber) {
		this.supplierID=suppplierID;
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	
	public void addSupplierToList(RetailSystem rs,Supplier supplier){
		rs.suppliers.add(supplier);
	}
	
	public void removeSupplierFromList(RetailSystem rs,Supplier supplier){
		rs.suppliers.remove(supplier);
	}
	
	public void viewSupplier(RetailSystem rs,Product product){	
		for(Product pro: rs.products){
			if(pro.getProductID()==product.getProductID()){
				System.out.println("Supplier of product: "+pro.getSupplier().getName());
			}
		}
	}
	
	public void viewSupplierList(RetailSystem rs){		
		for(Supplier sup:rs.suppliers){
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
