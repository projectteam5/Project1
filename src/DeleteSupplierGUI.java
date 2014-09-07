import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.Refreshable;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DeleteSupplierGUI extends JPanel {
	private JComboBox<String> supplierDropDown = new JComboBox<String>();
	private JButton deleteButton;
	private JLabel title;
	private JLabel instruction;
	// Instance of supplier in aid of accessing removal method
	private Supplier supplierToRemove;
	

	public DeleteSupplierGUI() {

		title = new JLabel("Deactivate Supplier");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		instruction = new JLabel("Please choose a supplier from the list below");
		deleteButton = new JButton("Deactivate Supplier");
		compileSupplierNames();

		this.add(title);
		this.add(instruction);
		this.add(supplierDropDown);
		this.add(deleteButton);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0, 1));
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {
				supplierToRemove = null;
				if (supplierDropDown.getItemCount() != 0) {
					deactivateSupplier(supplierDropDown.getSelectedItem()
							.toString());
					refresh();
				} else
					//JOptionPane.showMessageDialog(null,
						//	"Empty Active Supplier List", "Success",
							//JOptionPane.PLAIN_MESSAGE);
				;
			}
		});
	}

	public void deactivateSupplier(String name) {
		for (Supplier supplier : Supplier.getSupplierList()) {
			if (name.equalsIgnoreCase(supplier.getName())) {
				supplier.setActive(false);
				Supplier.saveSupplier();
				JOptionPane.showMessageDialog(null,
						"Supplier is not active anymore", "Success",
						JOptionPane.PLAIN_MESSAGE);
				break;
			}
		}
	}

	public void refresh() {
		supplierDropDown.removeAllItems();
		compileSupplierNames();
	}

	// Used to fill up the combo box with suppliers from the list
	public void compileSupplierNames() {
		for (Supplier supplier : Supplier.getSupplierList()) {
			supplierDropDown.addItem(supplier.getName());
		}
	}

}