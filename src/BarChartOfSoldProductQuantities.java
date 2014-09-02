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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartOfSoldProductQuantities extends JPanel {
	private static final long serialVersionUID = 1L;  
   private String[][] matrix;
  
   ArrayList<String> idNamesList;
   
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
   ArrayList<String> listOfProductNames = new ArrayList<String>();
   private ToHoldSoldProductsAndQuantity convertedMatrixObject;
   private ToHoldSoldProductsAndQuantity listObject;
  
   private DataSetValue dataset;

   public BarChartOfSoldProductQuantities(String applicationTitle, String chartTitle) {
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

        // based on the dataset we create the chart
        JFreeChart barChart = ChartFactory.createBarChart("Units of Products Sold Per Month", "Products", "Units", createDataset(),PlotOrientation.VERTICAL, true, true, false);
        
        // Adding chart into a chart panel

        ChartPanel chartPanel = new ChartPanel(barChart);
        
      
        // setting default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        this.add(chartPanel);
        
    }
  
   private CategoryDataset createDataset() {
     // row keys...
      final String jan = "January";
      final String feb = "February";
      final String mar = "March";
      final String apr = "Apr";
      final String may = "May";
      final String jun = "June";
      final String jul = "July";
      final String aug = "August";
      final String sep = "September";
      final String oct = "October";
      final String nov = "November";
      final String dec = "December";

      // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      
      for(ToHoldSoldProductsAndQuantity listItem: janList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),jan);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: febList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),feb);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: marList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),mar);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: aprList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),apr);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: mayList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),may);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: junList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),jun);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: julList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),jul);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: augList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),aug);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: sepList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),sep);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: octList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),oct);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: novList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),nov);    
      }
      for(ToHoldSoldProductsAndQuantity listItem: decList){ 
    	  dataset.addValue(listItem.getQuantity(), listItem.getName(),dec);    
      }      
      return dataset;
  }
   
   // previous months data values
   
	  
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