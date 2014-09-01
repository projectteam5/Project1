import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

@SuppressWarnings("serial")
public class AccountingGraph extends JPanel implements ActionListener {
	
	private JButton backButton;
	
	public AccountingGraph(String applicationTitle, String chartTitle) {
		
		this.setLayout(new GridLayout(0,1));
		
		createChart(createDataset(), "Accounting Chart");
		
        ChartPanel chartPanel = new ChartPanel(createChart(createDataset(), "Accounting Chart"));
        
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        backButton = new JButton("Return To Data");
        
        this.add(backButton);
        
        this.add(chartPanel);
        
        backButton.addActionListener(this);
        
        backButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				MenuGUI.getInstance().setPanelAction(new AccountingGUI());
			}
		});
        
        this.setVisible(true);
        
    }

    private PieDataset createDataset() {
    	
       final  DefaultPieDataset result = new DefaultPieDataset();
       
       result.setValue("Income", Accounting.salesIncome(AccountingGUI.getPeriod1(), AccountingGUI.getPeriod2()));
       result.setValue("Purchases", Accounting.goodsPurchase(AccountingGUI.getPeriod1(), AccountingGUI.getPeriod2()));
       
       return result;
        
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title,dataset,true,true,false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
        
    }
    
	public void actionPerformed(ActionEvent e) {
		
		
		
	}

}