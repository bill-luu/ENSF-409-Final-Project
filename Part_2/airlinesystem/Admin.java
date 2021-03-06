package airlinesystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The admin class, which extends from Passenger. Used to provice a backend
 * to the AdminGUI.
 */
public class Admin extends Passenger {
	public ArrayList<Ticket> receivedTickets;

	/**
	 * Constructor for Admin, based of Passenger
	 * @param serverName The server name/ IP Address
	 * @param portNumber The port number
	 */
	public Admin(String serverName, int portNumber){
		super(serverName, portNumber);
	}

	/**
	 * Used to check the format of a ticket
	 * @param param The parameter used to determine what category the user is searching
	 * @param key The key used to search the database
	 * @return Returns nothing if no error occurs and the error message if it does
	 */
	 public String checkFormatTicketSearch(String param, String key){
	    	String toReturn = "";
	    	switch (param){
	    	case "ticketId":
	    		if(!isNum(key) || key.length() > 6)
	    			toReturn = "Search field format error, please ensure field is an integer of 6 digits or less";
	    		break;
	    	case "flightId":
	    		if(!isNum(key) || key.length() > 4)
	    			toReturn = "Search field format error, please ensure field is an integer of 4 digits or less";
	    		break;
	    	case "lastName":
	    		if(key.length() > 45 || key.contains("_"))
	    			toReturn = "Search field format error, please ensure field does not contain underscores or exceed 45 characters";
	    		break;
	    	default:
	    		toReturn = "Unrecognized Parameter error...";
	    		break;
	    	}
	    	return toReturn;	
	    }

	/**
	 * Used to check a format of a flight
	 * @param flightToCheck The flight to check
	 * @return Returns "GOOD FORMAT" if format is good or an error message if they happen
	 */
	public String checkFormatFlightAdd(Flight flightToCheck){
		String toReturn="";
		boolean badFormat = false;
		
		String current = flightToCheck.getDest();
		if(current.length() > 45 || current.isEmpty() || current.contains("_")){
			badFormat = true;
			toReturn += "Destination Location format Error, does not contain underscores and does not exceed 45 characters_";
		}
		
		current = flightToCheck.getSrc();
		if(current.length() > 45 || current.isEmpty() || current.contains("_")){
			badFormat = true;
			toReturn += "Departure Location format Error, does not contain underscores and does not exceed 45 characters_";
		}
		
		if(!isValidDate(flightToCheck.getDate())){
			badFormat = true;
			toReturn += "Date format Error, please ensure format follows dd/MM/yyyy , the date is valid and date has not passed_";
		}
		
		current = flightToCheck.getTime();
		if(!current.matches("^([01][0-9]|[2][0-3]):[0-5][0-9]")){
			badFormat = true;
			toReturn += "Time format Error, please ensure format follows hh:mm in 24hr time_";
		}
		
		current = flightToCheck.getDuration();
		if(!current.matches("^\\d{3}:([01][0-9]|[2][0-3]):[0-5][0-9]")){
			badFormat = true;
			toReturn += "Duration format Error, please ensure format follows ddd:hh:mm in 24hr time_";
		}
		
		current = flightToCheck.getTotalSeats();
		if(!isNum(current)){
			badFormat = true;
			toReturn += "Total Seats format Error, please ensure entered value is an integer_";
		}
		
		current = flightToCheck.getPrice();
		if(!isNum(current) || current.length()>19){
			badFormat = true;
			toReturn += "Price format Error, please ensure entered value is an integer 19 digits or less";
		}
		
		if(badFormat){
			return "ERROR_" + toReturn;
		}
		return "GOOD FORMAT";
	}

	/**
	 * Funnctionn to search tickets
	 * @param param The parameter/category searched for
	 * @param key The key searched for
	 * @return
	 */
	 public String searchTickets(String param, String key) {
	        String response = "";
	        String format = checkFormatTicketSearch(param, key);
	        if(format.contains("error"))
	        	return format;
	        try {
	           outputStream.writeObject((String)"SEARCHTICKET_" + param + "_" + key);
	            response = (String)inputStream.readObject();
	            if (response.equals("GOOD")) {
	            	Thread.sleep(100);
	               // inputStream = new ObjectInputStream(socket.getInputStream());
	                receivedTickets = (ArrayList<Ticket>) inputStream.readObject();
	                System.out.println(receivedTickets.size());
	                return "GOOD";
	            }
	            else {
	                return "The ticket you were looking for could not be found";
	            }
	        }
	        catch (IOException e) {
	            System.err.println(e.getStackTrace());
	            return "An IO Exception occured...";
	        }
			catch (ClassNotFoundException e) {
				System.err.println(e.getStackTrace());
				return "A ClassNotFound Exception occured";
			}
	        catch(Exception e)
	        {
	        	e.getStackTrace();
	        	return "Another exception occured";
	        }
	    }

	/**
	 * Used to refresh to get all the tickets
	 * @return GOOD if tickets exist, an error message if not
	 */
	public String getTickets() {
	        String response = "";

	        try {
	           outputStream.writeObject((String)"GETTICKETS_");
	           Thread.sleep(10);
	            response = (String)inputStream.readObject();
	            if (response.equals("GOOD")) {
	                //inputStream = new ObjectInputStream(socket.getInputStream());
	                receivedTickets = (ArrayList<Ticket>) inputStream.readObject();
	                if(!receivedTickets.isEmpty())
	                	return "GOOD";
	                else
	                	return "No tickets could be found";
	            }
	            else {
	                return "Could not get tickets";
	            }
	        }
	        catch (IOException e) {
	            System.err.println(e.getStackTrace());
	            return "An IO Exception occured...";
	        }
			catch (ClassNotFoundException e) {
				System.err.println(e.getStackTrace());
				return "A ClassNotFound Exception occured";
			}
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        	return "Other error occured";
	        }
	    }

	/**
	 * Function used to add a flight
	 * @param flightToAdd A flight based on admin-inputted information
	 * @return An all good if the function goes without errors or an error if found
	 */
	public String addFlight(Flight flightToAdd){
		String toReturn = checkFormatFlightAdd(flightToAdd);
		if(toReturn.contains("ERROR"))
			return toReturn;
		String response = "";
		 try {
	           outputStream.writeObject((String)"ADDFLIGHT_");
	          //  outputStream = new ObjectOutputStream(socket.getOutputStream());
	           Thread.sleep(500);
	            outputStream.writeObject(flightToAdd);
	            response = (String)inputStream.readObject();
	            return response;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "An IO Exception occured...";
	        }
	}

	/**
	 * Deletes a ticket in the database
	 * @param ticketNumber The ticket number of the ticket to be deleted
	 * @return An all good if the function goes without errors or an error if found
	 */
	public String deleteTicket(String ticketNumber){
		String response = "";
		try{
			outputStream.writeObject((String)"DELETE_" + ticketNumber);
			response = (String)inputStream.readObject();
			return response;
		} catch (Exception e) {
            System.err.println(e.getStackTrace());
            return "An IO Exception occured...";  
		}
	}

	/**
	 * Loads a flight from a textfile
	 * @param fileName The name of the file
	 * @return An all good if the function goes without errors or an error if found
	 */
	public String loadFlights(String fileName){
		String response;
		try{
			BufferedReader br = new BufferedReader (new FileReader(fileName));
			System.out.println("File exists.");
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				String[] str = line.split("		");
				Flight flightToAdd = new Flight(str[0], str[1], str[2], str[3], str[4], str[5], str[6]);
				response = addFlight(flightToAdd);
				if(!response.equals("Flight Added Successfully"))
					return "The following error occured while loading flights into database: " + response;
				line = br.readLine();
			} 
			br.close();
		}catch(Exception e){
			return "File does not exist, or is improperly formatted";
		}	
		return "Flights loaded from file.";
	}
}
