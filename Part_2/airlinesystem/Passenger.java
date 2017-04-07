package airlinesystem;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Passenger {
    protected PrintWriter ticketWriter;
//    protected PrintWriter socketOut;
    protected Socket socket;
//    protected BufferedReader socketIn;
    protected ObjectOutputStream outputStream;
    protected ObjectInputStream inputStream;
    public ArrayList<Flight> receivedFlights;


    public Passenger(String serverName, int portNumber) {
        try {
            socket = new Socket(serverName, portNumber);
            System.out.println("Trying to connect to output");
			outputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Trying to connect to Input");
            inputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
        	System.out.println("Something went wrong.");
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
            outputStream.writeObject((String)"SEARCHFLIGHT_" + param + "_" + key);
            response = (String)inputStream.readObject(); 
            if (response.equals("GOOD")) {
               // inputStream = new ObjectInputStream(socket.getInputStream());
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
            outputStream.writeObject((String)"GETFLIGHTS_");
            response = (String)inputStream.readObject();
            if (response.equals("GOOD")) {
                //inputStream = new ObjectInputStream(socket.getInputStream());
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
            outputStream.writeObject((String)"BOOK_" + flightNumber + "_" + firstName + "_" + lastName);
            response = (String)inputStream.readObject();
            if (response.equals("GOOD")) {
              //  inputStream = new ObjectInputStream(socket.getInputStream());
            	Thread.sleep(10);
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
        catch(Exception e)
        {
        	e.printStackTrace();
        	return "An exception occured";
        }
    }

    public void printTicket(Ticket ticketA) {

        try {
            ticketWriter = new PrintWriter("Flight" + ticketA.getFlightId() + "-ticket.txt");
            ticketWriter.println("Your Ticket Information")
            ticketWriter.println("Ticket #: " + ticketA.getFlightId()); 
            ticketWriter.println("First Name: " + ticketA.getFirstName());
            ticketWriter.println("Last Name: " + ticketA.getLastName());
            ticketWriter.println("Flight #: ") + ticketA.getFlightId());
			ticketWriter.println("Starting Destination: " + ticketA.getSrc());
			ticketWriter.println("Final Destination: " + ticketA.getDest());
			ticketWriter.println("Date of Departure: " + ticketA.getDate());
			ticketWriter.println("Time of Departure: ") + ticketA.getTime());
			ticketWriter.println("Duration of Flight: " + ticketA.getDuration());
			ticketWriter.println("Price of Flight (+ tax): " + ticketA.getTaxedPrice());
            ticketWriter.println("Thank you for choosing Air Yeezy");
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
    		if(!isNum(key) || key.length() > 4)
    			toReturn = "Search field format error, please ensure field is an integer of 4 digits or less";
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

    public void quitServer()
    {
        try{
            outputStream.writeObject((String)"QUIT_");
            socket.close();
//            socketIn.close();
//            socketOut.close();
            outputStream.close();
            inputStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
