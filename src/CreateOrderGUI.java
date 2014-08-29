import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateOrderGUI extends JPanel implements ActionListener {
	
	private JTextField orderDateTextField;
	private JTextField quantityTextField;
	private JTextField expectedDeliveryDateTextField;
	private JComboBox<String> comboBoxList;
	private JLabel labelTitle;
	private JLabel labelOrderDate;
	private JLabel labelProductID;
	private JLabel labelProductQuantity;
	private JLabel labelExpectedDeliveryDate;
	private JButton submitButton;
	
	private Date newOrderDate;
	private Product newProduct;
	private int newQuantity;
	private Date newExpectedDeliveryDate;
	
	private Date newReceivedDate;
	private boolean newReceived;
	
	public CreateOrderGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		labelTitle = new JLabel("Create an Order");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		new JLabel("OrderID");
		new JTextField();
		
		labelOrderDate = new JLabel("Order Date");
		orderDateTextField = new JTextField();
		orderDateTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
		labelProductID = new JLabel("Product ID");
		comboBoxList = new JComboBox<String>();
		comboBoxList.addItem("");
		for(Product p : RetailSystem.getInstance().getProducts()) {
			if(p.isActive()==true) {
				comboBoxList.addItem(p.getProductID() + " - " + p.getName());
			}
		}
		
		labelProductQuantity = new JLabel("Product Quantity");
		quantityTextField = new JTextField();
		
		labelExpectedDeliveryDate = new JLabel("Expected Delivery Date");
		expectedDeliveryDateTextField = new JTextField();
		expectedDeliveryDateTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
		submitButton = new JButton("Submit");
		
		this.add(labelTitle);
		this.add(labelOrderDate);
		this.add(orderDateTextField);
		this.add(labelProductID);
		this.add(comboBoxList);
		this.add(labelProductQuantity);
		this.add(quantityTextField);
		this.add(labelExpectedDeliveryDate);
		this.add(expectedDeliveryDateTextField);
		this.add(submitButton);
		
		submitButton.addActionListener(this);
		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == submitButton) {
			
			boolean duplicateOrderID = false;
			boolean dataOK = true;
			
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
			
			try {
				newQuantity = Integer.parseInt(quantityTextField.getText());
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Order quantity must be an Integer");
				dataOK = false;
			}
			
			try {
				newOrderDate = DateFormat.getDateInstance().parse(orderDateTextField.getText());
				newExpectedDeliveryDate = DateFormat.getDateInstance().parse(expectedDeliveryDateTextField.getText());
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Date format must be in the form: dd-MMM-yyyy");
				dataOK = false;
			}
			
			if(newExpectedDeliveryDate.before(newOrderDate)) {
				JOptionPane.showMessageDialog(this, "Expected delivery date can not be before Order date");
				dataOK = false;
			}
			
			if( Order.checkForOrders(newProduct) ) {
				
				JOptionPane.showMessageDialog(this, "Can not order this Product again");
				
				dataOK = false;
				
			}
			
			if ((!duplicateOrderID) && (dataOK==true)){
				try{
					JOptionPane.showMessageDialog(this, "Order Added");
					
					newReceivedDate = DateFormat.getDateInstance().parse(orderDateTextField.getText());
					newReceived = false;
					
					Order newOrder = new Order(newOrderDate, newProduct, newQuantity, newExpectedDeliveryDate,
							newReceivedDate, newReceived);
					RetailSystem.getInstance().getOrders().add(newOrder);
					
					saveOrder();
					
					orderDateTextField.setEditable(false);
					quantityTextField.setEditable(false);
					expectedDeliveryDateTextField.setEditable(false);
					comboBoxList.setEnabled(false);
					submitButton.setEnabled(false);
					
					
				} catch(NumberFormatException | ParseException e) {
					JOptionPane.showMessageDialog(this, "Error processing request");
				}
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