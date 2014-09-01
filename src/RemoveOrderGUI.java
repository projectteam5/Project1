import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
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
	private JLabel printLabel;
	private JLabel labelTitleMain;

	public RemoveOrderGUI() {

		this.setLayout(new GridLayout(0, 1));

		labelTitleMain = new JLabel("Remove an Order");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));

		orderList = new JComboBox<String>();
		removeOrderButton = new JButton("Remove Order");
		printLabel = new JLabel();

		Order.getOrdersCheck(orderList);
		/*
		 * for(Order order: RetailSystem.getInstance().getOrders()){
		 * if(order.isActive()==true) { if(order.isReceived()==false) {
		 * orderList.addItem(order.getOrderID()); } } }
		 */
		this.add(labelTitleMain);
		this.add(orderList);
		this.add(removeOrderButton);
		this.add(printLabel);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);

		removeOrderButton.addActionListener(this);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {

		Object target = event.getSource();

		printLabel.setText("");

		if (target == removeOrderButton) {

			boolean orderFound = false;

			if (orderList.getSelectedIndex() < 0) {

				orderFound = false;

			} else {

				String orderID = orderList.getSelectedItem().toString();

				for (Order order : RetailSystem.getInstance().getOrders()) {

					if (orderID.equalsIgnoreCase(order.getOrderID())) {

						orderFound = true;

						printLabel.setText("<html><table>" + "<tr>" + "<td>"
								+ order.getOrderID()
								+ "</td>"
								+ "<td>"
								+ DateFormat.getDateInstance().format(
										order.getOrderDate())
								+ "</td>"
								+ "<td>"
								+ order.getProduct().getName()
								+ "</td>"
								+ "<td>"
								+ order.getQuantity()
								+ "</td>"
								+ "<td>"
								+ DateFormat.getDateInstance().format(
										order.getExpectedDeliveryDate())
								+ "</td>"
								+ "<td>"
								+ DateFormat.getDateInstance().format(
										order.getDateReceived()) + "</td>"
								+ "<td>" + order.isReceived() + "</td>"
								+ "</tr>" + "</table></html>");

						int answer = JOptionPane.showConfirmDialog(this,
								"Are you sure?", "Remove Order",
								JOptionPane.YES_NO_OPTION);

						if (answer == JOptionPane.YES_OPTION) {
							order.setActive(false);
							// RetailSystem.getInstance().getOrders().remove(order);

							Order.saveOrder();

							JOptionPane.showMessageDialog(this, "Order "
									+ order.getOrderID()
									+ " has been set as inactive");

							// remove(printLabel);

							orderList.removeItem(orderID);

							printLabel.setText("");

						}

						if (answer == JOptionPane.NO_OPTION) {

							printLabel.setText("");

						}

						repaint();

						revalidate();

						break;
					}
				}
			}
			if (!orderFound) {

				JOptionPane.showMessageDialog(this,
						"No Order With This ID in System!");
			}
		}
	}
}