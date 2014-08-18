import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OrderGUI extends JFrame implements ActionListener {
	private JPanel panel;
	private Container container;
	private JButton createOrder;
	private JButton editOrder;
	private JButton viewOrder;
	private JButton removeOrder;
	private JButton returnToMainMenu;
	
	public OrderGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		setTitle(this.getClass().toString());
		setLocationRelativeTo(null);	//null sets the frame to centre
		
		panel = new JPanel();
		container = getContentPane();
		container.add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(4, 2));
		
		createOrder = new JButton("Create Order");
		editOrder = new JButton("Edit Order");
		viewOrder = new JButton("View Order");
		removeOrder = new JButton("Remove Order");
		returnToMainMenu = new JButton("Main Menu");
		
		panel.add(createOrder);
		panel.add(editOrder);
		panel.add(viewOrder);
		panel.add(removeOrder);
		panel.add(returnToMainMenu);
		
		createOrder.addActionListener(this);
		editOrder.addActionListener(this);
		viewOrder.addActionListener(this);
		removeOrder.addActionListener(this);
		returnToMainMenu.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		Object target = event.getSource();
		
		if(target == createOrder) {
			try {
				CreateOrderGUI createOrder = new CreateOrderGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach createOrderGUI");
			}
		}
		
		if(target == editOrder) {
			try {
				EditOrderGUI editOrder = new EditOrderGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach EditOrderGUI");
			}
		}
		
		if(target == viewOrder) {
			try {
				ViewOrderGUI viewOrder = new ViewOrderGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach viewOrderGUI");
			}
		}
		
		if(target == removeOrder) {
			try {
				RemoveOrderGUI removeOrder = new RemoveOrderGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach viewOrderGUI");
			}
		}
		
		if(target == returnToMainMenu) {
			try {
				MenuGUI returnToMainMenu = new MenuGUI();
				this.setVisible(false);
				this.dispose();
			} catch(Exception e) {
				System.err.println(e);
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(this, "cannot reach MenuGUI");
			}
		}
	}
}