import java.awt.Font;
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
	private Date newReceivedDate;
	private boolean newReceived;
	private Order order;
	private JLabel labelTitleMain;
	private JLabel labelOrderID;
	private JLabel labelOrderDate;
	private JLabel labelProductID;
	private JLabel labelProductQuantity;
	private JLabel labelExpectedDeliveryDate;
	private JLabel labelActualDeliveryDate;
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
		comboBoxList.addItem(order.getProduct().getProductID());
		for(Product p : RetailSystem.getInstance().getProducts()) {
			comboBoxList.addItem(p.getProductID() + " - " + p.getName());
		}
		
		labelProductQuantity = new JLabel("Product Quantity");
		quantityTextField = new JTextField(String.valueOf(order.getQuantity()));
		
		labelExpectedDeliveryDate = new JLabel("Expected Delivery Date");
		expectedDeliveryDateTextField = new JTextField();
		expectedDeliveryDateTextField.setText(DateFormat.getDateInstance().format(order.getExpectedDeliveryDate()));
		
		labelActualDeliveryDate = new JLabel("Actual Delivery Date");
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
		this.add(labelActualDeliveryDate);
		this.add(dateReceivedTextField);
		this.add(labelOrderReceived);
		this.add(receivedCheckBox);
		this.add(submitButton);
		
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
			newReceivedDate = null;
			newReceived = false;
			
			boolean dataOK = true;
			
			newOrderId = idTextField.getText();
			
			try {
				newOrderDate = DateFormat.getDateInstance().parse(orderDateTextField.getText());
				newExpectedDeliveryDate = DateFormat.getDateInstance().parse(expectedDeliveryDateTextField.getText());
				newReceivedDate = DateFormat.getDateInstance().parse(dateReceivedTextField.getText());
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Date format must be in the form: dd-MMM-yyyy");
				dataOK = false;
			}
			
			try {
				newQuantity = Integer.parseInt(quantityTextField.getText());
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Order quantity must be an Integer");
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
				order.setDateReceived(newReceivedDate);
				order.setReceived(newReceived);
				
				if(receivedCheckBox.isSelected()) {
					for(Stock stock : RetailSystem.getInstance().getStocks()) {
						if(stock.getProduct().getProductID().equals(newProduct.getProductID())) {
							stock.setUnits(stock.getUnits()+newQuantity);
							//order.setActive(false);
							
							saveStock();
						}
					}
				}
				
				saveOrder();
				
				JOptionPane.showMessageDialog(this, "Order has been edited");
				
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
	
	public static void saveStock(){
	  	 try {
	  		 FileWriter stockFile;
	  		stockFile = new FileWriter("stocks.txt");
	  		
	  		 DataBase.writeStocks(RetailSystem.getInstance().getStocks(), stockFile);
	  		stockFile.close();
	  		
	  	 } catch (Exception exception) {
	  		 
	  		 exception.printStackTrace();
	  	 }
	   }
	
	
}