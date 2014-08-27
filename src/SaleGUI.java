import javax.swing.JComboBox;
import javax.swing.JPanel;


public class SaleGUI extends JPanel {
	private JComboBox<String> productDropDown = new JComboBox<String>();

	public SaleGUI() {
		compileProductNames();
		this.add(productDropDown);
	}
	
	public void compileProductNames(){
		for(Product product: RetailSystem.getInstance().getProducts()){
			if(product.isActive()){
				productDropDown.addItem(product.getName());

			}
		}
	}

}
