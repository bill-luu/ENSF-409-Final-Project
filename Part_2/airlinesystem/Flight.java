package airlinesystem;
import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * The flight class used to create flight objects
 */
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
	static final long serialVersionUID = 2L;

	/**
	 * Flight constructor used when adding a flightID
	 * @param fId The flight ID
	 * @param dest The destination of the flight
	 * @param src The initial location of the flight
	 * @param dateOF The date the flight leaves
	 * @param timeOF The time the flight leaves
	 * @param durOF The duration of the flight
	 * @param tS The number of total seats
	 * @param aS The number of remaining seats
	 * @param prc The price of the flight
	 */
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

	/**
	 * The flight constructor without the flight ID
	 * @param dest The destination of the flight
	 * @param src The initial location of the flight
	 * @param dateOF The date the flight leaves
	 * @param timeOF The time the flight leaves
	 * @param durOF The duration of the flight
	 * @param tS The number of total seats
	 * @param prc The price of the flight
	 */
	public Flight(String dest, String src, String dateOF, String timeOF, String durOF, String tS, String prc){
		destination = dest;
		source = src;
		dateOfFlight = dateOF;
		timeOfFlight = timeOF;
		durationOfFlight = durOF;
		totalSeats = tS;
		price = prc;
	}

	/**
	 * The string displayed to the GUI
	 * @return The string information of the flight sent to the GUI
	 */
	public String toDisplay(){
		return flightId + "  " + destination + "  " + source + "  " + "  " + dateOfFlight;
	}

	/**
	 * If a flight has availble seats
	 * @return True if a flight has availble seats, false otherwise
	 */
	public boolean hasAvailableSeats(){
		if(Integer.getInteger(availableSeats) <= 0)
			return false;
		return true;
	}

	/**
	 * Setters and Getters
	 */
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
		String toReturn = String.format("%.2f", prc);
		return toReturn;
	}
}
