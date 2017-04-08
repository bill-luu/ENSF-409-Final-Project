package airlinesystem;

/**
 * The ticket class used to create ticket objects
 */
public class Ticket extends Flight {
	private String ticketId;
	private String firstName;
	private String lastName;
	static final long serialVersionUID = 1L;

	/**
	 * The constructor with all fields
	 * @param fId The flight ID
	 * @param dest The destination of the flight
	 * @param src The initial destination of the flight
	 * @param dateOF The date of the flight
	 * @param timeOF The time of the flight
	 * @param durOF The duration of the flight
	 * @param tS The total seats of the flight
	 * @param tA The availble seats of the flight
	 * @param prc The price of the flight
	 * @param tId The ticket ID
	 * @param fN The first name of the passenger
	 * @param lN The last name of the messenger
	 */
	public Ticket (String fId, String dest, String src, String dateOF, String timeOF, String durOF, String tS, String tA, String prc, String tId, String fN, String lN){
		super(fId, dest, src, dateOF, timeOF, durOF, tS, tA, prc);
		ticketId = tId;
		firstName = fN;
		lastName = lN;
	}
	public Ticket (Flight flight, String ticketID, String firstName, String lastName){
		super(flight.getFlightId(), flight.getDest(), flight.getSrc(), flight.getDate(), flight.getTime(), flight.getDuration(), flight.getTotalSeats(), flight.getAvailableSeats(), flight.getPrice());
		this.ticketId = ticketID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Setters and Getters
	 */
	public String getTicketId(){
		return ticketId;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}

	/**
	 * Displays the ticket info in the GUI
	 * @return A string with the ticket info
	 */
	public String toDisplay(){
		String paddedLastName = this.lastName;
		String paddedFirstName = this.firstName;
		while(paddedLastName.length() != 45)
			paddedLastName += " ";
		while(paddedFirstName.length() != 45)
			paddedFirstName += " ";
		String toReturn = this.ticketId + "  " + getFlightId() + "  " + paddedLastName + "  " + paddedFirstName;
		return toReturn;
	}
}
