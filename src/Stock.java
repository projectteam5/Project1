import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Stock {
	private int units;
	private Product product;
	private boolean active;
	
	public Stock(int units, Product product) {
		this.units = units;
		this.product = product;
		this.active = true;
	}
	public Stock(int units, Product product,boolean active){
		this.units=units;
		this.product = product;
		this.active = active;
	}
	public void viewStockList(){
		for(Stock s: RetailSystem.getInstance().getStocks()){
		System.out.println("Product: "+s.getProduct().getName()+"\n Units: "+s.getUnits());
		}
	}
	public void addStockToList(Product product, int units){
		for(Stock s :RetailSystem.getInstance().getStocks()){
			if(s.getProduct().getProductID()==product.getProductID()){
				s.setUnits(s.getUnits()+units);
				JOptionPane.showMessageDialog(null, units+" units of "+product.getName()+" added to stock");
			}else{
				RetailSystem.getInstance().getStocks().add(new Stock(units,product));
				JOptionPane.showMessageDialog(null,"New product added to stock\nName: "+product.getName()+"\nUnits: "+units);
			}
		}
	}
	public void removeStockFromList(Product product, int units){
		boolean found = false;
		
		for(Stock s:RetailSystem.getInstance().getStocks()){
			if(s.getProduct().getProductID()==product.getProductID()){
				s.setUnits(s.getUnits()-units);
				JOptionPane.showMessageDialog(null, units+" units of "+product.getName()+" removed from stock");
				found = true;
				
			}
		}
		if(!found){
			JOptionPane.showMessageDialog(null,"No such product exists");
		}
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public static void autoGenterateOrderCheck(){
		ArrayList<Stock>stocks = RetailSystem.getInstance().getStocks();
		boolean found = false;
		for(Stock s: stocks)
		if(s.units<=5){
			for(Order o: RetailSystem.getInstance().getOrders()){
				if(o.getProduct().getProductID().equalsIgnoreCase(s.product.getProductID())){
					found = true;
				}
					
			}
			if(!found){
			//Order newOrder = new Order(this.product);
			
			//RetailSystem.getInstance().getOrders().add(newOrder);
			
			}
		}
		
	}
	public static void updateStock(Invoice invoice){
		ArrayList<Stock> stocks = RetailSystem.getInstance().getStocks();
		ArrayList<LineItem> items;
		items = invoice.getSale().getLineItems();
		
		for(LineItem item:items){
			for(Stock stock: stocks){
				
				if(item.getProduct().getProductID().equalsIgnoreCase(stock.getProduct().getProductID())){
					stock.setUnits(stock.getUnits()-item.getQuantity());
				}
				
			}
			
		}
		saveStock();
	}
	public static void saveStock(){
		try {
			FileWriter userFile;
			userFile = new FileWriter("stocks.txt");
			DataBase.writeStocks(RetailSystem.getInstance().getStocks(), userFile);
			userFile.close();// close the stock file
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
