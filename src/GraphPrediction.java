import java.awt.Color;

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
   
    public class GraphPrediction extends JPanel{
      
      public GraphPrediction() {
            
        XYSeries                series1   = new XYSeries("Hard Drives");
                                series1.add(20, 10);
                                series1.add(40, 20);
                                series1.add(70, 50);
   
        XYSeries                series2   = new XYSeries("PCs");
                                series2.add(20, 30);
                                series2.add(40, 40);
                                series2.add(70, 10);
                        
        XYSeriesCollection      xyDataset = new XYSeriesCollection();
                                xyDataset.addSeries(series1);
                                xyDataset.addSeries(series2);
   
        JFreeChart              chart     = ChartFactory.createXYLineChart("Sales Prediction","Sales in Euro","Time",xyDataset,PlotOrientation.VERTICAL,true,false,false);
                                chart.setBackgroundPaint(Color.gray); 
                        
        XYPlot                  plot      = (XYPlot) chart.getPlot();
                                plot.setBackgroundPaint       (Color.white);
                                plot.setDomainGridlinePaint   (Color.GREEN);
                                plot.setRangeGridlinePaint    (Color.orange);
                                plot.setAxisOffset            (new RectangleInsets(50, 0, 20, 5));
                                plot.setDomainCrosshairVisible(true);
                                plot.setRangeCrosshairVisible (true);
   
        XYLineAndShapeRenderer  renderer  = (XYLineAndShapeRenderer) plot.getRenderer();      
                                renderer.setBaseShapesVisible(true);
                                renderer.setBaseShapesFilled (true);
   
        ChartPanel              chartPanel     = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        this.add(chartPanel);
                                              
      }  
   
    }