package airlinesystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server connects to each client and assigns them a thread to run.
 * @author Bill Luu, Mark Ricalde, Travis Manchee
 *
 */
public class Server {
	/**
	 * The database of the program
	 */
	Database database;
	/**
	 * The serversocket to establish a conenction.
	 */
	ServerSocket serverSocket;
	/**
	 * Creates the database, and starts up the server.
	 */
	public Server()
	{
		database = new Database();
		try {
			serverSocket = new ServerSocket(9090);
			System.out.println("Server is now running");
			communicate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Communicates with the client, and creates them a thread when they connect.
	 */
	public void communicate()
	{
		try{
			while(true)
			{
				Socket s = serverSocket.accept();
				Runnable r = new AirlineThread(s, database);
				Thread t = new Thread(r);
				t.start();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Server server = new Server();
	}

}
