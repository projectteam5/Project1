import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RemoveOrderGUI extends JPanel implements ActionListener {

	private JComboBox<String> orderList;
	private JButton removeOrderButton;
	private JLabel labelTitleMain;
	private JLabel infoLabel;
	
	// fixing the layout
	JLabel labelEmpty = new JLabel(" ");
	JLabel labelEmpty1 = new JLabel(" ");
	JLabel labelEmpty2 = new JLabel(" ");
	JLabel labelEmpty3 = new JLabel(" ");

	public RemoveOrderGUI() {

		this.setLayout(new GridLayout(0, 1));

		labelTitleMain = new JLabel("Remove an Order");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));
		infoLabel = new JLabel("Please choose an order from the list below");

		orderList = new JComboBox<String>();
		removeOrderButton = new JButton("Remove Order");

		Order.getOrdersCheck(orderList);
		
		this.add(labelTitleMain);
		this.add(infoLabel);
		this.add(orderList);
		this.add(removeOrderButton);
		
		// fixing the layout
		
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);

		removeOrderButton.addActionListener(this);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {

		Object target = event.getSource();

		if (target == removeOrderButton) {

			boolean orderFound = false;

			if (orderList.getSelectedIndex() < 0) {

				orderFound = false;

			} else {

				String orderID = orderList.getSelectedItem().toString();

				for (Order order : RetailSystem.getInstance().getOrders()) {

					if (orderID.equalsIgnoreCase(order.getOrderID())) {

						orderFound = true;

						int answer = JOptionPane.showConfirmDialog(this,
								"Are you sure?", "Remove Order",
								JOptionPane.YES_NO_OPTION);

						if (answer == JOptionPane.YES_OPTION) {
							
							order.setActive(false);

							Order.saveOrder();

							JOptionPane.showMessageDialog(this, "Order "
									+ order.getOrderID()
									+ " from "
									+ DateFormat.getInstance().format(order.getOrderDate())
									+ " has been removed from the system", "Attention", JOptionPane.INFORMATION_MESSAGE);

							orderList.removeItem(orderID);
							
							revalidate();

							repaint();
							
							break;

						}

						if (answer == JOptionPane.NO_OPTION) {

						}
						
						revalidate();

						repaint();

						break;
					}
				}
			}
			if (!orderFound) {

				JOptionPane.showMessageDialog(this,
						"No Order With This ID in System!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}