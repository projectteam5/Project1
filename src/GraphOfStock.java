import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraphOfStock extends JPanel {
	private ArrayList<Stock> stocks;
	//private DataSetValue dataSet;
	
	public GraphOfStock() {
		
		this.setLayout(new GridLayout(0,1));
		
		JFreeChart stockGraph = ChartFactory.createBarChart("Current stock levels","Products","Units",createDataSet(),PlotOrientation.VERTICAL,true, false, false);
	
		ChartPanel chart = new ChartPanel(stockGraph);
		
		chart.setPreferredSize(new java.awt.Dimension(500, 270));
		
		this.add(chart);
	
	}
	private CategoryDataset createDataSet(){
		
		String products = "Product"; //column key
		stocks = RetailSystem.getInstance().getStocks();
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(Stock s:stocks){
			dataset.addValue(s.getUnits(), s.getProduct().getName(), products); 
		}
		
		return dataset;
		
	}

}
