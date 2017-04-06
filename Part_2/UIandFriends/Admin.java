package Final_Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Admin {
	
	public String checkFormatFlight(Flight toCheck){
		String toReturn="";
		boolean badFormat = false;
		
		
		String curr = toCheck.getDest();
		if(curr.length() > 20 || curr.isEmpty() || curr.contains("_")){
			badFormat = true;
			String temp = toReturn;
			toReturn = temp + " Destination Location format Error, please ensure field is not empty and does not excede 20 characters_";
		}
		
		curr = toCheck.getSrc();
		if(curr.length() > 20 || curr.isEmpty() || curr.contains("_")){
			badFormat = true;
			String temp = toReturn;
			toReturn = temp + " Departure Location format Error, please ensure field is not empty and does not exceed 20 characters_";
		}
		
		if(!isDate(toCheck.getDate())){
			badFormat = true;
			String temp = toReturn;
			toReturn = temp + " Date format Error, please ensure format follows dd/MM/yyyy and date has not passed_";
		}
		
		curr = toCheck.getTime();
		if(!curr.matches("^([01][0-9]|[2][0-3]):[0-5][0-9]")){
			badFormat = true;
			String temp = toReturn;
			toReturn = temp + " Time format Error, please ensure format follows hh:mm in 24hr time_";
		}
		
		curr = toCheck.getDuration();
		if(!curr.matches("^\\d{3}:([01][0-9]|[2][0-3]):[0-5][0-9]")){
			badFormat = true;
			String temp = toReturn;
			toReturn = temp + " Duration format Error, please ensure format follows dd:hh:mm_";
		}
		
		curr = toCheck.getTotalSeats();
		if(!isNum(curr)){
			badFormat = true;
			String temp = toReturn;
			toReturn = temp + "Total Seats format Error, please ensure entered value is an integer_";
		}
		
		curr = toCheck.getPrice();
		if(!isNum(curr) || curr.length()>19){
			badFormat = true;
			String temp = toReturn;
			toReturn = temp + " Price format Error, please ensure entered value is an integer 19 digits or less_";
		}
		
		if(badFormat){
			String temp = toReturn;
			toReturn = "ERROR_" + temp;
			return toReturn;
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
		    } catch(Exception e) { 
		        return false; 
		    }
		    return true;
	}
	
	public String addFlight(Flight addedFlight){
		String toReturn = checkFormatFlight(addedFlight);
		if(toReturn.contains("ERROR")){
			return toReturn;
		}
		String toSend = "ADDFLIGHT_ + *insert serialized addedFlight here";
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
