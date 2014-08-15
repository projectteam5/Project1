import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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
		//orderDateTextField.setText(new Date().toGMTString());
		orderDateTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
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
			comboBoxList.addItem(p.getProductID());
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
		//expectedDeliveryDateTextField.setText(new Date().toGMTString());
		expectedDeliveryDateTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
		dateReceivedTextField = new JTextField();
		panel.add(dateReceivedTextField);
		dateReceivedTextField.setEditable(true);
		dateReceivedTextField.addActionListener(this);
		//dateReceivedTextField.setText(new Date().toGMTString());
		dateReceivedTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
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
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public Order getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JLabel getLabel3() {
		return label3;
	}

	public void setLabel3(JLabel label3) {
		this.label3 = label3;
	}

	public JLabel getLabel4() {
		return label4;
	}

	public void setLabel4(JLabel label4) {
		this.label4 = label4;
	}

	public JLabel getLabel5() {
		return label5;
	}

	public void setLabel5(JLabel label5) {
		this.label5 = label5;
	}

	public JLabel getLabel6() {
		return label6;
	}

	public void setLabel6(JLabel label6) {
		this.label6 = label6;
	}

	public JLabel getLabel7() {
		return label7;
	}

	public void setLabel7(JLabel label7) {
		this.label7 = label7;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
	}

	public JTextField getOrderDateTextField() {
		return orderDateTextField;
	}

	public void setOrderDateTextField(JTextField orderDateTextField) {
		this.orderDateTextField = orderDateTextField;
	}

	public JComboBox<String> getComboBoxList() {
		return comboBoxList;
	}

	public void setComboBoxList(JComboBox<String> comboBoxList) {
		this.comboBoxList = comboBoxList;
	}

	public JTextField getQuantityTextField() {
		return quantityTextField;
	}

	public void setQuantityTextField(JTextField quantityTextField) {
		this.quantityTextField = quantityTextField;
	}

	public JTextField getExpectedDeliveryDateTextField() {
		return expectedDeliveryDateTextField;
	}

	public void setExpectedDeliveryDateTextField(
			JTextField expectedDeliveryDateTextField) {
		this.expectedDeliveryDateTextField = expectedDeliveryDateTextField;
	}

	public JTextField getDateReceivedTextField() {
		return dateReceivedTextField;
	}

	public void setDateReceivedTextField(JTextField dateReceivedTextField) {
		this.dateReceivedTextField = dateReceivedTextField;
	}

	public JCheckBox getReceivedTextField() {
		return receivedTextField;
	}

	public void setReceivedTextField(JCheckBox receivedTextField) {
		this.receivedTextField = receivedTextField;
	}

	public JButton getSave() {
		return save;
	}

	public void setSave(JButton save) {
		this.save = save;
	}

	public JButton getClear() {
		return clear;
	}

	public void setClear(JButton clear) {
		this.clear = clear;
	}

	public JButton getMenu() {
		return menu;
	}

	public void setMenu(JButton menu) {
		this.menu = menu;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNewOrderId() {
		return newOrderId;
	}

	public void setNewOrderId(String newOrderId) {
		this.newOrderId = newOrderId;
	}

	public Date getNewOrderDate() {
		return newOrderDate;
	}

	public void setNewOrderDate(Date newOrderDate) {
		this.newOrderDate = newOrderDate;
	}

	public Product getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}

	public int getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(int newQuantity) {
		this.newQuantity = newQuantity;
	}

	public Date getNewExpectedDeliveryDate() {
		return newExpectedDeliveryDate;
	}

	public void setNewExpectedDeliveryDate(Date newExpectedDeliveryDate) {
		this.newExpectedDeliveryDate = newExpectedDeliveryDate;
	}

	public Date getNewReceivedDate() {
		return newReceivedDate;
	}

	public void setNewReceivedDate(Date newReceivedDate) {
		this.newReceivedDate = newReceivedDate;
	}

	public boolean isNewReceived() {
		return newReceived;
	}

	public void setNewReceived(boolean newReceived) {
		this.newReceived = newReceived;
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
				//quantity = Integer.parseInt(text);	//getting the String back as an Integer
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
					newOrderId = label1.getText();
					
					//Locale localDate = new Locale("en", "GB");
					//DateFormat df = new SimpleDateFormat("dd/MM/yyyy", localDate);					
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					
					//newOrderDate = df.parse(label2.getText());
					newOrderDate = DateFormat.getDateInstance().parse(label2.getText());
					newProduct = retrieveProduct(label3.getText());
					newQuantity = Integer.parseInt(label4.getText());
					newExpectedDeliveryDate = DateFormat.getDateInstance().parse(label5.getText());
					newReceivedDate = DateFormat.getDateInstance().parse(label6.getText());
					
					boolean boo = Boolean.parseBoolean(label7.getText());
					newReceived = boo;
					
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
				System.out.println("error is here");
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
	
	public Product retrieveProduct(String productID){
		Product productReturned = null;
		for(Product product : RetailSystem.getInstance().getProducts()){
			if(product.getProductID().equals(productID)){
				productReturned = product;
			}
		}
		return productReturned;
	}
}