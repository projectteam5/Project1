import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	private String productName;
	
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
		new GregorianCalendar();
		
		
		labelOrderDate = new JLabel("Order Date");
		orderDateTextField = new JTextField();
		orderDateTextField.setText(DateFormat.getDateInstance().format(new Date()));
		
		labelProductID = new JLabel("Product Name");
		comboBoxList = new JComboBox<String>();
		for(Product p : RetailSystem.getInstance().getProducts()) {
			if(p.getSupplier().isActive()) {
				comboBoxList.addItem(p.getName());
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
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		
		df.setLenient(true);
		
		boolean dataOK = true;
		
		if(target == submitButton) {
			
			try {
				
				newOrderDate = df.parse(orderDateTextField.getText());
			
			} catch (ParseException e) {
				
				dataOK = false;
				
				//e.printStackTrace();
				
				JOptionPane.showMessageDialog(this, "Order date could not be parsed", "Error", JOptionPane.ERROR_MESSAGE);
			
			}
			
			try {
				
				productName = comboBoxList.getSelectedItem().toString();
				
			} catch( NullPointerException e) {
				
				dataOK = false;
				
				//e.printStackTrace();
				
				JOptionPane.showMessageDialog(this, "The product ComboBox is empty", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
			try {
				
				Order.checkForOrders(productName);
				
			} catch( NullPointerException e) {
				
				dataOK = false;
				
				//e.printStackTrace();
				
				JOptionPane.showMessageDialog(this, "No product selected", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
			try {
				
				newQuantity = Integer.parseInt(quantityTextField.getText());
				
			} catch(NumberFormatException e) {
				
				dataOK = false;
				
				//e.printStackTrace();
				
				JOptionPane.showMessageDialog(this, "Order quantity could not be parsed", "Error", JOptionPane.ERROR_MESSAGE);
			
			}
			
			try {
				
				newExpectedDeliveryDate = df.parse(expectedDeliveryDateTextField.getText());
			
			} catch (ParseException e) {
				
				dataOK = false;
				
				//e.printStackTrace();
				
				JOptionPane.showMessageDialog(this, "Expected delivery date could not be parsed", "Error", JOptionPane.ERROR_MESSAGE);
			
			}
			
			if( orderDateTextField.getText().trim().length() != df.toPattern().length() ) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "Date pattern is not correct length", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
			if( !Order.validateDatePattern(orderDateTextField.getText()) ) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "Date does not match specified pattern: 'dd-MMM-yyyy'", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
			if( !Order.checkForOrders(productName) ) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "An order for this product has been made "
						+ "and as yet has not been received", "Attention", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			if(quantityTextField.getText()==null) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "Order must have a quantity", "Attention", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			if(!(newQuantity > 0)) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "Order quantity must be greater than 0", "Attention", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			if( expectedDeliveryDateTextField.getText().trim().length() != df.toPattern().length() ) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "Date pattern is not correct length", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
			if( !Order.validateDatePattern(expectedDeliveryDateTextField.getText()) ) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "Date does not match specified pattern: 'dd-MMM-yyyy'", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
			if( newExpectedDeliveryDate.before(newOrderDate) ) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "Expected delivery date should not be before order date", "Attention", JOptionPane.INFORMATION_MESSAGE);
			
			}
			
			if( newExpectedDeliveryDate.before(Order.addDaysToDate(newOrderDate, 5)) ) {
				
				dataOK = false;
				
				JOptionPane.showMessageDialog(this, "Expected delivery date should be at least 5 days after order date", "Attention", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			if ( dataOK==true ) {
				try{
					
					for(Product product: RetailSystem.getInstance().getProducts()){
						
						if(productName.contains(product.getName())){
							
							newProduct = product;
							
						}
						
					}
					
					newReceivedDate = newExpectedDeliveryDate;
					
					newReceived = false;
					
					Order newOrder = new Order(newOrderDate, newProduct, newQuantity, newExpectedDeliveryDate,newReceivedDate, newReceived);
					
					RetailSystem.getInstance().getOrders().add(newOrder);
					
					Order.saveOrder();
					
					JOptionPane.showMessageDialog(this, "Order has been created", "Success", JOptionPane.INFORMATION_MESSAGE);
					
					orderDateTextField.setEditable(false);
					comboBoxList.setEnabled(false);
					quantityTextField.setEditable(false);
					expectedDeliveryDateTextField.setEditable(false);
					submitButton.setEnabled(false);
					
					
				} catch(NumberFormatException | ParseException | NullPointerException e) {
					
					dataOK = false;
					
					//e.printStackTrace();
					
					JOptionPane.showMessageDialog( this, "Error processing order", "Error", JOptionPane.ERROR_MESSAGE );
					
				}
			}
		}	
	}
	
}