package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {
	Socket socketM;
	ServerSocket serverSocketM;
	PrintWriter writer;
	BufferedReader reader;
	int flightIds;
	int ticketIds;

	private Connection connection;
	private Statement stmt;
	private ResultSet rs;
	
	public Server()
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
	
	synchronized void communicate()
	{
		
	}
	
	synchronized void addFlight(Flight flight)
	{
		try{	//Probably doesn't need to be synchronized
			stmt = connection.createStatement();
			String prep = "INSERT INTO flights "
			+ " (flightnumber, sourcelocation, destlocation, date, time, duration, totalseats, remainingseats, price)"
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

			//TODO, write string to socket
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public String searchFlight(String param, String key)
	{
		try{
			stmt = connection.createStatement();
			String query;

			query = "SELECT * FROM flights WHERE" + param + " = '" + key + "'";
			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				//TODO: Send contents of rs to Socket
			}
		}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
		return "";
	}
	
	public String searchFlight()
	{
		try{	
			stmt = connection.createStatement();
			String query = "SELECT * FROM flights";

			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				//TODO: Send contents of RS to Socket
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public void deleteFlight(String flightId)
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
			prepared.setInt(1, Integer.parseInt(ticket.getId()));
			prepared.setInt(2, Integer.parseInt(ticket.getFlightId()));
			prepared.setString(3, ticket.getFirstName());
			prepared.setString(4, ticket.getLastName());
			prepared.setString(5, ticket.getDate());
			prepared.setString(6, ticket.getPrice());
			prepared.executeUpdate();

			//TODO, write string to socket
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteTicket(String ticketId)
	{
		try
		{
			//Statement 
			stmt = connection.createStatement();
			String query = "DELETE FROM ticket WHERE ticketid= " + Integer.parseInt(ticketId);
			stmt.execute(query);
		}
		catch(SQLException err)
		{
			err.printStackTrace();
		}
	}
}
