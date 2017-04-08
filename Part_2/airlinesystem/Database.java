package airlinesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * @author Bill Luu, Mark Ricalde, Travis Manchee
 * Provides the server interaction with the mySQL Database. 
 * 
 */

public class Database {
	
	/**
	 * A unique flight ID, will be the highest id not yet taken.
	 */
	int uniqueFlightId;
	/**
	 * A unique Ticket ID, is the highest id not yet taken.
	 */
	int uniqueTicketId;

	
	/**
	 * The connection to the database
	 */
	private Connection connection;
	/**
	 * The statement to send to the database
	 */
	private Statement stmt;
	
	/**
	 * Initializes the Database
	 */
	public Database()
	{
		initializeConnection();
		initializeIds();
	}
	
	/**
	 * Initializes the connection to database
	 */
	public void initializeConnection()
	{
		try 
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedatabase", "root", "PASSWORD");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Sets the IDs to be Unique
	 * Runs through the database and sets the IDs to be the highest one
	 */
	public void initializeIds()
	{
		//Parse through flight ID
		ResultSet rs;
		try
		{	
			uniqueFlightId = 1000;
			stmt = connection.createStatement();
			String query = "SELECT * FROM flights";

			rs = stmt.executeQuery(query);
			
			while(rs.next()) //Get the highest flight ID
			{
				Flight flight = new Flight(rs.getString("flightnumber"), rs.getString("destlocation"),
						   rs.getString("sourcelocation"), rs.getString("date"),
						   rs.getString("time"), rs.getString("duration"),
						   rs.getString("totalseats"), rs.getString("remainingseats"),
						   rs.getString("price")) ;
				
				if(uniqueFlightId < Integer.parseInt(flight.getFlightId()))
					uniqueFlightId = Integer.parseInt(flight.getFlightId());
			}
			uniqueFlightId++; //To make sure the ID is unique
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{	
			uniqueTicketId = 100000;
			stmt = connection.createStatement();
			String query = "SELECT * FROM ticket";

			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				Flight f = getFlight(rs.getString("flightid"));
				Ticket ticket = new Ticket(f, rs.getString("ticketid"), rs.getString("firstname"), rs.getString("lastname"));
				if(uniqueTicketId < Integer.parseInt(ticket.getTicketId()))
					uniqueTicketId = Integer.parseInt(ticket.getTicketId());
			}
			uniqueTicketId++;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Searches through all the flights and returns an ArrayList that contains flights containing the key for the param.
	 * @param param The parameter to check
	 * @param key The value the parameter should have
	 * @return An ArrayList of flights matching the param/key, null if an exception was raised
	 */
	synchronized ArrayList<Flight> searchFlight(String param, String key)
	{
		ResultSet rs;
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM flights WHERE " + param + "= '" + key + "'";
			rs = stmt.executeQuery(query);
			ArrayList<Flight> flightList = new ArrayList<Flight>();
			
			while(rs.next())
			{
				Flight flight = new Flight(rs.getString("flightnumber"), rs.getString("destlocation"),
						   rs.getString("sourcelocation"), rs.getString("date"),
						   rs.getString("time"), rs.getString("duration"),
						   rs.getString("totalseats"), rs.getString("remainingseats"),
						   rs.getString("price")) ;
				
				flightList.add(flight);
			}
			
			return flightList;
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns an ArrayList of all the flights in the database
	 * @return An Arraylist of all flights, null if an exception occured.
	 */
	synchronized ArrayList<Flight> getAllFlights()
	{
		ResultSet rs;
		try{	
			stmt = connection.createStatement();
			String query = "SELECT * FROM flights";

			rs = stmt.executeQuery(query);

			ArrayList<Flight> flightList = new ArrayList<Flight>();
			
			while(rs.next())
			{
				Flight flight = new Flight(rs.getString("flightnumber"), rs.getString("destlocation"),
						   rs.getString("sourcelocation"), rs.getString("date"),
						   rs.getString("time"), rs.getString("duration"),
						   rs.getString("totalseats"), rs.getString("remainingseats"),
						   rs.getString("price")) ;
				
				flightList.add(flight);
			}
			
			return flightList;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Updates the amount of seats in a flight.
	 * @param flight The flight to be updated
	 * @param changeInRemainingSeats The number of seats being added/removed
	 * @throws SeatException If a flight with no seats available is being booked, then this is thrown
	 */
	synchronized void changeSeats(Flight flight, int changeInRemainingSeats) throws SeatException
	{
		try{
			String prep = "UPDATE flights "
						+ "SET destlocation= ?"
						+ ", sourcelocation= ?"
						+ ", date= ?"
						+ ", time= ?"
						+ ", duration= ?"
						+ ", totalseats= ? "
						+ ", remainingseats= ?"
						+ ", price= ?"
						+ "WHERE flightnumber= " + flight.getFlightId();
			int newRemainingSeats = Integer.parseInt(flight.getAvailableSeats()) + changeInRemainingSeats;
			if(newRemainingSeats < 0)
			{
				throw new SeatException();
			}
			PreparedStatement preparedStatement = connection.prepareStatement(prep);
			preparedStatement.setString(1, flight.getDest());
			preparedStatement.setString(2, flight.getSrc());
			preparedStatement.setString(3, flight.getDate());
			preparedStatement.setString(4, flight.getTime());
			preparedStatement.setString(5, flight.getDuration());
			preparedStatement.setString(6, flight.getTotalSeats());
			preparedStatement.setString(7, Integer.toString(Integer.parseInt(flight.getAvailableSeats()) + changeInRemainingSeats));
			preparedStatement.setString(8, flight.getPrice());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns the flight with a specific ID
	 * @param flightId 
	 * @return Flight with id flightId
	 */
	synchronized Flight getFlight(String flightId)
	{
		ResultSet rs;
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM flights WHERE flightnumber" + " = '" + Integer.parseInt(flightId) + "'";
			rs = stmt.executeQuery(query);
			Flight flight = null;
			while(rs.next())
			{
				flight = new Flight(rs.getString("flightnumber"), rs.getString("destlocation"),
									   rs.getString("sourcelocation"), rs.getString("date"),
									   rs.getString("time"), rs.getString("duration"),
									   rs.getString("totalseats"), rs.getString("remainingseats"),
									   rs.getString("price")) ;
			}
			return flight;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Adds a ticket to the database
	 * @param ticket The ticket to be added
	 * @return The ticket that was added, with proper ID, null when exception was raised
	 */
	synchronized Ticket addTicket(Ticket ticket)
	{
		try{
			stmt = connection.createStatement();
			String prep = "INSERT INTO ticket "
			+ " (ticketid, flightid, firstname, lastname, date, price)"
			+" VALUES(?, ?, ?, ?, ?, ?)"; 

			int id = uniqueTicketId++;
			changeSeats(getFlight(ticket.getFlightId()), -1);
			PreparedStatement prepared = connection.prepareStatement(prep);
			prepared.setInt(1, id);
			prepared.setInt(2, Integer.parseInt(ticket.getFlightId()));
			prepared.setString(3, ticket.getFirstName());
			prepared.setString(4, ticket.getLastName());
			prepared.setString(5, ticket.getDate());
			prepared.setString(6, ticket.getPrice());
			prepared.executeUpdate();

			
			return getTicket(Integer.toString(id));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(SeatException e)
		{
			return null;
		}
	}

	/**
	 * Adds a Flight to the database
	 * @param flight Flight to be added
	 * @return Confirmation String or error string
	 */
	synchronized String addFlight(Flight flight)
	{
		try{	
			stmt = connection.createStatement();
			String prep = "INSERT INTO flights "
			+ " (flightnumber, destlocation, sourcelocation, date, time, duration, totalseats, remainingseats, price)"
			+" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"; 

			PreparedStatement prepared = connection.prepareStatement(prep);
			prepared.setInt(1, ++uniqueFlightId);
			prepared.setString(2, flight.getDest());
			prepared.setString(3, flight.getSrc());
			prepared.setString(4, flight.getDate());
			prepared.setString(5, flight.getTime());
			prepared.setString(6, flight.getDuration());
			prepared.setInt(7, Integer.parseInt(flight.getTotalSeats()));
			prepared.setInt(8, Integer.parseInt(flight.getTotalSeats())); //Available Seats = Total Seats
			prepared.setString(9, flight.getPrice());
			prepared.executeUpdate();

			return "Flight Added Successfully";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return "Flight could not be added.";
		}
	}
	
	/**
	 * Delete a ticket from the database
	 * @param ticketId 
	 * @return Confirmation/Error String
	 */
	synchronized String deleteTicket(String ticketId)
	{
		try
		{
			//Statement 
			stmt = connection.createStatement();
			Ticket t = getTicket(ticketId);
			changeSeats(getFlight(t.getFlightId()), 1);
			String query = "DELETE FROM ticket WHERE ticketid= " + Integer.parseInt(ticketId);
			stmt.execute(query);

			return "Booking removed";
		}
		catch(Exception err)
		{
			err.printStackTrace();
			return "Unable to remove booking";
		}
	}
	
	/**
	 * Returns a Ticket based on ID
	 * @param ticketId
	 * @return A ticket with ID ticketID, null if exception was raised.
	 */
	synchronized Ticket getTicket(String ticketId)
	{
		ResultSet rs;
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM ticket WHERE ticketid" + " = '" + Integer.parseInt(ticketId) + "'";
			rs = stmt.executeQuery(query);
			Ticket t = null;
			while(rs.next())
			{
				Flight f = getFlight(rs.getString("flightid"));
				t = new Ticket(f, rs.getString("ticketid"), rs.getString("firstname"), rs.getString("lastname"));
			}
			
			return t;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Removes a flight from the database
	 * @param flightId The id of the flight to be removed
	 */
	synchronized void deleteFlight(String flightId)
	{
		try
		{
			//Statement 
			stmt = connection.createStatement();
			String query = "DELETE FROM flights WHERE flightnumber= " + Integer.parseInt(flightId);
			stmt.execute(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	/**
	 * Searches through all the tickets and returns an ArrayList that contains tickets containing the key for the param.
	 * @param param The parameter to check
	 * @param key The value the parameter should have
	 * @return An ArrayList of tickets matching the param/key, null if an exception was raised
	 */
	synchronized ArrayList<Ticket> searchTicket(String param, String key)
	{
		ResultSet rs;
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM ticket WHERE " + param + "= '" + key + "'";
			rs = stmt.executeQuery(query);

			ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
			while(rs.next())
			{
				Flight f = getFlight(rs.getString("flightid"));
				Ticket ticket = new Ticket(f, rs.getString("ticketid"), rs.getString("firstname"), rs.getString("lastname"));
			
				ticketList.add(ticket);
			}
			return ticketList;
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns an arraylist of all tickets in database
	 * @return an Arraylist of tickets, null if an exception was raised.
	 */
	synchronized ArrayList<Ticket> getAllTickets()
	{
		ResultSet rs;
		try{	
			stmt = connection.createStatement();
			String query = "SELECT * FROM ticket";

			rs = stmt.executeQuery(query);

			ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
			while(rs.next())
			{
				Flight f = getFlight(rs.getString("flightid"));
				Ticket ticket = new Ticket(f.getFlightId(), f.getDest(), f.getSrc(), f.getDate(), f.getTime(), f.getDuration(), f.getTotalSeats(), f.getAvailableSeats(), f.getPrice(), rs.getString("ticketid"), rs.getString("firstname"), rs.getString("lastname"));
				ticketList.add(ticket);
			}
			return ticketList;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
