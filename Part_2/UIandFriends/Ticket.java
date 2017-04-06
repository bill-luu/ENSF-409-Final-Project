package Final_Project;

public class Ticket extends Flight {
	private String ticketId;
	private String firstName;
	private String lastName;
	
	public Ticket (String fId, String dest, String src, String dateOF, String timeOF, String durOF, String tS, String tA, String prc, String tId, String fN, String lN){
		super(fId, dest, src, dateOF, timeOF, durOF, tS, tA, prc);
		ticketId = tId;
		firstName = fN;
		lastName = lN;
	}
	public Ticket (Flight flight, String ticketID, String firstName, String lastName){
		super(flight.getFlightId(), flight.getDest(), flight.getSrc(), flight.getDate(), flight.getTime(), flight.getDuration(), flight.getTotalSeats(), flight.getAvailableSeats(), flight.getPrice());
		ticketId = ticketId;
		firstName = firstName;
		lastName = lastName;
	}
	
	public String getTicketId(){
		return ticketId;
	}
	
	public String toDisplay(){
		String toReturn = super.getFlightId() + "		" + ticketId + "		" + lastName;
		return toReturn;
	}
	public String toPrint(){
		String toReturn ="";
		return toReturn;
	}

}
