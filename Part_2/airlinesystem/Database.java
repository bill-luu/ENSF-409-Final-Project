package airlinesystem;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	int flightIds;
	int ticketIds;

	private Connection connection;
	private Statement stmt;
	private ResultSet rs;
	
	public Database()
	{
		initializeConnection();
	}
	
	public void initializeConnection()
	{
		try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedatabase", "root", "PASSWORD");
			} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
			}
	}
	
	synchronized String addFlight(Flight flight)
	{
		try{	
			stmt = connection.createStatement();
			String prep = "INSERT INTO flights "
			+ " (flightnumber, destlocation, sourcelocation, date, time, duration, totalseats, remainingseats, price)"
			+" VALUES(?, ?, ?, ?, ?, ?)"; 

			PreparedStatement prepared = connection.prepareStatement(prep);
			prepared.setInt(1, Integer.parseInt(flight.getId()));
			prepared.setString(2, flight.getSourceLocation);
			prepared.setString(3, flight.getDestLocation());
			prepared.setString(4, flight.getDate());
			prepared.setString(5, flight.getTime());
			prepared.setString(6, flight.getDuration());
			prepared.setInt(7, Integer.parseInt(flight.getTotalSeats()));
			prepared.setInt(8, Integer.parseInt(flight.getRemainingSeats()));
			prepared.setString(9, flight.getPrice());
			prepared.executeUpdate();

			return "Flight successfully booked.";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return "Flight could not be booked.";
		}
	}
	
	synchronized Flight getFlight(String flightId)
	{
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM flights WHERE flightnumber" + " = '" + Integer.parseInt(flightId) + "'";
			rs = stmt.executeQuery(query);

			Flight flight = new Flight(rs.getString("flightnumber"), rs.getString("destlocation"),
									   rs.getString("sourcelocation"), rs.getString("date"),
									   rs.getString("time"), rs.getString("duration"),
									   rs.getString("totalseats"), rs.getString("remainingseats"),
									   rs.getString("price")) ;
			return flight;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	synchronized ArrayList<Flight> searchFlight(String param, String key)
	{
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM flights WHERE" + param + " = '" + key + "'";
			rs = stmt.executeQuery(query);
			ArrayList<Flight> flightList = new ArrayList();
			
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
	
	synchronized ArrayList<Flight> searchFlight()
	{
		try{	
			stmt = connection.createStatement();
			String query = "SELECT * FROM flights";

			rs = stmt.executeQuery(query);

			ArrayList<Flight> flightList = new ArrayList();
			
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
	
	synchronized ArrayList<Ticket> searchTicket(String param, String key)
	{
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM ticket WHERE" + param + " = '" + key + "'";
			rs = stmt.executeQuery(query);

			ArrayList<Ticket> ticketList = new ArrayList();
			while(rs.next())
			{
				Ticket ticket = new Ticket(rs.getString("ticketid"), rs.getString("flightid"),
										   rs.getString("firstname"), rs.getString("lastname"),
										   rs.getString("date"), rs.getString("price"));
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
	
	synchronized ArrayList<Ticket> searchTicket()
	{
		try{	
			stmt = connection.createStatement();
			String query = "SELECT * FROM ticket";

			rs = stmt.executeQuery(query);

			ArrayList<Ticket> ticketList = new ArrayList();
			while(rs.next())
			{
				Ticket ticket = new Ticket(rs.getString("ticketid"), rs.getString("flightid"),
										   rs.getString("firstname"), rs.getString("lastname"),
										   rs.getString("date"), rs.getString("price"));
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
	
	synchronized void addTicket(Ticket ticket)
	{
		try{	//Probably doesn't need to be synchronized
			stmt = connection.createStatement();
			String prep = "INSERT INTO ticket "
			+ " (ticketid, flightid, firstname, lastname, date, price)"
			+" VALUES(?, ?, ?, ?, ?, ?)"; 

			PreparedStatement prepared = connection.prepareStatement(prep);
			prepared.setInt(1, ticketIds++);
			prepared.setInt(2, flightIds++);
			prepared.setString(3, ticket.getFirstName());
			prepared.setString(4, ticket.getLastName());
			prepared.setString(5, ticket.getDate());
			prepared.setString(6, ticket.getPrice());
			prepared.executeUpdate();

			//TODO, update available seats on plane
		}
		catch(SQLException e)
		{
			e.printStackTrace();
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
			//TODO: Increment avaliable seats in Plane

			return "Booking removed";
		}
		catch(SQLException err)
		{
			err.printStackTrace();
			return "Unable to remove booking";
		}
	}
}
