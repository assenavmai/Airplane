package planes;

public class Orders {
	
	private String orderName;
	private String orderDest;

	private boolean scheduled;
	
	public Orders(String orderName, String orderDest, boolean scheduled) {
		this.orderName = orderName;
		this.orderDest = orderDest;
		this.scheduled = scheduled;
	}
	
	public String getOrderName() {
		return this.orderName;
	}
	
	public String getOrderDest() {
		return this.orderDest;
	}
	
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}
	
	public boolean getScheduled() {
		return this.scheduled;
	}
}
