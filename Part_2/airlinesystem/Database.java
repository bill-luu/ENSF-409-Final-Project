package airlinesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	int uniqueFlightId;
	int uniqueTicketId;

	private Connection connection;
	private Statement stmt;
	
	public Database()
	{
		initializeConnection();
		initializeIds();
	}
	
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
				System.out.println(rs.getString("flightid"));
				System.out.println(f);
				System.out.println(f.getFlightId());
				System.out.println(f.getAvailableSeats());
				Ticket ticket = new Ticket(f, rs.getString("ticketid"), rs.getString("firstname"), rs.getString("lastname"));
				if(uniqueTicketId < Integer.parseInt(ticket.getTicketId()))
					uniqueTicketId = Integer.parseInt(ticket.getTicketId());
			}
			uniqueTicketId++;
			System.out.println(uniqueTicketId);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	synchronized ArrayList<Flight> searchFlight(String param, String key)
	{
		ResultSet rs;
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM flights WHERE" + param + " = '" + key + "'";
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

	synchronized void changeSeats(Flight flight, int changeInRemainingSeats)
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
				System.out.println(rs.getString("flightnumber"));
				System.out.println(rs.getString("destlocation"));
				System.out.println(rs.getString("sourcelocation"));
				System.out.println(rs.getString("date"));
				System.out.println(rs.getString("time"));
				System.out.println(rs.getString("duration"));
				System.out.println(rs.getString("totalseats"));
				System.out.println(rs.getString("remainingseats"));
				System.out.println(rs.getString("price"));
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

	synchronized void addTicket(Ticket ticket)
	{
		try{
			stmt = connection.createStatement();
			String prep = "INSERT INTO ticket "
			+ " (ticketid, flightid, firstname, lastname, date, price)"
			+" VALUES(?, ?, ?, ?, ?, ?)"; 

			
			PreparedStatement prepared = connection.prepareStatement(prep);
			prepared.setInt(1, uniqueTicketId++);
			prepared.setInt(2, uniqueFlightId);
			prepared.setString(3, ticket.getFirstName());
			prepared.setString(4, ticket.getLastName());
			prepared.setString(5, ticket.getDate());
			prepared.setString(6, ticket.getPrice());
			prepared.executeUpdate();

			changeSeats(getFlight(ticket.getFlightId()), -1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

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

			return "Flight successfully added.";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return "Flight could not be added.";
		}
	}
	
	synchronized String deleteTicket(String ticketId)
	{
		try
		{
			//Statement 
			stmt = connection.createStatement();
			String query = "DELETE FROM ticket WHERE ticketid= " + Integer.parseInt(ticketId);
			stmt.execute(query);
			Ticket t = getTicket(ticketId);
			changeSeats(getFlight(t.getFlightId()), 1);

			return "Booking removed";
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			return "Unable to remove booking";
		}
	}

	synchronized Ticket getTicket(String ticketId)
	{
		ResultSet rs;
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM ticket WHERE ticketid" + " = '" + Integer.parseInt(ticketId) + "'";
			rs = stmt.executeQuery(query);
			Flight f = getFlight(rs.getString("flightid"));
			Ticket t = new Ticket(f, rs.getString("ticketid"), rs.getString("firstname"), rs.getString("lastname"));
	
			return t;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	synchronized void deleteFlight(String flightId)
	{
		try
		{
			//Statement 
			stmt = connection.createStatement();
			String query = "DELETE FROM flgihts WHERE flightnumber= " + Integer.parseInt(flightId);
			stmt.execute(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
	synchronized ArrayList<Ticket> searchTicket(String param, String key)
	{
		ResultSet rs;
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM ticket WHERE" + param + " = '" + key + "'";
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
