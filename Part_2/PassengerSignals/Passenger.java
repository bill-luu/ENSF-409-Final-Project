import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;

public class Passenger {
    private PrintWriter ticketWriter;
    private PrintWriter socketOut;
    private Socket socket;
    private BufferedReader socketIn;
    private OutputFileStream outputStream;
    private InputFileStream inputStream;


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

        try {
            socketOut.println(param);
            socketOut.println(key);
            response = socketIn.readLine();

            if (!response.equals("nofind")) {
                return response;
            } else {
                return "The flight you were looking for could not be found";
            }
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
        return "There's been an error in code, please fix it";
    }

    public void getFlights() {
        String response = "";

        try {
            socketOut.println("getflights");

            while (!response.equals("end")) {
                response = socketIn.readLine();
            }
        }
        catch (IOException e){
            System.err.println(e.getStackTrace());
        }
    }

    void bookTicket(String nameA, String flightNumber) {
        String response = "";

        try {
            socketOut.println("bookflight");
            socketOut.println(flightNumber);
            response = socketIn.readLine();

            if (!response.equals("allbooked")) {
                System.out.println("Booked Succesfully!");
            }
        }
        catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }


    public void printTicket(Ticket ticketA) {
        String ticketFirstName, ticketLastName, ticketFlightNumber, ticketSource, ticketDestination, ticketDate, ticketTime, ticketDuration, ticketPrice;

        ticketWriter = new PrintWriter(ticketFlightNumber + "ticket.txt");
        ticketWriter.printf("%5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s", "First Name", "Last Name", "Flight #", "Starting Destionation", "Final Destination", "Date of Departure", "Time of Departure", "Duration of Flight", "Total Price of FLight" );
        ticketWriter.printf("%5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s, %5s", "First Name", "Last Name", "Flight #", "Starting Destionation", "Final Destination", "Date of Departure", "Time of Departure", "Duration of Flight", "Total Price of FLight" ); //To be changed soon
        ticketWriter.close();
    }

    public boolean checkFormatFlight() {
        return true;
    }
}
