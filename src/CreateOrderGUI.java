import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

/*
 * Does everyone like comments?
 * .||.. :P
 * .|||.. :P
 * .||||.. :P
 * .|||||.. :P
 */
public class CreateOrderGUI extends JFrame implements ActionListener {
	private JPanel panel;
	private Container container;
	private ArrayList<Product> products;
	
	private ArrayList<Order> orders;
	private Order newOrder;
	
	//giving the user some 'feedback' with a textArea component, will be set unEditable
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	
	//giving these VERY descriptive names, will make more sense later...
	private JTextField idTextField;	//not sure about letting the user full control over the naming of ID's...
									//will leave as-is for now
	private JTextField orderDateTextField;
	private JComboBox<String> comboBoxList;	//using this combo list to search product by productID
	private JTextField quantityTextField;
	private JTextField expectedDeliveryDateTextField;
	//these will be hidden as we are creating an order...
	private JTextField dateReceivedTextField;	//set to null for now
	private JCheckBox receivedTextField;	//set to false for now (order has not been received so, you know..)
	
	private JButton save;
	private JButton clear;
	private JButton menu;
	private JButton exit;
	
	//at various points this handy String type will be used in the methods to appended to
	//initialize here to use throughout :)
	private String text;
	
	private String newOrderId;
	private Date newOrderDate;
	private Product newProduct;
	private int newQuantity;
	private Date newExpectedDeliveryDate;
	private Date newReceivedDate;
	private boolean newReceived;
	
	public CreateOrderGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle(this.getClass().toString());
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);	//null sets the frame to centre
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(10,25));
		
		label1 = new JLabel("id");
		panel.add(label1);
		
		label2 = new JLabel("date");
		panel.add(label2);
		
		label3 = new JLabel("product");
		panel.add(label3);
		
		label4 = new JLabel("quantity");
		panel.add(label4);
		
		label5 = new JLabel("expected delivery date");
		panel.add(label5);
		
		label6 = new JLabel("date received");
		panel.add(label6);
		
		label7 = new JLabel("recieved?");
		panel.add(label7);
		
		//add the components to the panel, setting the id as hidden is an option going forward...
		idTextField = new JTextField(5);
		panel.add(idTextField);
		idTextField.setEditable(true);
		idTextField.addActionListener(this);
		idTextField.setText("enter id for order");
		
		orderDateTextField = new JTextField();
		panel.add(orderDateTextField);
		orderDateTextField.setEditable(true);
		orderDateTextField.addActionListener(this);
		orderDateTextField.setText(new Date().toGMTString());
		
		/*
		 * here is where i hope to fill up the comboBox list with product objects to choose from
		 */
		//getting the list of products to use in array list from the RetailSystem list
		products = RetailSystem.getInstance().getProducts();
		//Initializing a JComboBox as an ArrayList of type String to hold product objects
		comboBoxList = new JComboBox<String>();
		panel.add(comboBoxList);
		comboBoxList.setEditable(false);
		comboBoxList.addActionListener(this);
		
		comboBoxList.addItem("");	//the add here is just cosmetic...to hide the first list item at load
		//"looping through an ArrayList called products which hold Product objects, using 'p' as the placeholder"
		for(Product p : products) {
			comboBoxList.addItem(p.getProductID() 
					+ " | " + p.getName()
					+ " | " + p.getCost());
		}
		comboBoxList.setToolTipText("choose product");
		
		quantityTextField = new JTextField(5);
		panel.add(quantityTextField);
		quantityTextField.setEditable(true);
		quantityTextField.addActionListener(this);
		quantityTextField.setText("enter quantity of product");
		
		expectedDeliveryDateTextField = new JTextField();
		panel.add(expectedDeliveryDateTextField);
		expectedDeliveryDateTextField.setEditable(true);
		expectedDeliveryDateTextField.addActionListener(this);
		expectedDeliveryDateTextField.setText(new Date().toGMTString());
		
		dateReceivedTextField = new JTextField();
		panel.add(dateReceivedTextField);
		dateReceivedTextField.setEditable(true);
		dateReceivedTextField.addActionListener(this);
		dateReceivedTextField.setText(new Date().toGMTString());
		
		receivedTextField = new JCheckBox();
		panel.add(receivedTextField);
		receivedTextField.addActionListener(this);
		receivedTextField.setSelected(false);
		receivedTextField.setText("is recieved?");
		
		save = new JButton("save");
		clear = new JButton("clear");
		menu = new JButton("menu");
		exit = new JButton("exit");
		panel.add(save);
		panel.add(clear);
		panel.add(menu);
		panel.add(exit);
		
		save.addActionListener(this);
		clear.addActionListener(this);
		menu.addActionListener(this);
		exit.addActionListener(this);
		
		//initializing the String variable we declared up above ^
		text = "";
		newOrderId = "";
		newOrderDate = new Date();
		newProduct = new Product();
		newQuantity = 0;
		newExpectedDeliveryDate = new Date();
		newReceivedDate = new Date();
		newReceived = false;

		setVisible(true);
	}
	
	//terminates the running JVM
	//i.e. stops everything
	void shutdownGUI() {
		System.exit(0);
	}
	
	//
	void clearAll() {
		if(!label1.getText().isEmpty() || !label2.getText().isEmpty() || !label3.getText().isEmpty() 
				|| !label4.getText().isEmpty() || !label5.getText().isEmpty() 
				|| !label6.getText().isEmpty() || !label7.getText().isEmpty() ) {
			label1.setText("");
			label2.setText("");
			label3.setText("");
			label4.setText("");
			label5.setText("");
			label6.setText("");
			label7.setText("");
		}
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == idTextField) {
			try {
				if(idTextField.getText().equalsIgnoreCase("")||idTextField.getText().equals("enter id for order")) {	
					JOptionPane.showMessageDialog(null, "cannot be left blank!");
					idTextField.setText("");
				}
				text = idTextField.getText();
				label1.setText(text);
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
			}
		}
		
		if(target == orderDateTextField) {
			try {
				text = orderDateTextField.getText();
				label2.setText(text);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Date only");
				quantityTextField.setText(new Date().toGMTString());
				System.err.println(e);
				System.err.println(e.getMessage());
			}
		}
		
		if(target == comboBoxList) {
			try {
				text = comboBoxList.getSelectedItem().toString();
				label3.setText(text);
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
			}
		}
		
		if(target == quantityTextField) {
			int quantity;
			try {
				text = quantityTextField.getText();
				label4.setText(text);
				quantity = Integer.parseInt(text);	//getting the String back as an Integer
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Integer only");
				quantityTextField.setText("");
				System.err.println(e);
				System.err.println(e.getMessage());
			}
		}
		
		if(target == expectedDeliveryDateTextField) {
			try {
				text = expectedDeliveryDateTextField.getText();
				label5.setText(text);
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
			}
		}
		
		if(target == dateReceivedTextField) {
			try {
				text = dateReceivedTextField.getText();
				label6.setText(text);
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
			}
		}
		
		if(target == receivedTextField) {
			try {
				if(receivedTextField.isSelected()==false) {
					label7.setText("FALSE");
				} else {
					label7.setText("TRUE");
				}
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
			}
		}
		
		if(target == save) {
			try {
				//a bit verbose here...
				if(label1.getText().isEmpty() || label2.getText().isEmpty() || label3.getText().isEmpty() 
						|| label4.getText().isEmpty() || label5.getText().isEmpty() 
						|| label6.getText().isEmpty() || label7.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(this, "fields may not be blank");
					System.out.println("cannot write to database, "
							+ " fields cannot be empty");
				} else {
					orders = new ArrayList<Order>();
				newOrder = new Order(newOrderId, newOrderDate, newProduct, newQuantity, 
						newExpectedDeliveryDate, newReceivedDate, newReceived);
				orders.add(newOrder);
				RetailSystem.getInstance().getOrders().add(newOrder);
				JOptionPane.showMessageDialog(this, "order saved");
				
				//just to show what's happening...
				System.out.println(RetailSystem.getInstance().getOrders().size());
				System.out.println(RetailSystem.getInstance().getOrders());
				}
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
			}
		}
		
		if(target == clear) {
			try {
				clearAll();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "unable to clear label contents");
			}
		}
		
		if(target == menu) {
			try {
				MenuGUI menu = new MenuGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "cannot reach MenuGUI");
			}
		}
		
		if(target == exit) {
			try {
				shutdownGUI();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "unable to close the program");
			}
		}
	}
}