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
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.MatrixSeries;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class GraphOfTopSales extends JPanel {

   private static final long serialVersionUID = 1L;  
   private String[][] matrix;
   
   ArrayList<ToHoldSoldProductsAndQuantity> janList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> febList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> marList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> aprList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> mayList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> junList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> julList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> augList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> sepList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> octList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> novList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> decList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   
   ArrayList<ToHoldSoldProductsAndQuantity> dupCheckList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   ArrayList<ToHoldSoldProductsAndQuantity> completeList= new ArrayList<ToHoldSoldProductsAndQuantity>();
   
   ArrayList<XYSeries> listOfXYSeries = new ArrayList<XYSeries>();


   private ToHoldSoldProductsAndQuantity convertedMatrixObject;
   private ToHoldSoldProductsAndQuantity listObject;

   XYSeries series;
    
   XYSeries seriesJan;XYSeries seriesFeb;XYSeries seriesMar;XYSeries seriesApr;XYSeries seriesMay;XYSeries seriesJun;
   XYSeries seriesJul;XYSeries seriesAug;XYSeries seriesSep;XYSeries seriesOct;XYSeries seriesNov;XYSeries seriesDec;
   
   public GraphOfTopSales(String applicationTitle, String chartTitle) {
	   	//	Getting the matrix that contains the date,products and quantities from all sales
	   	matrix = Invoice.productsSold();
	   	listObject= new ToHoldSoldProductsAndQuantity("", "", 0);
	   	dupCheckList.add(listObject);
	   	
	   	//	Finding out how many Individual products with quantities are in Matrix
	   	int count = 0;
	   	
		for(Invoice invoice : RetailSystem.getInstance().getInvoices()){
			count = count + invoice.getSale().getLineItems().size();
		}
		// Converting the matrix to a list of objects
		for(int i=0; i<count; i++){
				convertedMatrixObject= new ToHoldSoldProductsAndQuantity(matrix[i][0], matrix[i][1], 
							Double.parseDouble(matrix[i][2]));
				completeList.add(convertedMatrixObject);
		}
		
		// Separating the complete list of itemized sales into lists by months
		for(ToHoldSoldProductsAndQuantity g: completeList){
			if(g.getDate().contains("Jan")){janList.add(g);}
			else if(g.getDate().contains("Feb")){febList.add(g);}
			else if(g.getDate().contains("Mar")){marList.add(g);}
			else if(g.getDate().contains("Apr")){aprList.add(g);}
			else if(g.getDate().contains("May")){mayList.add(g);}
			else if(g.getDate().contains("Jun")){junList.add(g);}
			else if(g.getDate().contains("Jul")){julList.add(g);}
			else if(g.getDate().contains("Aug")){augList.add(g);}
			else if(g.getDate().contains("Sep")){sepList.add(g);}
			else if(g.getDate().contains("Oct")){octList.add(g);}
			else if(g.getDate().contains("Nov")){novList.add(g);}
			else decList.add(g);
		}	
		
		// Checking each list for multiple product and quantities and resorting
		// such that each month list contains unique products with calculated
		// quantities if the product appeared more than once on the list
		resortMonthlyListToCalculateMulipleSalesOfProducts();
				
		// Creating the data set according to month.
		// Will be 12 standard months on X-Axis with quantities on
		// the Y-Axis with numerous colored lines depicting products
		for(ToHoldSoldProductsAndQuantity list:janList){
			series = new XYSeries(list.getName());
			listOfXYSeries.add(series);
		}
		
		
		
	   	// Adding the data set to the collection for viewing

		XYSeriesCollection  xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(seriesJan);
        xyDataset.addSeries(seriesFeb);
        xyDataset.addSeries(seriesMar);
        xyDataset.addSeries(seriesApr);
        xyDataset.addSeries(seriesMay);
        xyDataset.addSeries(seriesJun);
        xyDataset.addSeries(seriesJul);
        xyDataset.addSeries(seriesAug);
        xyDataset.addSeries(seriesSep);
        xyDataset.addSeries(seriesOct);
        xyDataset.addSeries(seriesNov);
        xyDataset.addSeries(seriesDec);
       
        
        // Declaring and initializing and adding the chart to the panel 
        
        JFreeChart	chart = ChartFactory.createXYLineChart("Products & Quantities over Time","Months","Quantity",xyDataset,PlotOrientation.VERTICAL,true,false,false);
        chart.setBackgroundPaint(Color.white); 

        XYPlot	plot	= (XYPlot) chart.getPlot();
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
        
    }
   
   // Function to check for two or more products sold in the same month that
   // is used in conjunction with the resortMonthlyLists function
   
   public void storeAndCheckForMoreSalesOfProduct(ToHoldSoldProductsAndQuantity sentListItem){
	   int i=0;
	   for(ToHoldSoldProductsAndQuantity thisList: dupCheckList){
		   if(sentListItem.getName()==thisList.getName()){
			   thisList.setQuantity(thisList.getQuantity()+sentListItem.getQuantity());
			   i=0;
		   }else
			   i=1;
	   }
	   
	   if(i==1){
		   dupCheckList.add(sentListItem);
		   i=0;
	   }
   }
   
   // Checking each list for multiple product and quantities and resorting
   // such that each month list contains unique products with calculated
   // quantities if the product appeared more than once on the list
   // The monthly list is sent to a method to check for multiple sales
   // of the same product within the month and then added to collectively
   // to a new list such that each list contains unique products. This original 
   // monthly list is then deleted with the contents of the new list passed back to 
   // the original list.
   
   public void resortMonthlyListToCalculateMulipleSalesOfProducts(){	
		// January
		for(ToHoldSoldProductsAndQuantity j: janList){storeAndCheckForMoreSalesOfProduct(j);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){janList.add(thisItem);}
		janList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){janList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
		
		// February
		for(ToHoldSoldProductsAndQuantity f: febList){storeAndCheckForMoreSalesOfProduct(f);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){febList.add(thisItem);}
		febList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){febList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// March
		for(ToHoldSoldProductsAndQuantity mar: marList){storeAndCheckForMoreSalesOfProduct(mar);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){marList.add(thisItem);}
		marList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){marList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// April
		for(ToHoldSoldProductsAndQuantity apr: aprList){storeAndCheckForMoreSalesOfProduct(apr);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){aprList.add(thisItem);}
		aprList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){aprList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// May
		for(ToHoldSoldProductsAndQuantity may: mayList){storeAndCheckForMoreSalesOfProduct(may);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){mayList.add(thisItem);}
		mayList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){mayList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// June
		for(ToHoldSoldProductsAndQuantity jun: junList){storeAndCheckForMoreSalesOfProduct(jun);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){junList.add(thisItem);}
		junList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){junList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// July
		for(ToHoldSoldProductsAndQuantity jul: julList){storeAndCheckForMoreSalesOfProduct(jul);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){julList.add(thisItem);}
		julList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){julList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// August
		for(ToHoldSoldProductsAndQuantity aug: augList){storeAndCheckForMoreSalesOfProduct(aug);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){augList.add(thisItem);}
		augList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){augList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// September
		for(ToHoldSoldProductsAndQuantity sep: sepList){storeAndCheckForMoreSalesOfProduct(sep);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){sepList.add(thisItem);}
		sepList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){sepList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// October
		for(ToHoldSoldProductsAndQuantity oct: octList){storeAndCheckForMoreSalesOfProduct(oct);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){octList.add(thisItem);}
		octList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){octList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// November
		for(ToHoldSoldProductsAndQuantity nov: novList){storeAndCheckForMoreSalesOfProduct(nov);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){novList.add(thisItem);}
		novList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){novList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	
		// December
		for(ToHoldSoldProductsAndQuantity dec: decList){storeAndCheckForMoreSalesOfProduct(dec);}
		for(ToHoldSoldProductsAndQuantity thisItem: dupCheckList){decList.add(thisItem);}
		decList.clear();
		dupCheckList.remove(0);
		for(ToHoldSoldProductsAndQuantity obj: dupCheckList){decList.add(obj);}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	}

}