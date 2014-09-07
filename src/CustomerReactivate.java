import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CustomerReactivate extends JPanel {
	private JLabel title;
	private JLabel customerLabel;
	private JComboBox<String> customerDropDown = new JComboBox<String>();
	private JButton buttonReactivate;
	private User pickedCustomer;
	// fixing the layout
	JLabel labelEmpty = new JLabel(" ");
	JLabel labelEmpty1 = new JLabel(" ");
	JLabel labelEmpty2 = new JLabel(" ");
	JLabel labelEmpty3 = new JLabel(" ");
	JLabel labelEmpty4 = new JLabel(" ");

	public CustomerReactivate() {
		this.setLayout(new GridLayout(0, 1));
		title = new JLabel("Reactivate Customer");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		customerLabel = new JLabel("Please choose a customer from the list below");
		buttonReactivate = new JButton("Reactivate Customer");
		Customer.customerListCompleteInactive(customerDropDown);

		this.add(title);
		this.add(labelEmpty);
		this.add(customerLabel);
		this.add(customerDropDown);
		this.add(buttonReactivate);
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);

		buttonReactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(customerDropDown.getItemCount()>0){
					System.out.println(customerDropDown.getComponentCount());
					reactivate();
				}
			}
		});
	}

	public void reactivate(){
		String id = RetailSystem.returnIDfromCombobox(customerDropDown.getSelectedItem().toString());
		Customer customer = Customer.retrieveCustomer(id);
		if(customer != null){
			customerReactivate(customer);
			reloadDropDown();
		}
	}
	public void reloadDropDown() {
		customerDropDown.removeAllItems();
		Customer.customerListCompleteInactive(customerDropDown);
		customerDropDown.revalidate();
		customerDropDown.repaint();
	}
	//reactivate customer
	public void customerReactivate(Customer customer) {
		customer.setActive(true);
		Customer.saveCustomer();
		JOptionPane.showMessageDialog(null, "Customer has been reactivated");
	}

}
