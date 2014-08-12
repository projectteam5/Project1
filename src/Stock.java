import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Stock {
	private int units;
	private Product product;
	
	public Stock(int units, Product product) {
		this.units = units;
		this.product = product;
	}
	public void viewStockList(){
		for(Stock s: RetailSystem.getStocks()){
		System.out.println("Product: "+s.getProduct().getName()+"\n Units: "+s.getUnits());
		}
	}
	public void addStockToList(Product product, int units){
		for(Stock s :RetailSystem.getStocks()){
			if(s.getProduct().getProductID()==product.getProductID()){
				s.setUnits(s.getUnits()+units);
				JOptionPane.showMessageDialog(null, units+" units of "+product.getName()+" added to stock");
			}else{
				stocks.add(new Stock(units,product));
				JOptionPane.showMessageDialog(null,"New product added to stock\nName: "+product.getName()+"\nUnits: "+units);
			}
		}
	}
	public void removeStockFromList(Product product, int units){
		boolean found = false;
		
		for(Stock s:RetailSystem.getStocks()){
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

}
