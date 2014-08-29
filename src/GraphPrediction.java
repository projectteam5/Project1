import java.awt.Color;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class GraphPrediction extends JPanel {
	ArrayList<XYSeries> xySeries = new ArrayList<XYSeries>();
	Date date = new Date();
	ArrayList<Invoice> january = new ArrayList<Invoice>();
	ArrayList<Invoice> feb = new ArrayList<Invoice>();
	ArrayList<Invoice> mar = new ArrayList<Invoice>();
	ArrayList<Invoice> apr = new ArrayList<Invoice>();
	ArrayList<Invoice> may = new ArrayList<Invoice>();
	ArrayList<Invoice> jun = new ArrayList<Invoice>();
	ArrayList<Invoice> jul = new ArrayList<Invoice>();
	ArrayList<Invoice> aug = new ArrayList<Invoice>();
	ArrayList<Invoice> sep = new ArrayList<Invoice>();
	ArrayList<Invoice> oct = new ArrayList<Invoice>();
	ArrayList<Invoice> nov = new ArrayList<Invoice>();
	ArrayList<Invoice> dec = new ArrayList<Invoice>();
	
	double balanceJan;double balanceFeb;double balanceMar;double balanceApr;double balanceMay;double balanceJun;
	double balanceJul;double balanceAug;double balanceSep;double balanceOct;double balanceNov;double balanceDec;
	double totalSalesInYear=0;double average;double perCentIncrease;

	public GraphPrediction() {
    	  
    	  for(Invoice inv:RetailSystem.getInstance().getInvoices()){
    		  totalSalesInYear = totalSalesInYear+inv.getTotalInvoice();
    		  
    		  if(addDaysFromDateString(inv.getInvoiceDate())==1){
    			  january.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==2){
    			  feb.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==3){
    			  mar.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==4){
    			  apr.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==5){
    			  may.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==6){
    			  jun.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==7){
    			  jul.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==8){
    			  aug.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==9){
    			  sep.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==10){
    			  oct.add(inv);
    		  }
    		  else if(addDaysFromDateString(inv.getInvoiceDate())==11){
    			  nov.add(inv);
    		  }
    		  else
    			  dec.add(inv);
    	  }
    	  
    	  average = totalSalesInYear/12;
    	  perCentIncrease=(average/totalSalesInYear);
    	  System.out.println(perCentIncrease);
    	  
    	  for(Invoice inv:january){
    		  balanceJan = balanceJan+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:feb){
    		  balanceFeb = balanceFeb+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:mar){
    		  balanceMar = balanceMar+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:apr){
    		  balanceApr = balanceApr+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:may){
    		  balanceMay = balanceMay+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:jun){
    		  balanceJun = balanceJun+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:jul){
    		  balanceJul = balanceJul+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:aug){
    		  balanceAug = balanceAug+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:sep){
    		  balanceSep = balanceSep+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:oct){
    		  balanceOct = balanceOct+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:nov){
    		  balanceNov = balanceNov+inv.getTotalInvoice();
    	  }
    	  for(Invoice inv:dec){
    		  balanceDec = balanceDec+inv.getTotalInvoice();
    	  }
    	  
    	  
    	  
    	  

            
        XYSeries                series1   = new XYSeries("Sales per month");
                                series1.add(1, balanceJan);
                                series1.add(2, balanceFeb);
                                series1.add(3, balanceMar);
                                series1.add(4, balanceApr);
                                series1.add(5, balanceMay);
                                series1.add(6, balanceJun);
                                series1.add(7, balanceJul);
                                series1.add(8, balanceAug);
                                series1.add(9, balanceSep);
                                series1.add(10, balanceOct);
                                series1.add(11, balanceNov);
                                series1.add(12, balanceDec);
                                
       XYSeries					series2 = new XYSeries("Predictions for Year 2");
       							series2.add(1,balanceJan+(balanceJan*perCentIncrease));
       							series2.add(2,balanceFeb+(balanceFeb*perCentIncrease));
       							series2.add(3,balanceMar+(balanceMar*perCentIncrease));
       							series2.add(4,balanceApr+(balanceApr*perCentIncrease));
       							series2.add(5,balanceMay+(balanceMay*perCentIncrease));
       							series2.add(6,balanceJun+(balanceJun*perCentIncrease));
       							series2.add(7,balanceJul+(balanceJul*perCentIncrease));
       							series2.add(8,balanceAug+(balanceAug*perCentIncrease));
       							series2.add(9,balanceSep+(balanceSep*perCentIncrease));
       							series2.add(10,balanceOct+(balanceOct*perCentIncrease));
       							series2.add(11,balanceNov+(balanceNov*perCentIncrease));
       							series2.add(12,balanceDec+(balanceDec*perCentIncrease));
   
                        
        XYSeriesCollection      xyDataset = new XYSeriesCollection();
                                xyDataset.addSeries(series1);
                                xyDataset.addSeries(series2);
                                
   
        JFreeChart              chart     = ChartFactory.createXYLineChart("Sales V Time","Months","EURO",xyDataset,PlotOrientation.VERTICAL,true,false,false);
                                chart.setBackgroundPaint(Color.white); 
                        
        XYPlot                  plot      = (XYPlot) chart.getPlot();
                                plot.setBackgroundPaint       (Color.lightGray);
                                plot.setDomainGridlinePaint   (Color.GREEN);
                                plot.setRangeGridlinePaint    (Color.white);
                                plot.setAxisOffset            (new RectangleInsets(50, 0, 20, 5));
                                plot.setDomainCrosshairVisible(true);
                                plot.setRangeCrosshairVisible (true);
   
        XYLineAndShapeRenderer  renderer  = (XYLineAndShapeRenderer) plot.getRenderer();      
                                renderer.setBaseShapesVisible(true);
                                renderer.setBaseShapesFilled (true);
   
        ChartPanel              chartPanel     = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        this.setLayout(new GridLayout(0, 1));
        this.add(chartPanel);
                                              
      }	public static double addDaysFromDateString(Date invoiceDate) {
		double result;

		Calendar calendar = new GregorianCalendar();

		calendar.setTime(invoiceDate);

		result = calendar.get(Calendar.MONTH);

		return result;
	}

}