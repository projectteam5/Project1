import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RemoveOrderGUI extends JFrame implements ActionListener {
	private JPanel panel;
	private Container container;
	private JComboBox<String> orderList;
	private JButton removeOrderButton;
	private JLabel printLabel;
	
	private JButton returnToMainMenu;
	
	public RemoveOrderGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 200);
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setLayout(new GridLayout(0,1));	
		
		orderList = new JComboBox<String>();
		removeOrderButton = new JButton("Remove Order");
		printLabel = new JLabel();
		
		returnToMainMenu = new JButton("Main Menu");
		
		for(Order order: RetailSystem.getInstance().getOrders()){
			orderList.addItem(order.getOrderID());
		}
		
		panel.add(orderList);
		panel.add(removeOrderButton);
		panel.add(printLabel);
		
		panel.add(returnToMainMenu);
		
		removeOrderButton.addActionListener(this);
		
		returnToMainMenu.addActionListener(this);

		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == removeOrderButton) {
			
			String orderID = orderList.getSelectedItem().toString();
			boolean orderFound = false;
			
			for(Order order: RetailSystem.getInstance().getOrders()){
				if(orderID.equalsIgnoreCase(order.getOrderID())){
					
					orderFound = true;
					
					printLabel.setText(order.getOrderID()
							+" | "+DateFormat.getDateInstance().format(order.getOrderDate())
							+" | "+order.getProduct().getProductID()
							+" | "+order.getQuantity()
							+" | "+DateFormat.getDateInstance().format(order.getExpectedDeliveryDate())
							+" | "+DateFormat.getDateInstance().format(order.getDateReceived())
							+" | "+order.isReceived());
					
					int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Remove Order", JOptionPane.YES_NO_OPTION);
					
					if (answer == JOptionPane.YES_OPTION) {
						RetailSystem.getInstance().getOrders().remove(order);
						
						saveOrder();
						
						JOptionPane.showMessageDialog(this, "Order "+order.getOrderID()+" has been removed from the system");
						printLabel.setText("");
				    }
					
					break;
				}
			}
			if(!orderFound){
				
				JOptionPane.showMessageDialog(this, "No Order With This ID in System!");
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