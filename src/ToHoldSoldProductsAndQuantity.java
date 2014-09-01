import java.util.ArrayList;


public class ToHoldSoldProductsAndQuantity {
	
	
	private String date;
	private String name;
	private double quantity;
	
	public ToHoldSoldProductsAndQuantity(String date, String name, double quantity) {
		this.date = date;
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


}
