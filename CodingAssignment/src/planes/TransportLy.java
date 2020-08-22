package planes;

import java.io.*;
import java.util.*;


public class TransportLy {

	public static void uploadSchedule(ArrayList<Flight> flights) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./schedule.txt"));
			
			String line;
			
			while((line = br.readLine()) != null) 
			{
				String[] splits = line.split(",");
				
				Flight f = new Flight(splits[2], splits[3], new Planes(20));
				f.setSchedule(splits[0], "00:00", "12:00", splits[1]);
				
				flights.add(f);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	public static void printSchedule(ArrayList<Flight> flights) {
		
		for(int i = 0; i < flights.size(); i++) {
			System.out.println(String.format("Flight: %s, departure: %s, arrival: %s, day: %s", 
					flights.get(i).getFlightNumber(), flights.get(i).getDestination(), flights.get(i).getSource(), flights.get(i).getDay()));
		}
	}
	
    public static void loadOrders(ArrayList<Flight> flights, ArrayList<Orders> orders) {
		
		boolean whitespaceState = false;
		boolean orderState = false;
		boolean destinationState = false;
		boolean quoteState = false;
		String orderName = "";
		String destinationName = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("./coding-assigment-orders.json"));
			
			int line;
			
			while((line = br.read()) != -1)
			{
				
				if(whitespaceState)
				{
					continue;
				}
				else if(line == '"') {
					quoteState = true;
					whitespaceState = false;
				}
				
				else if(quoteState)
				{
					if(orderState) {
						orderState = false;
						
					}
					else if(destinationState)
					{
						destinationState = false;
						
						Orders o = new Orders(orderName, destinationName, false);
						
						for(int i = 0; i < flights.size(); i++) {
							
							if(flights.get(i).getDestination().equals(destinationName) && flights.get(i).getOrderSize() < 20) {
								flights.get(i).addOrder(o);
								o.setScheduled(true);
								break;
							}
						}
						
						if(!o.getScheduled())
						{
							orders.add(o);
						}

						orderName = "";
						destinationName = "";
						
						
					}
					else
					{
						if(line == 'o') {
							orderState = true;
							
						}
						else if(line == 'Y' && orderState == false) {
							destinationState = true;
						}
					}					
					
					quoteState = false;
				}
				if(orderState && line != '"') {
					
					orderName += (char)line;
				}
				if(destinationState && line != '"') {
					
					destinationName += (char)line;
				}	
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
    
    public static void printItinerary(ArrayList<Flight> flights, ArrayList<Orders> orders) {
    	
    	for(int i = 0; i < flights.size(); i++) {
    		for(int j = 0; j < flights.get(i).getOrderSize(); j++)
    		{
    			System.out.println(String.format("order: %s, flightNumber: %s, departure: %s, arrival: %s, day: %s", 
    					flights.get(i).getOrders(j),flights.get(i).getFlightNumber(), flights.get(i).getDestination(), flights.get(i).getSource(), flights.get(i).getDay()));
    		}
		}
    	
    	for(int i = 0; i < orders.size(); i++) {
    		System.out.println(String.format("order: %s, flightNumber: not scheduled", orders.get(i).getOrderName()));
    	}
    	
    }
	
	public static void main(String[] args) {
		
		ArrayList<Flight> flights = new ArrayList<>();
		ArrayList<Orders> orders = new ArrayList<>();

		uploadSchedule(flights);
		printSchedule(flights);
		loadOrders(flights, orders);
		printItinerary(flights, orders);
		
    }
}
