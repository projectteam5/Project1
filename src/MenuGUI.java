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
		panel.setLayout(new GridLayout(4, 2));
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
			panel.add(buttonSave);

		} else {
			panel.add(buttonCustomer);
			panel.add(buttonAvailability);
			panel.add(buttonViewOrder);
		}

		buttonSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SupplierGUI supplierGUI = new SupplierGUI();
			}
		});

		buttonCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGUI customerGui = new CustomerGUI();
			}
		});

		buttonUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserGUI userGui = new UserGUI();
			}
		});

		buttonAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvailableStockLevelsGUI availableStock = new AvailableStockLevelsGUI();

			}
		});

		buttonProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductMenuGUI productMenuGUI = new ProductMenuGUI();

			}
		});

		buttonOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderGUI orderGUI = new OrderGUI();

			}
		});
		
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter userFile;
					userFile = new FileWriter("users.txt");
					DataBase.writeUsers(RetailSystem.getInstance().getUsers(),userFile);
					userFile.close();// close the user file
					userFile = new FileWriter("products.txt");
					DataBase.writeProducts(RetailSystem.getInstance().getProducts(),userFile);
					userFile.close();// close the user file	
					userFile = new FileWriter("suppliers.txt");
					DataBase.writeSuppliers(RetailSystem.getInstance().getSuppliers(),userFile);
					userFile.close();// close the user file
					userFile = new FileWriter("customers.txt");
					DataBase.writeCustomers(RetailSystem.getInstance().getCustomers(),userFile);
					userFile.close();// close the user file
					userFile = new FileWriter("orders.txt");
					DataBase.writeOrders(RetailSystem.getInstance().getOrders(),userFile);
					userFile.close();// close the user file
					userFile = new FileWriter("stocks.txt");
					DataBase.writeStocks(RetailSystem.getInstance().getStocks(),userFile);
					userFile.close();// close the user file
					JOptionPane.showMessageDialog(null, "Data have been saved!", "Notification", JOptionPane.INFORMATION_MESSAGE );
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}

				

			}
		});

		this.setVisible(true);

	}

}
