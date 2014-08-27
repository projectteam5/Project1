import java.util.ArrayList;

public class Accounting {
	
	private ArrayList<Invoice> invoices;
	
	private ArrayList<Order> orders;
	
	private ArrayList<Stock> stocks;

	public Accounting() {
		
		invoices = RetailSystem.getInstance().getInvoices();
		
		orders = RetailSystem.getInstance().getOrders();
		
		stocks = RetailSystem.getInstance().getStocks();
		
	}
	
	public double showProfits() {
		
		double totalProfit = 0.0;
		double profit = 0.0;
		double loss = 0.0;
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			loss += order.getProduct().getCost() * order.getQuantity();
			
		}
		
		totalProfit = profit - loss;
		
		return totalProfit;
		
	}
	
	public double showLosses() {
		
		return 0;
		
	}
	
	public double showProfitsAndLosses() {
		
		return 0;
		
	}
	
	public double showExpectedSales() {
		
		return 0;
		
	}

}
