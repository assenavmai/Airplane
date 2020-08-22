package planes;

import java.util.ArrayList;

public class Flight {
	
	private String day;
	private String source;
	private String destination;
	private String departureTime;
	private String arrivalTime;
	private Planes plane;
	private String flightNumber;
	private ArrayList<Orders> orders;
	
	public Flight(String source, String destination, Planes plane) {

		this.source = source;
		this.destination = destination;
		this.plane = plane;
		this.orders = new ArrayList<>();
	}
	
	public void setSchedule(String day, String arrivalTime, String departureTime, String flightNumber) {
		this.day = day;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.flightNumber = flightNumber;
	}
	
	public String getFlightNumber() {
		return this.flightNumber;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getDay() {
		return this.day;
	}
	
	public String getOrders(int index) {
		return this.orders.get(index).getOrderName();
	}
	
	public int getOrderSize() {
		return this.orders.size();
	}
	
	public void setOrders(ArrayList<Orders> order) {

		this.orders = order;
	}
	

	public void addOrder(Orders order) {
		this.orders.add(order);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
