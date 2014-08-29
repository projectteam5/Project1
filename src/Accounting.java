import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Accounting {
	
	public Accounting() {
		
		RetailSystem.getInstance().getInvoices();
		
		RetailSystem.getInstance().getOrders();
		
		RetailSystem.getInstance().getStocks();
		
	}
	
	public static String subtractMonthsFromDateString() {
		
		String newDate = "";
		
		Calendar calendar = new GregorianCalendar();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		calendar.setTime(new Date());
		
		calendar.add(Calendar.MONTH, -1);
		
		newDate = dateFormat.format(calendar.getTime());
		
		return newDate;
	}
	
	public static Date subtractMonthsFromDate() {
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(new Date());
		
		calendar.add(Calendar.MONTH, -1);
		
		Date newDate = calendar.getTime();
		
		return newDate;
	}
	
	public static double salesIncome() {
		
		double totalProfit = 0.0;
		
		double profit = 0.0;
		
		for(Invoice invoice : RetailSystem.getInstance().getInvoices()) {
			
			if( (invoice.getInvoiceDate().after(Accounting.subtractMonthsFromDate())&&invoice.getInvoiceDate().before(new Date())) &&invoice.isPaid() ) {
				
				profit += invoice.getTotalInvoice();
				
			}
			
		}
		
		totalProfit = profit;
		
		return totalProfit;
		
	}
	
	public static double showLosses() {
		
		double totalLosses = 0.0;
		
		double loss = 0.0;
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if( (order.getOrderDate().after(Accounting.subtractMonthsFromDate())&&order.getOrderDate().before(new Date())) &&order.isReceived() ) {
				
				loss += order.getProduct().getCost() * order.getQuantity();
				
			}
			
		}
		
		totalLosses = loss;
		
		return totalLosses;
		
	}
	
	public static double showProfitsAndLosses() {
		
		double pl = 0.0;
		
		double profit = Accounting.salesIncome();
		
		double loss = Accounting.showLosses();
		
		pl = profit - loss;
		
		return pl;
		
	}
	
	public static double openingStock() {
		
		double s = 0.0;
		
		double openingStock = 0.0;
		
		for(Stock stock : RetailSystem.getInstance().getStocks()) {
			
			s += stock.getProduct().getCost() * stock.getUnits();
			
		}
		
		openingStock = s;
		
		return openingStock;
		
	}
	
	public static double closingStock() {
		
		double s = 0.0;
		
		double o = 0.0;
		
		double closingStock = 0.0;
		
		for(Stock stock : RetailSystem.getInstance().getStocks()) {
			
			s += stock.getProduct().getCost() * stock.getUnits();
			
		}
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if( (order.getOrderDate().after(Accounting.subtractMonthsFromDate())&&order.getOrderDate().before(new Date())) &&order.isReceived() ) {
				
				o += order.getProduct().getCost();
				
			}
			
		}
		
		closingStock = s + o;
		
		return closingStock;
		
	}
	
	public static double goodsPurchase() {
		
		double p = 0.0;
		
		double purchaces = 0.0;
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if( (order.getOrderDate().after(Accounting.subtractMonthsFromDate())&&order.getOrderDate().before(new Date())) &&order.isReceived() ) {
				
				p += order.getProduct().getCost() * order.getQuantity();
				
			}
			
		}
		
		purchaces = p;
		
		return purchaces;
		
	}
	
	public static double costOfSales() {
		
		// Opening Stock = stock - orders
		
		// Closing Stock = stock + orders
		
		// Purchases = orders
		
		// Cost of Sales = Opening stock + Purchases - Closing Stock 
		
		double costOfSales = 0.0;
		
		double oS = Accounting.openingStock();
		
		double p = Accounting.goodsPurchase();
		
		double cS = Accounting.closingStock();
		
		costOfSales = (oS + p) - cS;
		
		return costOfSales;
		
	}
	
	public static double grossProfit() {
		
		double grossProfit = 0.0;
		
		double sI = Accounting.salesIncome();
		
		double cS = Accounting.costOfSales();
		
		grossProfit = sI - cS;
		
		return grossProfit;
		
	}
	
	public static double showExpectedProfit() {
		
		double expectedSales = 0.0;
		
		double prediction = 0.0;
		
		for(Stock stock : RetailSystem.getInstance().getStocks()) {
			
			prediction += ( stock.getProduct().getCost() + stock.getProduct().getMarkup() ) * stock.getUnits();
			
		}
		
		expectedSales = prediction;
		
		return expectedSales;
		
	}

}
