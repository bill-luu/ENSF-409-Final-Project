import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	Database database;
	ServerSocket serverSocket;
	public Server()
	{
		database = new Database();
		try {
			serverSocket = new ServerSocket(9090);
			System.out.println("Server is now running");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
