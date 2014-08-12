import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Stock {
	private int units;
	private Product product;
	
	public Stock(int units, Product product) {
		this.units = units;
		this.product = product;
	}
	public void viewStockList(ArrayList<Stock>stocks){
		for(Stock s: stocks){
		System.out.println("Product: "+s.getProduct().getName()+"\n Units: "+s.getUnits());
		}
	}
	public void addStockToList(ArrayList<Stock> stocks,Product product, int units){
		for(Stock s : stocks){
			if(s.getProduct()==this.product){
				s.setUnits(s.getUnits()+units);
			}else{
				stocks.add(new Stock(units,product));
			}
		}
	}
	public void removeStockFromList(ArrayList<Stock> stocks, Product product, int units){
		boolean found = false;
		
		for(Stock s: stocks){
			if(s.getProduct()==product){
				s.setUnits(s.getUnits()-units);
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
