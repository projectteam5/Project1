import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SupplierMenuGUI extends JPanel{
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 12);
	private final static Color colorButtons = new Color(126, 163, 249);
	private final static Color colorButtonSelected = new Color(21, 82, 223);
	
	JButton editButton;
	JButton addButton;
	JButton deleteButton;
	JButton viewListButton;
	JButton singleSupplierDetails;
	JButton reactivateSupplier;
	JLabel title;

	public SupplierMenuGUI() {
	
		this.setLayout(new GridLayout(0, 1));
		title = new JLabel("Supplier Menu");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		editButton = new JButton("Edit Supplier");
	 	viewListButton = new JButton("List Suppliers");
	 	deleteButton = new JButton("Delete Supplier");
	 	addButton = new JButton("Add Supplier");
	 	reactivateSupplier = new JButton("Reactivate Supplier");
	 	singleSupplierDetails = new JButton("Supplier details");
	 	
	 	colorButton();
	 	
	 	this.add(title);
	 	this.add(addButton);
	 	this.add(deleteButton);
	 	this.add(reactivateSupplier);
	 	this.add(editButton);
	 	this.add(singleSupplierDetails);
	 	this.add(viewListButton);
		
		// Launches the edit supplier frame
		editButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				colorButton();
				editButton.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new EditSupplierGUI());
			}
		});
		
		addButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				addButton.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new AddSupplierGUI());
			}
		});
			
		// Launches the add/delete supplier from list frame
		deleteButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				colorButton();
				deleteButton.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new DeleteSupplierGUI());
			}
		});
		
		// Launches the reactivation of a supplier GUI
		reactivateSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				colorButton();
				reactivateSupplier.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ReactivateSupplierGUI());
			}
		});
		
		// Launches the list suppliers frame
		viewListButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				colorButton();
				viewListButton.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new ListSuppliersGUI());
			}
		});
		
		// Launches the the supplier of a particular product frame
		singleSupplierDetails.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				colorButton();
				singleSupplierDetails.setBackground(colorButtonSelected);
				MenuGUI.getInstance().setPanelAction(new SupplierDetailsGUI());
			}
		});
	}
	public void colorButton(){
		addButton.setBackground(colorButtons);
		addButton.setFont(fontButtons);
		deleteButton.setBackground(colorButtons);
		deleteButton.setFont(fontButtons);
		editButton.setBackground(colorButtons);
		editButton.setFont(fontButtons);
		singleSupplierDetails.setBackground(colorButtons);
		singleSupplierDetails.setFont(fontButtons);
		viewListButton.setBackground(colorButtons);
		viewListButton.setFont(fontButtons);
		reactivateSupplier.setBackground(colorButtons);
		reactivateSupplier.setFont(fontButtons);
	}
}
