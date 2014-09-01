import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ReactivateSupplierGUI extends JPanel {
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JButton reactivateButton;
	private JLabel title;
	private JLabel instruction;
	private Supplier supplierToReactivate = null;

	public ReactivateSupplierGUI() {
		title = new JLabel("Reactivate Supplier");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		reactivateButton = new JButton("Reactivate");
		instruction = new JLabel(
				"Please choose a supplier to reactivate from drop down menu");
		compileSupplierNames();

		this.add(title);
		this.add(instruction);
		this.add(supplierDropDown);
		this.add(reactivateButton);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0, 1));
		this.setVisible(true);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);

		reactivateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {
				if (supplierDropDown.getItemCount() > 0) {
					String name = supplierDropDown.getSelectedItem().toString();
					// Getting the supplier from the list and assigning to new
					// object then
					// Using another object to access supplier class method to
					// delete from list
					Supplier supplierToReactivate = findAndReturnSupplierFromList(name);
					supplierToReactivate.setActive(true);
					JOptionPane.showMessageDialog(null,
							"Supplier is now active", "Success",
							JOptionPane.PLAIN_MESSAGE);
					supplierToReactivate.saveSupplier();
					refresh();
				} else
					JOptionPane.showMessageDialog(null,
							"There are no suppliers to reactivate", "Success",
							JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	// Takes the name selected from drop-down menu, finds it on the list
	// And returns the supplier
	private Supplier findAndReturnSupplierFromList(String name) {
		Supplier supplierToReactivate = null;
		for (Supplier supplier : RetailSystem.getInstance().getSuppliers()) {
			if (name.equalsIgnoreCase(supplier.getName())) {
				supplierToReactivate = supplier;
				break;
			}
		}
		return supplierToReactivate;
	}

	// Used to fill up the combo box with suppliers from the list
	public void compileSupplierNames() {
		for (Supplier supplier : RetailSystem.getInstance().getSuppliers()) {
			if (supplier.isActive() == false) {
				supplierDropDown.addItem(supplier.getName());
			}
		}
	}

	public void refresh() {
		supplierDropDown.removeAllItems();
		compileSupplierNames();
		supplierDropDown.revalidate();
		supplierDropDown.repaint();
	}

}
