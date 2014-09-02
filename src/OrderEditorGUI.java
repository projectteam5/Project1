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
	private JLabel labelProductID;
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
		
		labelProductID = new JLabel("Product ID");
		comboBoxList = new JComboBox<String>();
		comboBoxList.addItem(order.getProduct().getProductID()+" - "+order.getProduct().getName());
		for(Product p : RetailSystem.getInstance().getProducts()) {
			comboBoxList.addItem(p.getProductID() + " - " + p.getName());
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
		this.add(labelProductID);
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
		
		if(target == submitButton) {
			newOrderId = null;
			newOrderDate = null;
			newProduct = null;
			newQuantity = 0;
			newExpectedDeliveryDate = null;
			newReceived = false;
			
			boolean dataOK = true;
			
			newOrderId = idTextField.getText();
			
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
				
				JOptionPane.showMessageDialog(this, "Expected delivery date must be after order date", "Attention", JOptionPane.INFORMATION_MESSAGE);
				dataOK = false;
				
			}
			
			try {
				newQuantity = Integer.parseInt(quantityTextField.getText());
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Order quantity must be an Integer", "Attention", JOptionPane.INFORMATION_MESSAGE);
				dataOK = false;
			}
			
			String productID = comboBoxList.getSelectedItem().toString();
			for(Product product: RetailSystem.getInstance().getProducts()){
				if(productID.contains(product.getProductID())){
					newProduct = product;
				}
			}
			
			newReceived = receivedCheckBox.isSelected();
			
			if(dataOK){
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
	
	public static Date addDaysToDate(int period) {
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(new Date());
		
		calendar.add(Calendar.DATE, period);
		
		Date newDate = calendar.getTime();
		
		return newDate;
	}
	
}