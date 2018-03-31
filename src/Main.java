import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;


public class Main {
	
	public static final int WORLD_SEED = (int) (Math.random() * 100000);
	public static volatile ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	public static GraphicsPanel gp;
	public static volatile ClientHandler lightAuthority;

	public static void main(String argv[]) throws Exception {

		  ServerSocket welcomeSocket = new ServerSocket(4242);
		  new Display();

		  while (true) {
		   Socket connectionSocket = welcomeSocket.accept();
		   connectionSocket.getOutputStream().write((WORLD_SEED + "\n").getBytes());
		   ClientHandler ch = new ClientHandler(connectionSocket.getInputStream(), connectionSocket.getOutputStream());
		   System.out.println("New client");
		   clients.add(ch);
		   Thread t = new Thread(ch);
		   t.start();
		  }
		 }

}
