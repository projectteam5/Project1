import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphForMonthlyProductUnitsSold extends JPanel {
	private static final long serialVersionUID = 1L;
	private String[][] matrix;
	private JButton backButton;
	private String title;
	private String monthFinder;
	private ToHoldSoldProductsAndQuantity convertedMatrixObject;
	private ToHoldSoldProductsAndQuantity listObject;

	ArrayList<String> idNamesList;
	ArrayList<ToHoldSoldProductsAndQuantity> monthList = new ArrayList<ToHoldSoldProductsAndQuantity>();
	ArrayList<ToHoldSoldProductsAndQuantity> dupCheckList = new ArrayList<ToHoldSoldProductsAndQuantity>();
	ArrayList<ToHoldSoldProductsAndQuantity> completeList = new ArrayList<ToHoldSoldProductsAndQuantity>();
	ArrayList<String> listOfProductNames = new ArrayList<String>();

	public GraphForMonthlyProductUnitsSold(String applicationTitle,
			String chartTitle) {
		// Getting the matrix that contains the date,products and quantities
		// from all sales
		this.setLayout(new BorderLayout(1, 1));
		this.title = applicationTitle;
		matrix = Invoice.productsSold();
		listObject = new ToHoldSoldProductsAndQuantity("", "", 0);
		dupCheckList.add(listObject);

		// Using the title to find out which monthly statistics to show
		if (title.equals("January")) {
			monthFinder = "Jan";
		} else if (title.equals("February")) {
			monthFinder = "Feb";
		} else if (title.equals("March")) {
			monthFinder = "Mar";
		} else if (title.equals("April")) {
			monthFinder = "Apr";
		} else if (title.equals("May")) {
			monthFinder = "May";
		} else if (title.equals("June")) {
			monthFinder = "Jun";
		} else if (title.equals("July")) {
			monthFinder = "Jul";
		} else if (title.equals("August")) {
			monthFinder = "Aug";
		} else if (title.equals("September")) {
			monthFinder = "Sep";
		} else if (title.equals("October")) {
			monthFinder = "Oct";
		} else if (title.equals("November")) {
			monthFinder = "Nov";
		} else if (title.equals("December")) {
			monthFinder = "Dec";
		}

		// Finding out how many Individual products with quantities are in
		// Matrix
		int count = 0;

		for (Invoice invoice : RetailSystem.getInstance().getInvoices()) {
			count = count + invoice.getSale().getLineItems().size();
		}
		// Converting the matrix to a list of objects
		for (int i = 0; i < count; i++) {
			convertedMatrixObject = new ToHoldSoldProductsAndQuantity(
					matrix[i][0], matrix[i][1],
					Double.parseDouble(matrix[i][2]));
			completeList.add(convertedMatrixObject);
		}

		// Separating the complete list of itemized sales into lists by months
		for (ToHoldSoldProductsAndQuantity g : completeList) {
			if (g.getDate().contains(monthFinder)) {
				monthList.add(g);
			}
		}

		// Checking each list for multiple product and quantities and resorting
		// such that each month list contains unique products with calculated
		// quantities if the product appeared more than once on the list
		resortMonthlyListToCalculateMulipleSalesOfProducts();

		// based on the dataset we create the chart
		JFreeChart barChart = ChartFactory.createBarChart(
				"Units of Products Sold", "Products", "Units", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);

		// Adding chart into a chart panel

		ChartPanel chartPanel = new ChartPanel(barChart);

		// setting default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

		backButton = new JButton("Return To SubMenu");

		this.add(chartPanel, BorderLayout.CENTER);
		this.add(backButton, BorderLayout.SOUTH);
		setVisible(true);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuGUI.getInstance().setPanelAction(
						new GraphMontlySelectSubMenu());
			}
		});
	}

	private CategoryDataset createDataset() {
		// row keys...
		final String monthName = title;

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (ToHoldSoldProductsAndQuantity listItem : monthList) {
			dataset.addValue(listItem.getQuantity(), listItem.getName(),
					monthName);
		}

		return dataset;
	}

	// Function to check for two or more products sold in the same month that
	// is used in conjunction with the resortMonthlyLists function
	public void storeAndCheckForMoreSalesOfProduct(
			ToHoldSoldProductsAndQuantity sentListItem) {
		int i = 0;
		for (ToHoldSoldProductsAndQuantity thisList : dupCheckList) {
			if (sentListItem.getName() == thisList.getName()) {
				thisList.setQuantity(thisList.getQuantity()
						+ sentListItem.getQuantity());
				i = 0;
			} else
				i = 1;
		}

		if (i == 1) {
			dupCheckList.add(sentListItem);
			i = 0;
		}
	}

	// Checking each list for multiple product and quantities and resorting
	// such that each month list contains unique products with calculated
	// quantities if the product appeared more than once on the list
	// The monthly list is sent to a method to check for multiple sales
	// of the same product within the month and then added to collectively
	// to a new list such that each list contains unique products. This original
	// monthly list is then deleted with the contents of the new list passed
	// back to
	// the original list.

	public void resortMonthlyListToCalculateMulipleSalesOfProducts() {
		// January
		for (ToHoldSoldProductsAndQuantity j : monthList) {
			storeAndCheckForMoreSalesOfProduct(j);
		}
		for (ToHoldSoldProductsAndQuantity thisItem : dupCheckList) {
			monthList.add(thisItem);
		}
		monthList.clear();
		dupCheckList.remove(0);
		for (ToHoldSoldProductsAndQuantity obj : dupCheckList) {
			monthList.add(obj);
		}
		dupCheckList.clear();
		dupCheckList.add(listObject);
	}

}