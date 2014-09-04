//GUI working. Method working//test commit
import java.awt.BorderLayout;
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

public class ViewProductGUI extends JPanel {
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JLabel label;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	
	private JLabel mainTitle;
	private JButton buttonViewProduct;

	public ViewProductGUI() {
		this.setLayout(new GridLayout(0, 1));
		compileProductNames();
		mainTitle = new JLabel("View Product");
		mainTitle.setFont(new Font("Arial", Font.BOLD, 20));
		buttonViewProduct = new JButton("View Product Details");
		label = new JLabel();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		
		label.setText("Please pick the product you want to view from the product list below");
		label1.setText("Product ID: ");
		label2.setText("Product Name: ");
		label3.setText("Product Cost: ");
		label4.setText("Product Markup: ");
		label5.setText("Supplier ID/Name: ");

		buttonViewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {
				String name = productDropDown.getSelectedItem().toString();
				boolean found = false;
				for (Product product : RetailSystem.getInstance().getProducts()) {
					if (name.equalsIgnoreCase(product.getName())) {
						found = true;
						label1.setText("Product ID: "+product.getProductID());
						label2.setText("Product Name: "+product.getName());
						label3.setText("Product Cost: "+product.getCost());
						label4.setText("Product Markup: "+product.getMarkup());
						label5.setText("Supplier ID/Name: "+product.getSupplier().getSupplierID()+" | "+product.getSupplier().getName());
						break;
					}
				}
				if (!found) {
					JOptionPane.showMessageDialog(null,
							"No Product With This ID in System!");
				}
			}
		});

		this.add(mainTitle);
		this.add(label);
		this.add(productDropDown);
		this.add(buttonViewProduct);
		
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.add(label5);
		// fixing the layout
		JLabel labelEmpty = new JLabel(" ");
		JLabel labelEmpty1 = new JLabel(" ");
		JLabel labelEmpty2 = new JLabel(" ");
		JLabel labelEmpty3 = new JLabel(" ");
		JLabel labelEmpty4 = new JLabel(" ");
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);

	}

	public void compileProductNames() {
		for (Product product : RetailSystem.getInstance().getProducts()) {
			if (product.isActive()) {
				productDropDown.addItem(product.getName());

			}
		}
	}

}
