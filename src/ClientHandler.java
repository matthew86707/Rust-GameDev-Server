import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Vector;


public class ClientHandler implements Runnable{
	
	double position_x = 0.0;
	double position_y = 0.0;
	double position_z = 0.0;
	
	InputStream clientInputStream;
	OutputStream clientOutputStream;
	
	public ClientHandler(InputStream is, OutputStream os) {
		this.clientInputStream = is;
		this.clientOutputStream = os;
	}

	@Override
	public void run() {
		while(true){
		try {
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientInputStream));
		DataOutputStream outToClient = new DataOutputStream(clientOutputStream);
		
		String clientData = inFromClient.readLine();
		
		System.out.println(clientData + " With " + Main.clients.size() + " connected.");
		System.out.flush();
		
		String[] pos = clientData.split(":");
		position_x = Double.parseDouble(pos[0]);
		position_y = Double.parseDouble(pos[1]);
		position_z = Double.parseDouble(pos[2]);
		
		StringBuilder playerPositions = new StringBuilder();
		
		for(int i = 0; i < Main.clients.size(); i++){
			ClientHandler ch = Main.clients.get(i);
			if(ch != this){
				playerPositions.append(i);
				playerPositions.append(":");
				playerPositions.append(ch.position_x);
				playerPositions.append(":");
				playerPositions.append(ch.position_y);
				playerPositions.append(":");
				playerPositions.append(ch.position_z);
			}
		}

		outToClient.writeBytes(playerPositions.toString() + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

}
