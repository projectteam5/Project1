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

public class ReactivateProductGUI extends JPanel {
	private JLabel title;
	private JLabel productLabel;
	private JComboBox<String> productDropDown = new JComboBox<String>();
	private JButton buttonReactivate;
	private Product chosenProduct;
	// fixing the layout
	JLabel labelEmpty = new JLabel(" ");
	JLabel labelEmpty1 = new JLabel(" ");
	JLabel labelEmpty2 = new JLabel(" ");
	JLabel labelEmpty3 = new JLabel(" ");
	JLabel labelEmpty4 = new JLabel(" ");

	public ReactivateProductGUI() {
		this.setLayout(new GridLayout(0, 1));
		title = new JLabel("Reactivating Products");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		productLabel = new JLabel("Please choose a product from the list below");
		buttonReactivate = new JButton("Reactivate");
		compileProductNames();

		this.add(title);
		this.add(labelEmpty);
		this.add(productLabel);
		this.add(productDropDown);
		this.add(buttonReactivate);
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);

		buttonReactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = productDropDown.getSelectedItem().toString();
				for (Product product : RetailSystem.getInstance().getProducts()) {
					if (name.equalsIgnoreCase(product.getName())) {
						chosenProduct = product;
						reactivateChosenProduct(chosenProduct);
						reloadDropDown();
						break;
					}
				}

			}
		});
	}
	public void compileProductNames() {
		for (Product product : RetailSystem.getInstance().getProducts()) {
			if (!product.isActive()) {
				productDropDown.addItem(product.getName());

			}
		}
	}

	public void reloadDropDown() {
		this.removeAll();
		productDropDown = new JComboBox();
		compileProductNames();
		this.add(title);
		this.add(productLabel);
		this.add(productDropDown);
		this.add(buttonReactivate);
		this.add(labelEmpty);
		this.add(labelEmpty1);
		this.add(labelEmpty2);
		this.add(labelEmpty3);
		this.add(labelEmpty4);
		revalidate();
		repaint();

	}

	public void reactivateChosenProduct(Product product) {
		product.setActive(true);
		saveProduct();
		JOptionPane.showMessageDialog(null, "Product has been set to active");
	}

	public static void saveProduct() {
		try {
			FileWriter productFile;
			productFile = new FileWriter("products.txt");
			DataBase.writeProducts(RetailSystem.getInstance().getProducts(),
					productFile);
			productFile.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
