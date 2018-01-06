import javax.swing.JFrame;


public class Display extends JFrame{
	
	public Display(){
		setSize(800, 600);
		setTitle("Server : Display");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsPanel gp = new GraphicsPanel();
		Main.gp = gp;
		add(gp);
		
	}
}
