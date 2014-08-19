import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateOrderGUI extends JFrame implements ActionListener {
	
	private JTextField idTextField;
	private JTextField orderDateTextField;
	private JComboBox<String> comboBoxList;
	private JTextField quantityTextField;
	private JTextField expectedDeliveryDateTextField;
	
	private JTextField dateReceivedTextField;
	private JCheckBox receivedCheckBox;
	
	private JButton submitButton;
	
	private JButton returnToMainMenu;
	
	private String newOrderId;
	private Date newOrderDate;
	private Product newProduct;
	private int newQuantity;
	private Date newExpectedDeliveryDate;
	
	private Date newReceivedDate;
	private boolean newReceived;
	
	public CreateOrderGUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setTitle("Create Order");
		
		JPanel panel = new JPanel();
		Container container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0,1));
		
		JLabel label1 = new JLabel("OrderID");
		idTextField = new JTextField();
		
		JLabel label2 = new JLabel("Order Date");
		orderDateTextField = new JTextField();
		orderDateTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
		JLabel label3 = new JLabel("Product ID");
		comboBoxList = new JComboBox<String>();
		comboBoxList.addItem("");
		for(Product p : RetailSystem.getInstance().getProducts()) {
			comboBoxList.addItem(p.getProductID());
		}
		
		JLabel label4 = new JLabel("Product Quantity");
		quantityTextField = new JTextField();
		
		JLabel label5 = new JLabel("Expected Delivery Date");
		expectedDeliveryDateTextField = new JTextField();
		expectedDeliveryDateTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
		
		JLabel label6 = new JLabel("Actual Delivery Date");
		dateReceivedTextField = new JTextField();
		dateReceivedTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
		JLabel label7 = new JLabel("Order Recieved?");
		receivedCheckBox = new JCheckBox();
		
		submitButton = new JButton("Submit");
		
		returnToMainMenu = new JButton("Main Menu");
		
		
		panel.add(label1);
		panel.add(idTextField);
		
		panel.add(label2);
		panel.add(orderDateTextField);
		panel.add(label3);
		panel.add(comboBoxList);
		panel.add(label4);
		panel.add(quantityTextField);
		panel.add(label5);
		panel.add(expectedDeliveryDateTextField);	
		//hidden - moving to edit order gui
		/*
		panel.add(label6);
		panel.add(dateReceivedTextField);
		panel.add(label7);
		panel.add(receivedCheckBox);
		*/
		panel.add(submitButton);
		
		panel.add(returnToMainMenu);
		
		submitButton.addActionListener(this);
		
		returnToMainMenu.addActionListener(this);
		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == submitButton) {
			
			boolean duplicateOrderID = false;
			boolean dataOK = true;
			
			newOrderId = idTextField.getText();
			
			String productID = comboBoxList.getSelectedItem().toString();
			for(Product product: RetailSystem.getInstance().getProducts()){
				if(productID.contains(product.getProductID())){
					newProduct = product;
				}
			}
			
			if(newProduct == null ) {
				JOptionPane.showMessageDialog(this, "Please choose a product");
				dataOK = false;
			}
			
			for(Order order: RetailSystem.getInstance().getOrders()){
				if(order.getOrderID().equalsIgnoreCase(newOrderId)){
					duplicateOrderID = true;
				}
			}
			if(duplicateOrderID == true){
				JOptionPane.showMessageDialog(this, "Order in system with same ID");
			}
			
			try {
				newQuantity = Integer.parseInt(quantityTextField.getText());
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Order quantity must be an Integer");
				dataOK = false;
			}
			
			try {
				newOrderDate = DateFormat.getDateInstance().parse(orderDateTextField.getText());
				newExpectedDeliveryDate = DateFormat.getDateInstance().parse(expectedDeliveryDateTextField.getText());
				//hidden - moved to edit order gui
				//newReceivedDate = DateFormat.getDateInstance().parse(dateReceivedTextField.getText());
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Date format must be in the form: dd-MMM-yyyy");
				dataOK = false;
			}
			
			if(newExpectedDeliveryDate.before(newOrderDate)) {
				JOptionPane.showMessageDialog(this, "Expected delivery date can not be before Order date");
				dataOK = false;
			}
			
			//hidden - moved to edit order gui
			//newReceived = receivedCheckBox.isSelected();
			
			if ((!duplicateOrderID) && (dataOK==true)){
				try{
					JOptionPane.showMessageDialog(this, "Order Added");
					
					newReceivedDate = DateFormat.getDateInstance().parse(orderDateTextField.getText());
					newReceived = false;
					
					Order newOrder = new Order(newOrderId, newOrderDate, newProduct, newQuantity, newExpectedDeliveryDate,
							newReceivedDate, newReceived);
					RetailSystem.getInstance().getOrders().add(newOrder);
					
					saveOrder();
					
					int answer = JOptionPane.showConfirmDialog(this, "Would you like to create another Order?", "Continue", JOptionPane.YES_NO_OPTION);
					
					if (answer == JOptionPane.NO_OPTION) {
						
						OrderGUI orderGUI = new OrderGUI();
						this.setVisible(false);
						this.dispose();
						
				    }
					
					
				}catch(NumberFormatException | ParseException e){
					JOptionPane.showMessageDialog(this, "Error processing request");
				}
			}
		}
		
		if(target == returnToMainMenu) {
			try {
				OrderGUI returnToMainMenu = new OrderGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach OrderGUI");
			}
		}
		
	}
	
	public static void saveOrder(){
	  	 try {
	  		 FileWriter orderFile;
	  		orderFile = new FileWriter("orders.txt");
	  		
	  		 DataBase.writeOrders(RetailSystem.getInstance().getOrders(), orderFile);
	  		orderFile.close();
	  		
	  	 } catch (Exception exception) {
	  		 
	  		 exception.printStackTrace();
	  	 }
	   }
}