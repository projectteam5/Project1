import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AccountingGUI extends JPanel implements ActionListener{
	
	private static JComboBox<Integer> accountingPeriod;
	private JButton selectButton;
	private JButton graphButton;

	private JLabel labelTitleMain;
	private JLabel salesIncomeLabel;
	private JLabel openingStockLabel;
	private JLabel salesIncome;
	private JLabel openingStock;
	private JLabel lessCostOfGoods;
	private JLabel goodsPurchase;
	private JLabel goodsPurchaseLabel;
	private JLabel closingStock;
	private JLabel closingStockLabel;
	private JLabel costOfSales;
	private JLabel costOfSalesLabel;
	private JLabel grossProfit;
	private JLabel grossProfitLabel;
	private JLabel labelDateRange;

	private JLabel choosePeriodLabel;
	
	private static int period;

	public AccountingGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitleMain = new JLabel("Profit & Loss Account");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		
		choosePeriodLabel = new JLabel("Choose Accounting Period");
		accountingPeriod = new JComboBox<Integer>();
		for(int i = 1;i<=12;i++) {
			accountingPeriod.addItem(i);
		}
		
		selectButton = new JButton("Select Period");
		
		labelDateRange = new JLabel();
				
		salesIncome = new JLabel("Sales Income");
		salesIncomeLabel = new JLabel();
		
		lessCostOfGoods = new JLabel("Less Cost of Goods Sold");
		
		openingStock = new JLabel("Opening Stock");
		openingStockLabel = new JLabel();
		
		goodsPurchase= new JLabel("Purchases");
		goodsPurchaseLabel = new JLabel();
		
		closingStock = new JLabel("Closing Stock");
		closingStockLabel = new JLabel();
		
		costOfSales = new JLabel("Cost of Sales");
		costOfSalesLabel = new JLabel();
		
		grossProfit = new JLabel("Gross Profit");
		grossProfitLabel = new JLabel();
		
		graphButton = new JButton("Show Graph");
		
		this.add(labelTitleMain);
		
		this.add(choosePeriodLabel);
		this.add(accountingPeriod);
		
		this.add(selectButton);
		
		this.add(labelDateRange);
		
		this.add(salesIncome);
		this.add(salesIncomeLabel);
		
		this.add(lessCostOfGoods);
		
		this.add(openingStock);
		this.add(openingStockLabel);
		
		this.add(goodsPurchase);
		this.add(goodsPurchaseLabel);
		
		this.add(closingStock);
		this.add(closingStockLabel);
		
		this.add(costOfSales);
		this.add(costOfSalesLabel);
		
		this.add(grossProfit);
		this.add(grossProfitLabel);
		
		this.add(graphButton);
		
		accountingPeriod.addActionListener(this);
		selectButton.addActionListener(this);
		graphButton.addActionListener(this);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object target = e.getSource();
		
        if( target == selectButton ){
        	
        	period = Integer.parseInt( accountingPeriod.getSelectedItem().toString() );
        	
        	String s1 = Accounting.dateOpsMonthsFromDateString( - period );
        	
        	String s2 = s1;
        	
        	s2 = Accounting.dateOpsMonthsFromDateString( -- period );
        	
        	String s3 = s2;
        	
        	s3 = Accounting.dateOpsMonthsFromDateString( - period );
        	
        	labelDateRange.setText( "From: " + s1 +" To: " + s3 );
    		
        	salesIncomeLabel.setText("€"+Accounting.salesIncome(s1, s3));
        	
        	openingStockLabel.setText("€"+Accounting.openingStock(s1, s3));
        	
        	goodsPurchaseLabel.setText("€"+Accounting.goodsPurchase(s1, s3));
        	
        	closingStockLabel.setText("€"+Accounting.closingStock(s1, s3));
        	
        	costOfSalesLabel.setText("€"+Accounting.costOfSales(s1, s3));
        	
        	grossProfitLabel.setText("€"+Accounting.grossProfit(s1, s3));
        	
        }
        
        if(target == graphButton) {
        	
        	try {
        		
				MenuGUI.getInstance().setPanelAction( new AccountingGraph("","") );

			} catch(Exception e1) {
				
				System.err.println(e1);
				System.err.println(e1.getMessage());
				JOptionPane.showMessageDialog( this, "cannot reach AccountingGraph" );
				
			}
        	
        	getPeriod1();
        	
        	getPeriod2();
        	
        }
        
    }

	public int getPeriod() {
		return period;
	}

	@SuppressWarnings("static-access")
	public void setPeriod(int period) {
		this.period = period;
	}
	
	public static String getPeriod1() {
		
		period = Integer.parseInt( accountingPeriod.getSelectedItem().toString() );
    	
    	String s1 = Accounting.dateOpsMonthsFromDateString( - period );
    	
    	return s1;
		
	}
	
	public static String getPeriod2() {
		
		period = Integer.parseInt( accountingPeriod.getSelectedItem().toString() );
    	
    	String s1 = Accounting.dateOpsMonthsFromDateString( - period );
    	
    	String s2 = s1;
    	
    	s2 = Accounting.dateOpsMonthsFromDateString( -- period );
    	
    	String s3 = s2;
    	
    	s3 = Accounting.dateOpsMonthsFromDateString( - period );
    	
    	return s3;
		
	}

}