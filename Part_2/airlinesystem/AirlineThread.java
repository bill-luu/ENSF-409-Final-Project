package airlinesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The individual thread each client uses. Recieves messages from the socket and performs operations on the database.
 * @author Bill Luu, Mark Ricalde, Travis Manchee
 *
 */
public class AirlineThread implements Runnable {

	/**
	 * The socket the thread uses
	 */
	Socket socket;
	/**
	 * The database the thread interacts with
	 */
	Database database;
	/**
	 * The Stream for input to the Socket
	 */
	private ObjectInputStream inputObject;
	/**
	 * The Stream for output from the socket
	 */
	private ObjectOutputStream outputObject;
	
	/**
	 * Initializes the thread. Makes the Object streams and assigns the socket/database
	 * @param socket
	 * @param database
	 */
	public AirlineThread(Socket socket, Database database)
	{
		this.socket = socket;
		this.database = database;
		
		try {
			System.out.println("Thread Started");
			outputObject = new ObjectOutputStream(socket.getOutputStream());
			inputObject = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the response is properly parsed.
	 * @param response
	 * @param expectedArgs The amount of arguments there should be
	 * @return true if the amount of arguments is expected, false otherwise
	 * @throws IOException
	 */
	public boolean isValidResponse(String[] response, int expectedArgs) throws IOException
	{
		if(response.length != expectedArgs) //Check if the number of arguments are correct.
		{
			outputObject.writeObject((String)"Could not parse response");
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * Parses the response from the client, and calls the appropriate database action.
	 */
	@Override
	public void run() {
		boolean isRunning = true;
		while(isRunning)
		{
			try {
				String[] response = ((String)inputObject.readObject()).split("_");
				System.out.println(response[0]);
				switch(response[0]){
					case "SEARCHFLIGHT":
						if(isValidResponse(response, 3))
						{
							String param = response[1];
							String key = response[2];
							outputObject.writeObject(database.searchFlight(param, key));
							System.out.println("Search done");
						}
						break;
					case "GETFLIGHTS":
						if(isValidResponse(response, 1))
						{
							outputObject.writeObject((String)"GOOD");
							outputObject.writeObject(database.getAllFlights());
						}
						break;
					case "BOOK":
						if(isValidResponse(response, 4))
						{
							String flightId = response[1];
							String firstName = response[2];
							String lastName = response[3];
							//Output a ticket
							
							Ticket newTicket = new Ticket(database.getFlight(flightId), ""+0, firstName, lastName);
							newTicket = database.addTicket(newTicket);
							if(newTicket != null)
							{
								outputObject.writeObject((String)"GOOD");
								outputObject.writeObject(newTicket);
							}
							else
							{
								outputObject.writeObject((String)"BAD");
							}
						}
						break;
					case "ADDFLIGHT":
						if(isValidResponse(response, 1))
						{
							try {
								Thread.sleep(100);
							//Wait to make sure the socket has sent the flight object
							Flight flight = (Flight) inputObject.readObject();
							
							outputObject.writeObject((String)database.addFlight(flight));
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
						break;
					case "DELETE":
						if(isValidResponse(response, 2))
						{
							String ticketId = response[1];
							outputObject.writeObject((String)database.deleteTicket(ticketId));
						}
						break;
					case "SEARCHTICKET":
						if(isValidResponse(response, 3))
						{
							String param = response[1];
							String key = response[2];
							outputObject.writeObject((String)"GOOD");
							outputObject.writeObject(database.searchTicket(param, key));
						}
						break;
					case "GETTICKETS":
						if(isValidResponse(response, 1))
						{
							outputObject.writeObject((String)"GOOD");
							outputObject.writeObject(database.getAllTickets());
						}
						break;
					case "QUIT":
						isRunning = false;
					default:
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try{
			inputObject.close();
			outputObject.close();
			socket.close();	
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
