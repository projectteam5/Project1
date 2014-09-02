import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

@SuppressWarnings("serial")
public class GraphPrediction extends JPanel {
	
	private ArrayList<Double> grossProfit;
	
	// sales data - Invoices
	private double janSales; private double febSales; private double marSales; private double aprSales;
	private double maySales; private double junSales; private double julSales; private double augSales;
	private double sepSales; private double octSales; private double novSales; private double decSales;
	
	// purchases data - Orders
	private double janPurchases; private double febPurchases; private double marPurchases; private double aprPurchases;
	private double mayPurchases; private double junPurchases; private double julPurchases; private double augPurchases;
	private double sepPurchases; private double octPurchases; private double novPurchases; private double decPurchases;
	
	// profit
	private double janProfit; private double febProfit; private double marProfit; private double aprProfit; private double mayProfit;
	private double junProfit; private double julProfit; private double augProfit; private double sepProfit; private double octProfit;
	private double novProfit; private double decProfit;
	
	public GraphPrediction() {
		
		grossProfit = new ArrayList<Double>();
		
		Calendar calendar1 = new GregorianCalendar();
		
		calendar1.setTime(new Date());
		
		// get the total sales for each month
		
		for(Invoice invoice : RetailSystem.getInstance().getInvoices()) {
			
			if(extractMonthFromDate(invoice.getInvoiceDate())==0) {
				
				janSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==1) {
				
				febSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==2) {
				
				marSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==3) {
				
				aprSales += invoice.getTotalInvoice();
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==4) {
				
				maySales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==5) {
							
				junSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==6) {
				
				julSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==7) {
				
				augSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==8) {
				
				sepSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==9) {
				
				octSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==10) {
				
				novSales += invoice.getTotalInvoice();
				
			}
			
			else if(extractMonthFromDate(invoice.getInvoiceDate())==11) {
				
				decSales += invoice.getTotalInvoice();
				
			}
			
			else {
				
				;
				
			}
			
		}
		
		// get the total purchases for each month
		
		for(Order order : RetailSystem.getInstance().getOrders()) {
			
			if(extractMonthFromDate(order.getDateReceived())==0 && order.isReceived()) {
				
				janPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==1 && order.isReceived()) {
				
				febPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==2 && order.isReceived()) {
				
				marPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==3 && order.isReceived()) {
				
				aprPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==4 && order.isReceived()) {
				
				mayPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==5 && order.isReceived()) {
							
				junPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==6 && order.isReceived()) {
				
				julPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==7 && order.isReceived()) {
				
				augPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==8 && order.isReceived()) {
				
				sepPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==9 && order.isReceived()) {
				
				octPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==10 && order.isReceived()) {
				
				novPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else if(extractMonthFromDate(order.getDateReceived())==11 && order.isReceived()) {
				
				decPurchases += order.getProduct().getCost() * order.getQuantity();
				
			}
			
			else {
				
				;
				
			}
			
		}
		
		// get the profit for each month - add to array
		
		janProfit = janSales - janPurchases;
		grossProfit.add(janProfit);
		
		febProfit = febSales - febPurchases;
		grossProfit.add(febProfit);
		
		marProfit = marSales - marPurchases;
		grossProfit.add(marProfit);
		
		aprProfit = aprSales - aprPurchases;
		grossProfit.add(aprProfit);
		
		mayProfit = maySales - mayPurchases;
		grossProfit.add(mayProfit);
		
		junProfit = junSales - junPurchases;
		grossProfit.add(junProfit);
		
		julProfit = julSales - julPurchases;
		grossProfit.add(julProfit);
		
		augProfit = augSales - augPurchases;
		grossProfit.add(augProfit);
		
		sepProfit = sepSales - sepPurchases;
		grossProfit.add(sepProfit);
		
		octProfit = octSales - octPurchases;
		grossProfit.add(octProfit);
		
		novProfit = novSales - novPurchases;
		grossProfit.add(novProfit);
		
		decProfit = decSales - decPurchases;
		grossProfit.add(decProfit);
        
        JFreeChart chart = ChartFactory.createXYLineChart("Sales, Purchases, Profit Graph", "Month", "(€) Amount", 
        		createXYSeriesDataset(), PlotOrientation.VERTICAL,true,true,false);
        
        chart.setBackgroundPaint(Color.white);
   
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        this.setLayout(new GridLayout(0, 1));
        this.add(chartPanel);
                                              
      }
	
	// collection - holding series
	
	private XYSeriesCollection createXYSeriesDataset() {
		
		final XYSeriesCollection data = new XYSeriesCollection();
		
		data.addSeries(xySeriesData1());
		
		data.addSeries(xySeriesData2());
		
		data.addSeries(xySeriesData3());
		
		return data;
		
	}
	
	// series1 - Purchases data
	
	private XYSeries xySeriesData1() {
		
		final XYSeries series = new XYSeries("Purchases");
		
		series.add(Calendar.JANUARY+1, janPurchases);
		series.add(Calendar.FEBRUARY+1, febPurchases);
		series.add(Calendar.MARCH+1, marPurchases);
		series.add(Calendar.APRIL+1, aprPurchases);
		series.add(Calendar.MAY+1, mayPurchases);
		series.add(Calendar.JUNE+1, junPurchases);
		series.add(Calendar.JULY+1, julPurchases);
		series.add(Calendar.AUGUST+1, augPurchases);
		series.add(Calendar.SEPTEMBER+1, sepPurchases);
		series.add(Calendar.OCTOBER+1, octPurchases);
		series.add(Calendar.NOVEMBER+1, novPurchases);
		series.add(Calendar.DECEMBER+1, decPurchases);
		
		return series;
		
	}
	
	// series2 - Sales data
	
	private XYSeries xySeriesData2() {
		
		final XYSeries series = new XYSeries("Sales");
		
		series.add(Calendar.JANUARY+1, janSales);
		series.add(Calendar.FEBRUARY+1, febSales);
		series.add(Calendar.MARCH+1, marSales);
		series.add(Calendar.APRIL+1, aprSales);
		series.add(Calendar.MAY+1, maySales);
		series.add(Calendar.JUNE+1, junSales);
		series.add(Calendar.JULY+1, julSales);
		series.add(Calendar.AUGUST+1, augSales);
		series.add(Calendar.SEPTEMBER+1, sepSales);
		series.add(Calendar.OCTOBER+1, octSales);
		series.add(Calendar.NOVEMBER+1, novSales);
		series.add(Calendar.DECEMBER+1, decSales);
		
		return series;
		
	}
	
	// series3 - Profit data
	
	private XYSeries xySeriesData3() {
		
		final XYSeries series = new XYSeries("Profit");
		
		series.add(Calendar.JANUARY+1, janProfit);
		series.add(Calendar.FEBRUARY+1, febProfit);
		series.add(Calendar.MARCH+1, marProfit);
		series.add(Calendar.APRIL+1, aprProfit);
		series.add(Calendar.MAY+1, mayProfit);
		series.add(Calendar.JUNE+1, junProfit);
		series.add(Calendar.JULY+1, julProfit);
		series.add(Calendar.AUGUST+1, augProfit);
		series.add(Calendar.SEPTEMBER+1, sepProfit);
		series.add(Calendar.OCTOBER+1, octProfit);
		series.add(Calendar.NOVEMBER+1, novProfit);
		series.add(Calendar.DECEMBER+1, decProfit);
		
		return series;
		
	}
	
	public static int extractMonthFromDate(Date date) {
		
		int month = 0;
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(date);
		
		month = calendar.get(Calendar.MONTH);
		
		return month;
		
	}
	

}