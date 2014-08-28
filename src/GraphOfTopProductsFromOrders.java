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

public class GraphOfTopProductsFromOrders extends JPanel {

   private static final long serialVersionUID = 1L;
   @SuppressWarnings("deprecation")
   static Date date = new Date();
   private ArrayList<Order> lastMonth;
   private ArrayList<Order> secondLastMonth;
   private ArrayList<Order> thirdLastMonth;
   private DataSetValue firstDS;
   private DataSetValue secondDS;
   private DataSetValue thirdDS;

   public GraphOfTopProductsFromOrders(String applicationTitle, String chartTitle) {
	    lastMonth = new ArrayList<Order>();
	    secondLastMonth = new ArrayList<Order>();
	    thirdLastMonth = new ArrayList<Order>();
	    this.setLayout(new GridLayout(0,1));

        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createBarChart("Best sellers by orders", "Products", "Amount", createDataset(),PlotOrientation.VERTICAL, true, true, false);
        
        // Adding chart into a chart panel

        ChartPanel chartPanel = new ChartPanel(pieChart);
        
      
        // setting default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        this.add(chartPanel);
        
    }
  
   private CategoryDataset createDataset() {
     // row keys...
      final String january = "Previous Month";
      final String feb = "2nd Last Month";
      final String mar = "3rd Last Month";

      // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      
      seperateAndRedefineStocksPerMonth(RetailSystem.getInstance().getOrders());
      findDataValuesForLastMonth(lastMonth,1); 
      dataset.addValue(firstDS.getValue1(), firstDS.getName1(),january);
      dataset.addValue(firstDS.getValue2(), firstDS.getName2(),january);
      dataset.addValue(firstDS.getValue3(), firstDS.getName3(),january);
      
      
      findDataValuesForLastMonth(secondLastMonth,2); 
      dataset.addValue(secondDS.getValue1(), secondDS.getName1(),feb);
      dataset.addValue(secondDS.getValue2(), secondDS.getName2(),feb);
      dataset.addValue(secondDS.getValue3(), secondDS.getName3(),feb);
      
      
      findDataValuesForLastMonth(thirdLastMonth,3); 
      dataset.addValue(thirdDS.getValue1(),  thirdDS.getName1(), mar);
      dataset.addValue(thirdDS.getValue2(),  thirdDS.getName2(), mar);
      dataset.addValue(thirdDS.getValue3(),  thirdDS.getName3(), mar);
      
      return dataset;
  }
   
   // previous months data values
   public void findDataValuesForLastMonth(ArrayList<Order> month, int monthID){
	   int numberOneQuantity=0;
	   int numberTwoQuantity=0;
	   int numberThreeQuantity=0;
	   // column keys...
	   String numberOneName = "";
	   String numberTwoName = "";
	   String numberThreeName = "";	   
	  
	  int highestQuantity=0,secondHighestQuantity=0,thirdHighestQuantity=0;
	      
      for(Order orders:month){
    	  numberOneQuantity=orders.getQuantity();
    	  for(Order ord:month){
    		 highestQuantity = ord.getQuantity();
    		 if(numberOneQuantity<=highestQuantity){
    			 numberOneQuantity = highestQuantity;
    			 numberOneName = ord.getProduct().getName();
    		 }
    	  }
      }
      
	  for(Order orders1:month){
		  numberTwoQuantity = orders1.getQuantity();
    	  for(Order ord1:month){
    		 secondHighestQuantity=ord1.getQuantity();
    		 if(numberTwoQuantity<=secondHighestQuantity && secondHighestQuantity<numberOneQuantity){
    			 numberTwoQuantity=secondHighestQuantity;
    			 numberTwoName = ord1.getProduct().getName();
    		 }
    	  }
	  }
	   
	  for(Order orders12:month){
		  numberThreeQuantity=orders12.getQuantity();
    	  for(Order ord12:month){
    		 thirdHighestQuantity=ord12.getQuantity();
    		 if(numberThreeQuantity<=thirdHighestQuantity && thirdHighestQuantity<numberTwoQuantity){
    			 numberThreeQuantity=thirdHighestQuantity;
    			 numberThreeName = ord12.getProduct().getName();
    		 }
    	  }
	  }
	  
	  if(monthID==1){
		  System.out.println("1="+numberOneQuantity+numberTwoQuantity+numberThreeQuantity);
		  firstDS = new DataSetValue(numberOneQuantity, numberOneName, numberTwoQuantity,
				  numberTwoName, numberThreeQuantity, numberThreeName);
	  }
	  else if(monthID==2){
		  System.out.println("2="+numberOneQuantity+numberTwoQuantity+numberThreeQuantity);
		  secondDS = new DataSetValue(numberOneQuantity, numberOneName, numberTwoQuantity,
				  numberTwoName, numberThreeQuantity, numberThreeName); 
	  }
	  else
		  System.out.println("3="+numberOneQuantity+numberTwoQuantity+numberThreeQuantity);
		  thirdDS = new DataSetValue(numberOneQuantity, numberOneName, numberTwoQuantity,
				  numberTwoName, numberThreeQuantity, numberThreeName);
   }
   
   
   // Function to separate the months of orders and add to specific list
   public void seperateAndRedefineStocksPerMonth(ArrayList<Order> orders){
	   for(Order o: orders){
		   if(addDaysFromDateString(o.getOrderDate(), date)==1){
			   lastMonth.add(o);
		   }
		   else if(addDaysFromDateString(o.getOrderDate(), date)==2){
			   secondLastMonth.add(o);
		   }
		   else if(addDaysFromDateString(o.getOrderDate(), date)==3){
			   thirdLastMonth.add(o);
		   }
	   }
   }
   
   // Function to return the difference in months from today to order date
   public static int addDaysFromDateString(Date orderDate, Date todaysDate) {
       int result;
       
       Calendar calendar = new GregorianCalendar();
       Calendar calendar2 = new GregorianCalendar();
       DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
      
       calendar.setTime(todaysDate);
       calendar2.setTime(orderDate);
       int a = calendar.get(Calendar.MONTH);
       int b = calendar2.get(Calendar.MONTH);
       
       result = a-b;
      
       return result;
   }
}