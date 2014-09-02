import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AccountingGUI extends JPanel implements ActionListener{
	
	private static JComboBox<String> accountingPeriod;
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
		
		this.setLayout(new GridLayout(0,1));
		
		labelTitleMain = new JLabel("Profit & Loss Account");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		
		choosePeriodLabel = new JLabel("Choose Preceding Months Accounting Period");
		accountingPeriod = new JComboBox<String>();
		
		for(int i = 1;i<=12;i++) {
			
			if(i==1) {
				accountingPeriod.addItem("Janurary");
			}
			if(i==2) {
				accountingPeriod.addItem("Feburary");
			}
			if(i==3) {
				accountingPeriod.addItem("March");
			}
			if(i==4) {
				accountingPeriod.addItem("April");
			}
			if(i==5) {
				accountingPeriod.addItem("May");
			}
			if(i==6) {
				accountingPeriod.addItem("June");
			}
			if(i==7) {
				accountingPeriod.addItem("July");
			}
			if(i==8) {
				accountingPeriod.addItem("August");
			}
			if(i==9) {
				accountingPeriod.addItem("September");
			}
			if(i==10) {
				accountingPeriod.addItem("October");
			}
			if(i==11) {
				accountingPeriod.addItem("November");
			}
			if(i==12) {
				accountingPeriod.addItem("December");
			}
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
        	
        	period = accountingPeriod.getSelectedIndex();
        	
        	labelDateRange.setText( "For: " + accountingPeriod.getSelectedItem() + " " + Calendar.getInstance().get(Calendar.YEAR) );
    		
        	salesIncomeLabel.setText("�"+Accounting.getIncomeForMonth(period));
        	
        	openingStockLabel.setText("�"+Accounting.getOpeningStockForMonth(period));
        	
        	goodsPurchaseLabel.setText("�"+Accounting.getPurchasesForMonth(period));
        	
        	closingStockLabel.setText("�"+Accounting.getClosingStockForMonth(period));
        	
        	costOfSalesLabel.setText("�"+Accounting.costOfSales(period));
        	
        	grossProfitLabel.setText("�"+Accounting.grossProfit(period));
        	
        }
        
        if(target == graphButton) {
        	
        	try {
        		
				MenuGUI.getInstance().setPanelAction( new AccountingGraph("","") );

			} catch(Exception e1) {
				
				System.err.println(e1);
				System.err.println(e1.getMessage());
				JOptionPane.showMessageDialog( this, "cannot reach AccountingGraph" );
				
			}
        	
        	getMonth();
        	
        }
        
    }

	public int getPeriod() {
		return period;
	}

	@SuppressWarnings("static-access")
	public void setPeriod(int period) {
		this.period = period;
	}
	
	public static int getMonth() {
		
		return period;
		
	}

}