import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Accounting {
	
	private ArrayList<Invoice> invoices;
	
	private ArrayList<Order> orders;
	
	private ArrayList<Stock> stocks;
	
	private Date accountingDate;
	
	public Accounting() {
		
		invoices = new ArrayList<Invoice>();
		
		orders = new ArrayList<Order>();
		
		stocks = new ArrayList<Stock>();
		
		accountingDate = new Date();
		
	}
	
	public ArrayList<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(ArrayList<Invoice> invoices) {
		this.invoices = invoices;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}

	public Date getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(Date accountingDate) {
		this.accountingDate = accountingDate;
	}
	
	public static String dateOpsMonthsFromDateString(int period) {
		
		String newDate = "";
		
		Calendar calendar = new GregorianCalendar();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		calendar.setTime(new Date());
		
		calendar.add(Calendar.MONTH, period);
		
		newDate = dateFormat.format(calendar.getTime());
		
		return newDate;
	}
	
	public static Date subtractMonthsFromDate(int period) {
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(new Date());
		
		calendar.add(Calendar.MONTH, - period);
		
		Date newDate = calendar.getTime();
		
		return newDate;
	}
	
	public static Date addMonthsToDate(int period) {
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(new Date());
		
		calendar.add(Calendar.MONTH, period);
		
		Date newDate = calendar.getTime();
		
		return newDate;
	}
	
	public static double salesIncome(String period1, String period2) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		Calendar calendar1 = new GregorianCalendar();
		
		Calendar calendar2 = new GregorianCalendar();
		
		try {
			calendar1.setTime(dateFormat.parse(period1));
			
			calendar2.setTime(dateFormat.parse(period2));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date date1 = calendar1.getTime();
		
		Date date2 = calendar2.getTime();
		
		double totalProfit = 0.0;
		
		double profit = 0.0;
		
		for(Invoice invoice : RetailSystem.getInstance().getInvoices()) {
			
			if( (invoice.getInvoiceDate().after(date1)
					&&invoice.getInvoiceDate().before(date2)) 
					&&invoice.isPaid() ) {
				
				profit += invoice.getTotalInvoice();
				
			}
			
		}
		
		totalProfit = profit;
		
		return totalProfit;
		
	}
	
	public static double openingStock(String period1, String period2) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		Calendar calendar1 = new GregorianCalendar();
		
		Calendar calendar2 = new GregorianCalendar();
		
		try {
			calendar1.setTime(dateFormat.parse(period1));
			
			calendar2.setTime(dateFormat.parse(period2));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date date1 = calendar1.getTime();
		
		Date date2 = calendar2.getTime();
		
		double s = 0.0;
		
		double openingStock = 0.0;
		
		for(Stock stock : RetailSystem.getInstance().getStocks()) {
			
			for(Order order : RetailSystem.getInstance().getOrders()) {
				
				if( stock.getProduct().getProductID().equals(order.getProduct().getProductID()) 
						&& (order.getOrderDate().after(date1)
						&& order.getOrderDate().before(date2)) 
						&& order.isReceived() ) {
					
					s += stock.getProduct().getCost() * stock.getUnits();
					
				}
				
			}
			
		}
		
		openingStock = s;
		
		return openingStock;
		
	}
	
	public static double closingStock(String period1, String period2) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		Calendar calendar1 = new GregorianCalendar();
		
		Calendar calendar2 = new GregorianCalendar();
		
		try {
			calendar1.setTime(dateFormat.parse(period1));
			
			calendar2.setTime(dateFormat.parse(period2));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date date1 = calendar1.getTime();
		
		Date date2 = calendar2.getTime();
		
		double s = 0.0;
		
		double o = 0.0;
		
		double closingStock = 0.0;
		
		for(Stock stock : RetailSystem.getInstance().getStocks()) {
			
			for(Order order : RetailSystem.getInstance().getOrders()) {
				
				if( stock.getProduct().getProductID().equals(order.getProduct().getProductID()) 
						&& (order.getOrderDate().after(date1)
						&& order.getOrderDate().before(date2)) 
						&& order.isReceived() ) {
					
					s += stock.getProduct().getCost() * stock.getUnits();
					
				}
				
			}
			
		}
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if( (order.getOrderDate().after(date1))
					&& order.getOrderDate().before(date2) 
					&& order.isReceived() ) {
				
				o += order.getProduct().getCost();
				
			}
			
		}
		
		closingStock = s + o;
		
		return closingStock;
		
	}
	
	public static double goodsPurchase(String period1, String period2) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		Calendar calendar1 = new GregorianCalendar();
		
		Calendar calendar2 = new GregorianCalendar();
		
		try {
			calendar1.setTime(dateFormat.parse(period1));
			
			calendar2.setTime(dateFormat.parse(period2));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date date1 = calendar1.getTime();
		
		Date date2 = calendar2.getTime();
		
		double p = 0.0;
		
		double purchaces = 0.0;
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if( (order.getOrderDate().after(date1)
					&& order.getOrderDate().before(date2))
					&& order.isReceived() ) {
				
				p += order.getProduct().getCost() * order.getQuantity();
				
			}
			
		}
		
		purchaces = p;
		
		return purchaces;
		
	}
	
	public static double costOfSales(String period1, String period2) {
		
		// Opening Stock = stock - orders
		
		// Closing Stock = stock + orders
		
		// Purchases = orders
		
		// Cost of Sales = Opening stock + Purchases - Closing Stock 
		
		double costOfSales = 0.0;
		
		double oS = Accounting.openingStock(period1, period2);
		
		double p = Accounting.goodsPurchase(period1, period2);
		
		double cS = Accounting.closingStock(period1, period2);
		
		costOfSales = (oS + p) - cS;
		
		return costOfSales;
		
	}
	
	public static double grossProfit(String period1, String period2) {
		
		double grossProfit = 0.0;
		
		double sI = Accounting.salesIncome(period1, period2);
		
		double cS = Accounting.costOfSales(period1, period2);
		
		grossProfit = sI - cS;
		
		return grossProfit;
		
	}
	
}
