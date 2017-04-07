package airlinesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class AirlineThread implements Runnable {

	Socket socket;
	Database database;
	private ObjectInputStream inputObject;
	private ObjectOutputStream outputObject;
	
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

	public boolean isValidResponse(String[] response, int expectedArgs) throws IOException
	{
		if(response.length != expectedArgs) //Check if the number of arguments are correct.
		{
			outputObject.writeObject((String)"Could not parse response");
			return false;
		}
		return true;
	}

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
							outputObject.writeObject((String)"GOOD");
							outputObject.writeObject(newTicket);
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
