import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Passenger {
    protected PrintWriter ticketWriter;
    protected PrintWriter socketOut;
    protected Socket socket;
    protected BufferedReader socketIn;
    protected ObjectOutputStream outputStream;
    protected ObjectInputStream inputStream;
    public ArrayList<Flight> receivedFlights;


    public Passenger(String serverName, int portNumber) {
        try {
            socket = new Socket(serverName, portNumber);
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    /**
     *
     * @param param The paramater being searched for
     * @param key The key used for the search
     * @return The object searched for
     */
    public String searchFlights(String param, String key) {
        String response = "";
        String format = checkFormatFlightSearch(param, key);
        if(format.contains("error"))
        	return format;
        try {
            socketOut.println("SEARCHFLIGHT_" + param + "_" + key);
            response = socketIn.readLine();
            if (!response.equals("GOOD")) {
                inputStream = new ObjectInputStream(socket.getInputStream());
                receivedFlights = (ArrayList<Flight>)inputStream.readObject();
                return "GOOD";
            }
            else {
                return "The flight you were looking for could not be found";
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
    }

    public String getFlights() {
        String response = "";
        try {
            socketOut.println("GETFLIGHTS_");
            response = socketIn.readLine();
            if (!response.equals("GOOD")) {
                inputStream = new ObjectInputStream(socket.getInputStream());
                receivedFlights = (ArrayList<Flight>)inputStream.readObject();
                return "GOOD";
            }
            else {
                return "No flights could be found";
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
    }

    public String bookTicket(String firstName, String lastName, String flightNumber) {
        String response = "";
        String format = checkFormatTicketAdd(firstName, lastName);
        if(format.contains("ERROR"))
        	return format;
        try {
            socketOut.println("BOOK_" + firstName + lastName + flightNumber);
            response = socketIn.readLine();
            if (!response.equals("GOOD")) {
                inputStream = new ObjectInputStream(socket.getInputStream());
                Ticket printedTicket = (Ticket)inputStream.readObject();
                printTicket(printedTicket);
                return "Ticket Successfully Booked!";
            }
            else {
                return "Error booking ticket, please refresh to ensure seats are available";
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
    }

    public void printTicket(Ticket ticketA) {

        try {
            ticketWriter = new PrintWriter("Flight" + ticketA.getFlightId() + "-ticket.txt");
            ticketWriter.printf("%5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s", "Ticket #", "First Name", "Last Name", "Flight #", "Starting Destionation", "Final Destination", "Date of Departure", "Time of Departure", "Duration of Flight", "Total Price of FLight");
            ticketWriter.printf("%5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s", ticketA.getTicketId(), ticketA.getFirstName(), ticketA.getLastName(), ticketA.getFlightId(), ticketA.getSrc(), ticketA.getDest(), ticketA.getDate(), ticketA.getTime(), ticketA.getDuration(), ticketA.getTaxedPrice());
            ticketWriter.close();
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getStackTrace());
        }
    }
    
    public boolean isNum(String toTest){
		 try { 
		        Integer.parseInt(toTest); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    }
		    return true;
	}

    public String checkFormatFlightSearch(String param, String key){
    	String toReturn = "";
    	switch (param){
    	case "flightId":
    		if(!isNum(key) || key.length() > 6)
    			toReturn = "Search field format error, please ensure field is an integer of 6 digits or less";
    		break;
    	case "destination":
    		if(key.length() > 45 || key.contains("_"))
    			toReturn = "Search field format error, please ensure field does not contain underscores or exceed 45 characters";
    		break;
    	case "source":
    		if(key.length() > 45 || key.contains("_"))
    			toReturn = "Search field format error, please ensure field does not contain underscores or exceed 45 characters";
    		break;
    	default:
    		toReturn = "Unrecognized Parameter error...";
    		break;
    	}
    	return toReturn;	
    }

    public String checkFormatTicketAdd(String firstName, String lastName) {
        String toReturn="";
		boolean badFormat = false;
		;
		if(firstName.length() > 45 || firstName.isEmpty() || firstName.contains("_")){
			badFormat = true;
			toReturn += "First Name format Error, please ensure field is not empty, does not contain underscores and does not exceed 45 characters_";
		}
		
		if(lastName.length() > 45 || lastName.isEmpty() || lastName.contains("_")){
			badFormat = true;
			toReturn += "Last Name format Error, please ensure field is not empty, does not contain underscores and does not exceed 45 characters_";
		}
		
		if(badFormat){
			return "ERROR_" + toReturn;
		}
		return "GOOD FORMAT";
    }
}
