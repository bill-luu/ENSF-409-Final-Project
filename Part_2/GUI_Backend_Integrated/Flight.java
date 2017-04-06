package Final_Project;

import java.io.Serializable;

public class Flight implements Serializable{
	private String flightId;
	private String destination;
	private String source;
	private String dateOfFlight;
	private String timeOfFlight;
	private String durationOfFlight;
	private String totalSeats;
	private String availableSeats;
	private String price;
	
	public Flight(String fId, String dest, String src, String dateOF, String timeOF, String durOF, String tS, String aS, String prc){
		flightId = fId;
		destination = dest;
		source = src;
		dateOfFlight = dateOF;
		timeOfFlight = timeOF;
		durationOfFlight = durOF;
		totalSeats = tS;
		availableSeats = aS;
		price = prc;
	}
	public Flight(String dest, String src, String dateOF, String timeOF, String durOF, String tS, String prc){
		destination = dest;
		source = src;
		dateOfFlight = dateOF;
		timeOfFlight = timeOF;
		durationOfFlight = durOF;
		totalSeats = tS;
		price = prc;
	}
	public String toDisplay(){
		String toReturn = flightId + "		" + destination + "		" + source;
		return toReturn;
	}
	public boolean hasAvailableSeats(){
		if(Integer.getInteger(availableSeats) <= 0)
			return false;
		return true;
	}
	
	public String getFlightId(){
		return flightId;
	}
	public String getDest(){
		return destination;
	}
	public String getSrc(){
		return source;
	}
	public String getDate(){
		return dateOfFlight;
	}
	public String getTime(){
		return timeOfFlight;
	}
	public String getDuration(){
		return durationOfFlight;
	}
	public String getTotalSeats(){
		return totalSeats;
	}
	public String getAvailableSeats(){
		return availableSeats;
	}
	public String getPrice(){
		return price;
	}
	public String getTaxedPrice(){
		Double prc = Double.parseDouble(price);
		prc *= 1.07;
		return prc.toString();
	}
}
