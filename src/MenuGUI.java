import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuGUI extends JFrame {
	
	private static MenuGUI instance; // we want only one instance of this class
	private JFrame frame;
	private Container container;
	private JPanel panelMenu;
	private JPanel panelSubMenu;
	private JPanel panelAction;
	private JPanel panelEmpty;
	
	private JButton buttonUser;
	private JButton buttonCustomer;
	private JButton buttonProduct;
	private JButton buttonSupplier;
	private JButton buttonOrder;
	private JButton buttonAvailability;
	private JLabel menuLabel;
	private JButton buttonViewGraph;
	private JButton buttonSale;
	private JButton buttonInvoice;
	private JButton buttonAccounting;

	private final static double percHeight1 = 0.80;
	private final static double percHeight2 = 0.50;
	private final static double percWidth1 = 0.20;
	private final static double percWidth2 = 0.15;
	private final static double percWidth3 = 0.50;
	private final static double percWidthEmpty = 0.03;
	private final static double percHeigthEmpty = 0.05;
	
	private final static Font fontButtons = new Font("Arial", Font.BOLD, 16);
	private final static Color colorButtons = new Color(126,163,249);
	private final static Color colorButtonSelected = new Color(21,82,223);

	public MenuGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024,600);
		frame.setTitle("Retail System");
		
		panelMenu = new JPanel();
		panelMenu.setLayout(new GridLayout(0, 1));
		
		panelSubMenu = new JPanel();
		panelSubMenu.setLayout(new GridLayout(0, 1));
			
		panelAction = new JPanel();
		panelAction.setLayout(new GridLayout(0, 1));
		
		panelEmpty = new JPanel();
		panelEmpty.setLayout(new GridLayout(0, 1));
		
		menuLabel = new JLabel("Main Menu");
		menuLabel.setFont( new Font("Arial", Font.BOLD, 20));
		buttonUser = new JButton("Manage Users");
		buttonCustomer = new JButton("Manage Customers");
		buttonProduct = new JButton("Manage Products");
		buttonSupplier = new JButton("Manage Suppliers");
		buttonOrder = new JButton("Manage Orders");
		buttonAvailability = new JButton("View Stock");
		buttonSale = new JButton("Sales");
		buttonViewGraph = new JButton("Graphs");
		buttonInvoice = new JButton("Invoices");
		buttonAccounting = new JButton("Accounting");
		
		colorButton();
		
		// if it is a manager i can see Manage Customer, Manage Product
		// Manage Supplier, Manage Order
		if (RetailSystem.getInstance().getCurrentUserType()
				.equalsIgnoreCase("Manager")) {
			panelMenu.add(menuLabel);
			panelMenu.add(buttonCustomer);
			panelMenu.add(buttonProduct);
			panelMenu.add(buttonSupplier);
			panelMenu.add(buttonUser);
			panelMenu.add(buttonOrder);
			panelMenu.add(buttonAvailability);
			panelMenu.add(buttonSale);
			panelMenu.add(buttonViewGraph);
			panelMenu.add(buttonInvoice);
			panelMenu.add(buttonAccounting);
			
		} else {
			panelMenu.add(menuLabel);
			panelMenu.add(buttonCustomer);
			panelMenu.add(buttonAvailability);
			panelMenu.add(buttonSale);
			panelMenu.add(buttonInvoice);
			//fixing the layout
			JLabel labelEmpty = new JLabel(" ");
			JLabel labelEmpty1 = new JLabel(" ");
			JLabel labelEmpty2 = new JLabel(" ");
			JLabel labelEmpty3 = new JLabel(" ");
			panelMenu.add(labelEmpty);
			panelMenu.add(labelEmpty1);
			panelMenu.add(labelEmpty2);
			panelMenu.add(labelEmpty3);
			panelMenu.add(labelEmpty);
			panelMenu.add(labelEmpty3);
			panelMenu.add(labelEmpty2);
			panelMenu.add(labelEmpty1);
	
		}
		
		container = frame.getContentPane();
		container.add(panelMenu);
		container.add(panelSubMenu);
		container.add(panelAction);
		container.setLayout(null);
		
		buttonUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				buttonUser.setBackground(colorButtonSelected);
				setSubMenu(new UserGUI());
				setPanelAction(panelEmpty);
			}
		});
		
		buttonCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				buttonCustomer.setBackground(colorButtonSelected);
				setSubMenu(new CustomerGUI());
				setPanelAction(panelEmpty);
			}
		});
		
		buttonSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton();
				buttonSupplier.setBackground(colorButtonSelected);
				setSubMenu(new SupplierMenuGUI());
				setPanelAction(panelEmpty);
			}
		});
		
		buttonAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorButton();
				buttonAvailability.setBackground(colorButtonSelected);
				setSubMenu(new StockGUI());
				setPanelAction(panelEmpty);

			}
		});

		buttonProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorButton();
				buttonProduct.setBackground(colorButtonSelected);
				setSubMenu(new ProductMenuGUI());
				setPanelAction(panelEmpty);

			}
		});

		buttonOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorButton();
				buttonOrder.setBackground(colorButtonSelected);
				setSubMenu(new OrderGUI());
				setPanelAction(panelEmpty);

			}
		});
		
		buttonSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorButton();
				buttonSale.setBackground(colorButtonSelected);
				setSubMenu(panelEmpty);
				setPanelAction(new SaleGUI());

			}
		});
		
		buttonViewGraph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				colorButton();
				buttonViewGraph.setBackground(colorButtonSelected);
				setSubMenu(new GraphGUI());
				setPanelAction(panelEmpty);
			}
		});
		
		buttonInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorButton();
				buttonInvoice.setBackground(colorButtonSelected);
				setSubMenu(new InvoiceMenuGUI());
				setPanelAction(panelEmpty);
			}
		});
		
		buttonAccounting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorButton();
				buttonAccounting.setBackground(colorButtonSelected);
				setSubMenu(panelEmpty);
				setPanelAction(new AccountingGUI());
			}
		});
		
		frame.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	resize();
            }
        });
		
		frame.setVisible(true);

	}
	
	public void setSubMenu (JPanel panel){
		container.remove(panelSubMenu);
		panelSubMenu = panel;
		container.add(panelSubMenu);
		this.resize();
	}
	
	public void setPanelAction (JPanel panel){
		container.remove(panelAction);
		panelAction = panel;
		container.add(panelAction);
		this.resize();
	}
	
	public void resize(){
		panelMenu.setSize((int)(frame.getWidth()*percWidth1),(int) (frame.getHeight()*percHeight1));
		panelMenu.setLocation((int)(frame.getWidth()*percWidthEmpty),(int)(frame.getHeight()*percHeigthEmpty));//
		//panelMenu.setBackground(Color.RED);
		panelSubMenu.setSize((int)(frame.getWidth()*percWidth2),(int) (frame.getHeight()*percHeight2));
		panelSubMenu.setLocation((int)(panelMenu.getLocation().getX()+ panelMenu.getWidth()+ frame.getWidth()*percWidthEmpty),(int)(frame.getHeight()*percHeigthEmpty));
		//panelSubMenu.setBackground(Color.RED);
		panelAction.setSize((int)(frame.getWidth()*percWidth3),(int) (frame.getHeight()*percHeight1));
		panelAction.setLocation((int)(panelSubMenu.getLocation().getX()+panelSubMenu.getWidth()+frame.getWidth()*percWidthEmpty),(int)(frame.getHeight()*percHeigthEmpty));
		//panelAction.setBackground(Color.RED);
		frame.revalidate();
		frame.repaint();
	}

	// we want a unique instance that every class can refer to
	// hence, we create the object only once and we save it in a static variable
	public static MenuGUI getInstance() {
			if (instance == null) {
				// the first time we try to access the instance
				// if it doesn't exist it is created
				instance = new MenuGUI();
			}
			return instance;
	}

	public void colorButton(){
		buttonUser.setBackground(colorButtons);
		buttonUser.setFont(fontButtons);
		buttonCustomer.setBackground(colorButtons);
		buttonCustomer.setFont(fontButtons);
		buttonProduct.setBackground(colorButtons);
		buttonProduct.setFont(fontButtons);
		buttonSupplier.setBackground(colorButtons);
		buttonSupplier.setFont(fontButtons);
		buttonOrder.setBackground(colorButtons);
		buttonOrder.setFont(fontButtons);
		buttonAvailability.setBackground(colorButtons);
		buttonAvailability.setFont(fontButtons);
		buttonSale.setBackground(colorButtons);
		buttonSale.setFont(fontButtons);
		buttonViewGraph.setBackground(colorButtons);
		buttonViewGraph.setFont(fontButtons);
		buttonInvoice.setBackground(colorButtons);
		buttonInvoice.setFont(fontButtons);
		buttonAccounting.setBackground(colorButtons);
		buttonAccounting.setFont(fontButtons);
	}


}
