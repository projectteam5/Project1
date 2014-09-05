//GUI done. Method working//test commit
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RemoveProductGUI extends JPanel {
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JPanel panel;
	private JButton buttonDeleteProduct;
	private JLabel labelTitle;
	private JLabel productLabel;

	// fixing the layout
	JLabel labelEmpty = new JLabel(" ");
	JLabel labelEmpty1 = new JLabel(" ");
	JLabel labelEmpty2 = new JLabel(" ");
	JLabel labelEmpty3 = new JLabel(" ");
	JLabel labelEmpty4 = new JLabel(" ");
	JLabel labelEmpty5 = new JLabel(" ");

	public RemoveProductGUI() {
		this.setLayout(new GridLayout(0, 1));

		labelTitle = new JLabel("Remove Product");
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		productLabel = new JLabel("Please choose a product from the list below");
		compileProductNames();

		buttonDeleteProduct = new JButton("Delete");

		this.add(labelTitle);
		this.add(labelEmpty);
		this.add(productLabel);
		this.add(productDropDown);
		this.add(buttonDeleteProduct);

		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);

		// When a user presses the delete button, the system will check which
		// product they are trying to delete
		buttonDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for (Product product : RetailSystem.getInstance().getProducts()) {
					if (name.equalsIgnoreCase(product.getName())) {
						found = true;
						product.setActive(false);
						JOptionPane.showMessageDialog(null, "Product "
								+ product.getName()
								+ " has been removed from the system");
						Product.saveProduct();
						repopulateComboBox();
						break;
					}
				}
				if (!found) {
					JOptionPane.showMessageDialog(null,
							"No Product With This ID in System!");
				}
			}
		});

	}

	public void repopulateComboBox() {
		this.removeAll();
		// this.remove(productDropDown);
		// this.remove(buttonDeleteProduct);
		compileProductNames();
		productDropDown = new JComboBox();
		compileProductNames();
		this.add(labelTitle);
		this.add(labelEmpty);
		this.add(productLabel);
		this.add(productDropDown);
		this.add(buttonDeleteProduct);
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);

		revalidate();
		repaint();
	}

	public void compileProductNames() {
		for (Product product : RetailSystem.getInstance().getProducts()) {
			if (product.isActive()) {
				productDropDown.addItem(product.getName());

			}
		}
	}


}
