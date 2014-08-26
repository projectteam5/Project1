import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph extends JFrame {

   private static final long serialVersionUID = 1L;

   public Graph(String applicationTitle, String chartTitle) {
        super(applicationTitle);

        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createBarChart(chartTitle, "Category", "Amount", createDataset(),PlotOrientation.VERTICAL, true, true, false);

        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
      
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
      
        // add to contentPane
        setContentPane(chartPanel);
    }
  
   private CategoryDataset createDataset() {
     
      // row keys...
      final String january = "January";
      final String february = "February";
      final String march = "March";

      // column keys...
      final String hardDrives = "Hard Drives";
      final String webCams = "Web Cams";
      final String usbKeys = "USB Keys";
      final String coolingFans = "Cooling Fans";
      final String burners = "Burners";

      // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

      dataset.addValue(1.0, january, hardDrives);
      dataset.addValue(4.0, january, webCams);
      dataset.addValue(3.0, january, usbKeys);
      dataset.addValue(5.0, january, coolingFans);
      dataset.addValue(5.0, january, burners);

      dataset.addValue(5.0, february, hardDrives);
      dataset.addValue(7.0, february, webCams);
      dataset.addValue(6.0, february, usbKeys);
      dataset.addValue(8.0, february, coolingFans);
      dataset.addValue(4.0, february, burners);

      dataset.addValue(4.0, march, hardDrives);
      dataset.addValue(3.0, march, webCams);
      dataset.addValue(2.0, march, usbKeys);
      dataset.addValue(3.0, march, coolingFans);
      dataset.addValue(6.0, march, burners);
     
      return dataset;
     
  }

   public static void main(String[] args) {
      Graph chart = new Graph("Stock", "Current Stock Levels");
      chart.pack();
      chart.setVisible(true);
   }
}