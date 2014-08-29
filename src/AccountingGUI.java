import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AccountingGUI extends JPanel{

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

	public AccountingGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitleMain = new JLabel("Profit & Loss Account");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		
		labelDateRange = new JLabel("From: "+Accounting.subtractMonthsFromDateString()+" To: "+DateFormat.getDateInstance().format(new Date()));
		
		salesIncome = new JLabel("Sales Income");
		salesIncomeLabel = new JLabel();
		salesIncomeLabel.setText("€"+Accounting.salesIncome());
		
		lessCostOfGoods = new JLabel("Less Cost of Goods Sold");
		
		openingStock = new JLabel("Opening Stock");
		openingStockLabel = new JLabel();
		openingStockLabel.setText("€"+Accounting.openingStock());
		
		goodsPurchase= new JLabel("Purchases");
		goodsPurchaseLabel = new JLabel();
		goodsPurchaseLabel.setText("€"+Accounting.goodsPurchase());
		
		closingStock = new JLabel("Closing Stock");
		closingStockLabel = new JLabel();
		closingStockLabel.setText("€"+Accounting.closingStock());
		
		costOfSales = new JLabel("Cost of Sales");
		costOfSalesLabel = new JLabel();
		costOfSalesLabel.setText("€"+Accounting.costOfSales());
		
		grossProfit = new JLabel("Gross Profit");
		grossProfitLabel = new JLabel();
		grossProfitLabel.setText("€"+Accounting.grossProfit());
		
		this.add(labelTitleMain);
		
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
		
		setVisible(true);
		
	}

}