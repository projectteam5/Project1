import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuGUI extends JFrame {
	private JButton buttonUser;
	private JButton buttonCustomer;
	private JButton buttonProduct;
	private JButton buttonSupplier;
	private JButton buttonOrder;
	private JButton buttonAvailability;
	private JButton buttonViewOrder;
	private JButton buttonSave;

	public MenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, 2));
		buttonUser = new JButton("Manage Users");
		buttonCustomer = new JButton("Manage Customers");
		buttonProduct = new JButton("Manage Products");
		buttonSupplier = new JButton("Manage Suppliers");
		buttonOrder = new JButton("Manage Orders");
		buttonAvailability = new JButton("View Stock");
		buttonViewOrder = new JButton("View Order");
		buttonSave = new JButton("Save");
		// if it is a manager i can see Manage Customer, Manage Product
		// Manage Supplier, Manage Order
		if (RetailSystem.getInstance().getCurrentUserType()
				.equalsIgnoreCase("Manager")) {
			panel.add(buttonCustomer);
			panel.add(buttonProduct);
			panel.add(buttonSupplier);
			panel.add(buttonOrder);
			panel.add(buttonAvailability);
			panel.add(buttonViewOrder);
			panel.add(buttonUser);
		} else {
			panel.add(buttonCustomer);
			panel.add(buttonAvailability);
			panel.add(buttonViewOrder);
		}

		buttonSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SupplierMenuGUI supplierGUI = new SupplierMenuGUI();
				closeMenu();
			}
		});

		buttonCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
				closeMenu();
			}
		});

		buttonUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserGUI userGui = new UserGUI();
				closeMenu();
			}
		});

		buttonAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockGUI availableStock = new StockGUI();
				closeMenu();

			}
		});

		buttonViewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAllOrdersGUI allOrders = new ViewAllOrdersGUI();
				closeMenu();

			}
		});

		buttonProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();
				closeMenu();

			}
		});

		buttonOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderGUI orderGUI = new OrderGUI();
				closeMenu();

			}
		});
		
		this.setVisible(true);

	}

	public void closeMenu() {
		this.setVisible(false);
	}

}
