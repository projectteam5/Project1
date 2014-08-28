import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph extends JPanel {

   private static final long serialVersionUID = 1L;
   private Date date = new Date();

   public Graph(String applicationTitle, String chartTitle) {
	   
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
      final String january = "1st Month";
      final String feb = "2nd Month";
      final String mar = "3rd Month";
      // column keys...
      String numberOne = "";
      String numberTwo = "";
      String numberThree = "";
      
      int numberOneQuantity=0;
      int numberTwoQuantity=0;
      int numberThreeQuantity=0;

      // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      int i=0,j=0,n=0;
      for(Order orders:RetailSystem.getInstance().getOrders()){
    	  if(orders.getQuantity()>i){
    		  i=orders.getQuantity();
    		  numberOne = orders.getProduct().getName();
    		  numberOneQuantity = orders.getQuantity();
    	  }
      }
      
      for(Order orders:RetailSystem.getInstance().getOrders()){
    	  if(orders.getQuantity()>j&&orders.getQuantity()<i){
    		  j=orders.getQuantity();
    		  numberTwo = orders.getProduct().getName();
    		  numberTwoQuantity = orders.getQuantity();
    	  }
      }
      
      for(Order orders:RetailSystem.getInstance().getOrders()){
    	  if(orders.getQuantity()>n&&orders.getQuantity()<j){
    		  n=orders.getQuantity();
    		  numberThree = orders.getProduct().getName();
    		  numberThreeQuantity = orders.getQuantity();
    	  }
      }
      
      dataset.addValue(numberOneQuantity, january, numberOne);
      dataset.addValue(numberTwoQuantity, january, numberTwo);
      dataset.addValue(numberThreeQuantity, january, numberThree);
      
      dataset.addValue(numberOneQuantity, feb, numberOne);
      dataset.addValue(numberTwoQuantity, feb, numberTwo);
      dataset.addValue(numberThreeQuantity, feb, numberThree);
      
      dataset.addValue(numberOneQuantity, mar, numberOne);
      dataset.addValue(numberTwoQuantity, mar, numberTwo);
      dataset.addValue(numberThreeQuantity, mar, numberThree);
      
      return dataset;
  }
}