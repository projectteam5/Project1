import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph extends JPanel {

   private static final long serialVersionUID = 1L;

   public Graph(String applicationTitle, String chartTitle) {
	   
	   this.setLayout(new GridLayout(0,1));

        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createBarChart(chartTitle, "Category", "Amount", createDataset(),PlotOrientation.VERTICAL, true, true, false);

        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
      
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        this.add(chartPanel);
        
    }
  
   private CategoryDataset createDataset() {
     
      // row keys...
      final String january = "January";

      // column keys...
      final String coolingFans = "Cooling Fans";

      // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      
      // add values to each 'bar' of chart
      //here, taking the units of this product we have in Stock
      //use a similar method for others
      for(Stock s : RetailSystem.getInstance().getStocks()) {
    	  if(s.getProduct().getName().contains("ICU Cam")) {
    		  dataset.addValue(s.getUnits(), january, coolingFans);
    	  }
      }
      
      
      /*
       * hardcode values into graph...
      dataset.addValue(1.0, january, hardDrives);  
     */
      
      return dataset;
  }
}