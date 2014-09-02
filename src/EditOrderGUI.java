import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class EditOrderGUI extends JPanel implements ActionListener {

	private JButton editOrderButton;
	private JLabel labelTitleMain;
	private static JComboBox<String> orderList;

	private static Order orderToEdit;

	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);

	public EditOrderGUI() {

		this.setLayout(new GridLayout(0, 1));

		labelTitleMain = new JLabel("Edit an Order");
		labelTitleMain.setFont(new Font("Arial", Font.BOLD, 20));

		orderList = new JComboBox<String>();
		editOrderButton = new JButton("Edit Order");

		Order.getOrdersCheck(orderList);
		
		this.add(labelTitleMain);
		this.add(orderList);
		this.add(editOrderButton);

		editOrderButton.addActionListener(this);

		setVisible(true);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
	}

	public void actionPerformed(ActionEvent event) {

		Object target = event.getSource();

		if (target == editOrderButton) {

			boolean orderFound = false;

			if (orderList.getSelectedIndex() < 0) {

				orderFound = false;

			} else {

				String orderID = orderList.getSelectedItem().toString();

				for (Order order : RetailSystem.getInstance().getOrders()) {

					if (orderID.equalsIgnoreCase(order.getOrderID())) {

						orderFound = true;

						orderToEdit = order;

						colorButton();
						editOrderButton.setBackground(colorButtonSelected);
						MenuGUI.getInstance().setPanelAction( new OrderEditorGUI());

						break;
					}
				}
			}
			if (!orderFound) {

				JOptionPane.showMessageDialog(this, "No Order With This ID in System!");
			}
		}

	}

	public void colorButton() {

		editOrderButton.setBackground(colorButtons);
		editOrderButton.setFont(fontButtons);

	}

	public static Order getOrderToEdit() {
		return orderToEdit;
	}

	@SuppressWarnings("static-access")
	public void setOrderToEdit(Order orderToEdit) {
		this.orderToEdit = orderToEdit;
	}

	public static JComboBox<String> getOrderList() {
		return orderList;
	}

	@SuppressWarnings("static-access")
	public void setOrderList(JComboBox<String> orderList) {
		this.orderList = orderList;
	}
}