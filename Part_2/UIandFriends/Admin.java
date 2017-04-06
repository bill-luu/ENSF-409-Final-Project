package Final_Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Admin extends Passenger {
	public ArrayList<Ticket> tickets;
	
	public Admin(String serverName, int portNumber){
		super(serverName, portNumber);
	}
	
	public String checkFormatFlight(Flight flightToCheck){
		String toReturn="";
		boolean badFormat = false;
		
		String curr = flightToCheck.getDest();
		if(curr.length() > 20 || curr.isEmpty() || curr.contains("_")){
			badFormat = true;
			toReturn += "Destination Location format Error, please ensure field is not empty and does not excede 20 characters_";
		}
		
		curr = flightToCheck.getSrc();
		if(curr.length() > 20 || curr.isEmpty() || curr.contains("_")){
			badFormat = true;
			toReturn += "Departure Location format Error, please ensure field is not empty and does not exceed 20 characters_";
		}
		
		if(!isDate(flightToCheck.getDate())){
			badFormat = true;
			toReturn += "Date format Error, please ensure format follows dd/MM/yyyy , the date is valid and date has not passed_";
		}
		
		curr = flightToCheck.getTime();
		if(!curr.matches("^([01][0-9]|[2][0-3]):[0-5][0-9]")){
			badFormat = true;
			toReturn += "Time format Error, please ensure format follows hh:mm in 24hr time_";
		}
		
		curr = flightToCheck.getDuration();
		if(!curr.matches("^\\d{3}:([01][0-9]|[2][0-3]):[0-5][0-9]")){
			badFormat = true;
			toReturn += "Duration format Error, please ensure format follows ddd:hh:mm in 24hr time_";
		}
		
		curr = flightToCheck.getTotalSeats();
		if(!isNum(curr)){
			badFormat = true;
			toReturn += "Total Seats format Error, please ensure entered value is an integer_";
		}
		
		curr = flightToCheck.getPrice();
		if(!isNum(curr) || curr.length()>19){
			badFormat = true;
			toReturn += "Price format Error, please ensure entered value is an integer 19 digits or less";
		}
		
		if(badFormat){
			return "ERROR_" + toReturn;
		}
		return "GOOD FORMAT";
	}
	
	public boolean isDate(String date){
		//code obtained and edited from http://stackoverflow.com/questions/15491894/regex-to-validate-date-format-dd-mm-yyyy
		if(!date.matches("^(?=\\d{2}([-.,\\/])\\d{2}\\1\\d{4}$)(?:0[1-9]|1\\d|[2][0-8]|29(?!.02.(?!(?!(?:[02468][1-35-79]|[13579]"
				+ "[0-13-57-9])00)\\d{2}(?:[02468][048]|[13579][26])))|30(?!.02)|31(?=.(?:0[13578]|10|12))).(?:0[1-9]|1[012]).\\d{4}$"))
			return false;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		try{
			Date flightDate = format.parse(date);
			if(!flightDate.after(currentDate))
				return false;
		}catch (Exception e){
			return false;
		}
		return true;
	}
	
	public boolean isNum(String toTest){
		 try { 
		        Integer.parseInt(toTest); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    }
		    return true;
	}
	
	public String getTickets(){
		//socketOut.println("GETTICKETS_");
		//
		return "";
	}
	
	public String searchTickets(String param, String key){
		//socketOut.println("SEARCHTICKETS_" + param + key);
		return "";
	}
	
	public String addFlight(Flight flightToAdd){
		String toReturn = checkFormatFlight(flightToAdd);
		if(toReturn.contains("ERROR")){
			return toReturn;
		}
		String toSend = "ADDFLIGHT_ + insert serialized flightToAdd here";
		//socketOut.println(toSend);
		//toReturn = 
		//return message will either be "flight successfully added", "An error occurred adding flight", or "Flight ID unavailable"
		return toReturn;
	}
	
	public String deleteTicket(String ticketNumber){
		String toSend = "DELETE_" + ticketNumber;
		//send to send and post return message into "toReturn";
		//socketOut.println(toSend);
		//
		String toReturn = "messaged returned from server";//return message will either be "ticket successfully deleted" or "An error occurred deleting ticket"
		return toReturn;
	}
	
	public String loadFlights(String fileName){
		try{
			BufferedReader br = new BufferedReader (new FileReader(fileName));
			String line = br.readLine();
			while (line != null) {
				String[] str = line.split("_");
				Flight flightToAdd = new Flight(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8]);
				addFlight(flightToAdd);
				line = br.readLine();
			} 
			br.close();
		}catch(Exception e){
			return "File does not exist, or is improperly formatted";
		}	
		return "Flights loaded from file.";
	}
}
