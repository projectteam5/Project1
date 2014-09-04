import java.text.DateFormat;
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
	
	private ArrayList<Accounting>accounts;
	
	private int year;

	private int month;

	private double sales;

	private double purchases;

	private double profit;
	
	public Accounting() {
		
		invoices = new ArrayList<Invoice>();
		
		orders = new ArrayList<Order>();
		
		stocks = new ArrayList<Stock>();
		
		accountingDate = new Date();
		
		accounts = new ArrayList<Accounting>();
		
		
	}
	
	public Accounting(Date month) {
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(month);
		
		this.month = calendar.get(Calendar.MONTH);
		
		this.sales = getIncomeForMonth(getMonth());
		
		this.purchases = getPurchasesForMonth(getMonth());
		
	}
	
	public Accounting(int year, int month, double sales, double purchases) {
		
		this.year = year;
		
		this.month = month;
		
		this.sales = sales;
		
		this.purchases = purchases;
		
	}
	
	public Accounting(int year, int month, double sales, double purchases, double profit) {
		
		this.year = year;
		
		this.month = month;
		
		this.sales = sales;
		
		this.purchases = purchases;
		
		this.profit = profit;
		
	}
	
	public Accounting(Date year, Date month) {
		
		Calendar calendar = new GregorianCalendar();
		
		Calendar calendar2 = new GregorianCalendar();
		
		calendar.setTime(year);
		
		calendar2.setTime(month);
		
		this.year = calendar.get(Calendar.YEAR);
		
		this.month = calendar2.get(Calendar.MONTH);
		
	}
	
	public Accounting(double sales, double purchases) {
		
		this.sales = sales;
		
		this.purchases = purchases;
		
	}
	
	public Accounting(double sales, double purchases, double profit) {
		
		this.sales = sales;
		
		this.purchases = purchases;
		
		this.profit = profit;
		
	}
	
	public ArrayList<Accounting> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Accounting> accounts) {
		this.accounts = accounts;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public double getPurchases() {
		return purchases;
	}

	public void setPurchases(double purchases) {
		this.purchases = purchases;
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
	
	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
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
	
	public static int extractMonthFromDate(Date date) {
		
		int month = 0;
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(date);
		
		int a = calendar.get(Calendar.MONTH);
		
		month = a;
		
		return month;
		
	}
	
	public static double getIncomeForMonth(int month) {
		
		double totalProfit = 0.0;
		
		double profit = 0.0;
		
		for(Invoice invoice : RetailSystem.getInstance().getInvoices()) {
			
			if( Accounting.extractMonthFromDate(invoice.getInvoiceDate())==month && invoice.isPaid() ) {
					
					profit += invoice.getTotalInvoice();
					
			}
			
		}
		
		totalProfit = profit;
		
		return totalProfit;
		
	}
	
	public static double getOpeningStockForMonth(int month) {
		
		double s = 0.0;
		
		double openingStock = 0.0;
		
		for(Stock stock : RetailSystem.getInstance().getStocks()) {
			
			for(Order order : RetailSystem.getInstance().getOrders()) {
				
				if( stock.getProduct().getProductID().equals(order.getProduct().getProductID()) 
						&& (Accounting.extractMonthFromDate(order.getOrderDate())==month) 
						&& order.isReceived() ) {
					
					s += stock.getProduct().getCost() * stock.getUnits();
					
				}
				
			}
			
		}
		
		openingStock = s;
		
		return openingStock;
		
	}
	
public static double getClosingStockForMonth(int month) {
		
	double s = 0.0;
	
	double o = 0.0;
	
	double closingStock = 0.0;
	
	for(Stock stock : RetailSystem.getInstance().getStocks()) {
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if( stock.getProduct().getProductID().equals(order.getProduct().getProductID()) 
					&& (Accounting.extractMonthFromDate(order.getOrderDate())==month) && order.isReceived() ) {
				
				s += stock.getProduct().getCost() * stock.getUnits();
				
			}
			
		}
		
	}
	
	for(Order order : RetailSystem.getInstance().getOrders()) {
		
		if( (Accounting.extractMonthFromDate(order.getOrderDate())==month) 
				&& order.isReceived() ) {
			
			o += order.getProduct().getCost();
			
		}
		
	}
	
	closingStock = s + o;
	
	return closingStock;
		
	}
	
	public static double getPurchasesForMonth(int month) {
		
		double p = 0.0;
		
		double purchaces = 0.0;
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if( Accounting.extractMonthFromDate(order.getOrderDate()) == month && order.isReceived() ) {
				
				p += order.getProduct().getCost() * order.getQuantity();
				
			}
			
		}
		
		purchaces = p;
		
		return purchaces;
		
	}
	
	public static double costOfSales(int month) {
		
		double costOfSales = 0.0;
		
		double oS = Accounting.getOpeningStockForMonth(month);
		
		double p = Accounting.getPurchasesForMonth(month);
		
		double cS = Accounting.getClosingStockForMonth(month);
		
		costOfSales = (oS + p) - cS;
		
		return costOfSales;
		
	}
	
	public static double grossProfit(int month) {
		
		double grossProfit = 0.0;
		
		double sI = Accounting.getIncomeForMonth(month);
		
		double cS = Accounting.costOfSales(month);
		
		grossProfit = sI - cS;
		
		return grossProfit;
		
	}
	
	public static int getNumberOfPurchasesForMonth(int month) {
		
		int p = 0;
		
		int purchaces = 0;
		
		ArrayList<Object> obj = new ArrayList<Object>();
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if( Accounting.extractMonthFromDate(order.getOrderDate()) == month && order.isReceived() ) {
				
				obj.add(order);
				
				p= obj.size();
				
			}
			
		}
		
		purchaces = p;
		
		return purchaces;
		
	}
	
	public static int getNumberOfSalesForMonth(int month) {
		
		int s = 0;
		
		int sales = 0;
		
		ArrayList<Object> obj = new ArrayList<Object>();
		
		for(Invoice invoice : RetailSystem.getInstance().getInvoices()) {
			
			if( Accounting.extractMonthFromDate(invoice.getInvoiceDate()) == month && invoice.isPaid() ) {
				
				obj.add(invoice);
				
				s = obj.size();
				
			}
			
		}
		
		sales = s;
		
		return sales;
		
	}
	
	
}
