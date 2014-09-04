import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OrderEditorGUI extends JPanel implements ActionListener {
	private JTextField idTextField;
	private JTextField orderDateTextField;
	private JComboBox<String> comboBoxList;
	private JTextField quantityTextField;
	private JTextField expectedDeliveryDateTextField;
	private JTextField dateReceivedTextField;
	private JCheckBox receivedCheckBox;
	private JButton submitButton;
	
	private String productName;
	
	private String newOrderId;
	private Date newOrderDate;
	private Product newProduct;
	private int newQuantity;
	private Date newExpectedDeliveryDate;
	private boolean newReceived;
	private Order order;
	private JLabel labelTitleMain;
	private JLabel labelOrderID;
	private JLabel labelOrderDate;
	private JLabel labelProductName;
	private JLabel labelProductQuantity;
	private JLabel labelExpectedDeliveryDate;
	private JLabel labelOrderReceived;

	public OrderEditorGUI() {
		
		this.setLayout(new GridLayout(0, 1));
		
		order = EditOrderGUI.getOrderToEdit();
		
		labelTitleMain = new JLabel("Order Editor");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		
		labelOrderID = new JLabel("OrderID");
		idTextField = new JTextField(order.getOrderID());
		idTextField.setEditable(false);
		
		labelOrderDate = new JLabel("Order Date");
		orderDateTextField = new JTextField();
		orderDateTextField.setText(DateFormat.getDateInstance().format(order.getOrderDate()));
		
		labelProductName = new JLabel("Product Name");
		comboBoxList = new JComboBox<String>();
		comboBoxList.addItem(order.getProduct().getName());
		for(Product p : RetailSystem.getInstance().getProducts()) {
			comboBoxList.addItem(p.getName());
		}
		
		labelProductQuantity = new JLabel("Product Quantity");
		quantityTextField = new JTextField(String.valueOf(order.getQuantity()));
		
		labelExpectedDeliveryDate = new JLabel("Expected Delivery Date");
		expectedDeliveryDateTextField = new JTextField();
		expectedDeliveryDateTextField.setText(DateFormat.getDateInstance().format(order.getExpectedDeliveryDate()));
		
		new JLabel("Actual Delivery Date");
		dateReceivedTextField = new JTextField();
		dateReceivedTextField.setText(DateFormat.getDateInstance().format(order.getDateReceived()));
		
		labelOrderReceived = new JLabel("Order Recieved?");
		receivedCheckBox = new JCheckBox("",order.isReceived());
		receivedCheckBox = new JCheckBox();
		receivedCheckBox.setSelected(order.isReceived());
		
		submitButton = new JButton("Submit");
		
		this.add(labelTitleMain);
		this.add(labelOrderID);
		this.add(idTextField);
		this.add(labelOrderDate);
		this.add(orderDateTextField);
		this.add(labelProductName);
		this.add(comboBoxList);
		this.add(labelProductQuantity);
		this.add(quantityTextField);
		this.add(labelExpectedDeliveryDate);
		this.add(expectedDeliveryDateTextField);
		this.add(labelOrderReceived);
		this.add(receivedCheckBox);
		this.add(submitButton);
		
		idTextField.setEditable(false);
		orderDateTextField.setEditable(false);
		comboBoxList.setEnabled(false);
		quantityTextField.setEditable(false);
		
		submitButton.addActionListener(this);
		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		
		Object target = event.getSource();
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		
		df.setLenient(true);
		
		boolean dataOK = true;
		
		if(target == submitButton) {
			
			newOrderId = null;
			
			newOrderDate = null;
			
			newProduct = null;
			
			newQuantity = 0;
			
			newExpectedDeliveryDate = null;
			
			newReceived = false;
			
			newOrderId = idTextField.getText();
			
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
			
			newReceived = receivedCheckBox.isSelected();
			
			if(dataOK){
				
				for(Product product: RetailSystem.getInstance().getProducts()){
					
					if(productName.contains(product.getName())){
						
						newProduct = product;
						
					}
					
				}
				
				order.setOrderID(newOrderId);
				order.setOrderDate(newOrderDate);
				order.setProduct(newProduct);
				order.setQuantity(newQuantity);
				order.setExpectedDeliveryDate(newExpectedDeliveryDate);
				
				if(!newReceived) {
					
					order.setDateReceived(newExpectedDeliveryDate);
					
				} else {
					
					order.setDateReceived(new Date());
					
				}
				
				order.setReceived(newReceived);
				
				if(receivedCheckBox.isSelected()) {
					Stock.increaseStock(newProduct, newQuantity);
					Stock.saveStock();
				}
				
				Order.saveOrder();
				
				JOptionPane.showMessageDialog(this, "Order has been edited", "Success", JOptionPane.INFORMATION_MESSAGE);
				
				//removing the change of isRecieved from EditOrder orderList
				if(receivedCheckBox.isSelected()) {
					
					EditOrderGUI.getOrderList().removeItem(order.getOrderID());
					
				}
				
				idTextField.setEditable(false);
				orderDateTextField.setEditable(false);
				comboBoxList.setEnabled(false);
				quantityTextField.setEditable(false);
				expectedDeliveryDateTextField.setEditable(false);
				dateReceivedTextField.setEditable(false);
				receivedCheckBox.setEnabled(false);
				submitButton.setEnabled(false);
				
			}
		}
	}
	
}