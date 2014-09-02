import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
		for(Product p : RetailSystem.getInstance().getProducts()) {
			if(p.getSupplier().isActive()) {
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
				JOptionPane.showMessageDialog(this, "Please choose a product", "Attention", JOptionPane.INFORMATION_MESSAGE);
				dataOK = false;
			}
			
			try {
				
				newQuantity = Integer.parseInt(quantityTextField.getText());
				
				if(quantityTextField.getText()==null) {
					
					JOptionPane.showMessageDialog(this, "Order must have a quantity", "Attention", JOptionPane.INFORMATION_MESSAGE);
					
					dataOK = false;
					
				}
				
				if(!(newQuantity > 0)) {
					
					JOptionPane.showMessageDialog(this, "Order quantity must be an more than 0", "Attention", JOptionPane.INFORMATION_MESSAGE);
					
					dataOK = false;
				}
				
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Order quantity must be an Integer", "Error", JOptionPane.ERROR_MESSAGE);
				dataOK = false;
			}
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			
			if(orderDateTextField.getText()==null) {
				
				dataOK = false;
				
			}
			
			if( orderDateTextField.getText().trim().length() != df.toPattern().length() ) {
				
				JOptionPane.showMessageDialog(this, "Date format must be in the form: dd-MMM-yyyy", "Error", JOptionPane.ERROR_MESSAGE);
				
				dataOK = false;
				
			}
			
			df.setLenient(false);
			
			try {
				
				newOrderDate = DateFormat.getDateInstance().parse(orderDateTextField.getText());
				
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
				dataOK = false;
			}
			
			if(expectedDeliveryDateTextField.getText()==null) {
				
				dataOK = false;
				
			}
			
			if( expectedDeliveryDateTextField.getText().trim().length() != df.toPattern().length() ) {
				
				JOptionPane.showMessageDialog(this, "Date format must be in the form: dd-MMM-yyyy", "Error", JOptionPane.ERROR_MESSAGE);
				
				dataOK = false;
				
			}
			
			df.setLenient(false);
			
			try {
				
				newExpectedDeliveryDate = DateFormat.getDateInstance().parse(expectedDeliveryDateTextField.getText());
				
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
				dataOK = false;
			}
			
			if(newExpectedDeliveryDate.before(newOrderDate)) {
				JOptionPane.showMessageDialog(this, "Expected delivery date can not be before Order date", "Attention", JOptionPane.INFORMATION_MESSAGE);
				dataOK = false;
			}
			
			if(newExpectedDeliveryDate.before(addDaysToDate(newOrderDate, 3))) {
				
				JOptionPane.showMessageDialog(this, "Expected delivery date should be at least 3 days after order date", "Attention", JOptionPane.INFORMATION_MESSAGE);
				dataOK = false;
				
			}
			
			// check if an open (!isReceived) Order is for this Product already - if so, we cannot Order
			/*
			if( !Order.checkForOrders(newProduct) ) {
				
				JOptionPane.showMessageDialog( this, "Product can not be ordered" );
				
				dataOK = false;
				
			}
			*/
			if ((!duplicateOrderID) && (dataOK==true)){
				try{
					
					newReceivedDate = DateFormat.getDateInstance().parse(expectedDeliveryDateTextField.getText());
					newReceived = false;
					
					Order newOrder = new Order(newOrderDate, newProduct, newQuantity, newExpectedDeliveryDate,newReceivedDate, newReceived);
					
					RetailSystem.getInstance().getOrders().add(newOrder);
					
					Order.saveOrder();
					
					JOptionPane.showMessageDialog(this, "Order has been created", "Success", JOptionPane.INFORMATION_MESSAGE);
					
					orderDateTextField.setEditable(false);
					quantityTextField.setEditable(false);
					expectedDeliveryDateTextField.setEditable(false);
					comboBoxList.setEnabled(false);
					submitButton.setEnabled(false);
					
					
				} catch(NumberFormatException | ParseException e) {
					
					JOptionPane.showMessageDialog( this, "Error processing request", "Error", JOptionPane.ERROR_MESSAGE );
					
				}
			}
		}	
	}
	
	public static Date addDaysToDate(Date date, int days) {
		
		Date newDate = new Date();
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(date);
		
		calendar.add(Calendar.DATE, days);
		
		newDate = calendar.getTime();
		
		return newDate;
		
	}
	
}