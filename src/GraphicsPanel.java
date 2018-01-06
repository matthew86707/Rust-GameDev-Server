import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.management.timer.TimerMBean;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel{
	
	 private Timer timer = new Timer();
	 
	 public GraphicsPanel(){
		 timer.schedule(new UpdateDisplayTask(), 500);
	 }
	
	public void paintComponent(Graphics g) 
	{
		g.setColor(Color.BLACK);
		g.drawString("WORLD SEED : " + Main.WORLD_SEED, 10, 15);
		g.setColor(Color.GREEN);
		for(ClientHandler player : Main.clients){
		g.drawRect((int)(400 + (player.position_x * 2.0)), 300 + (int)(player.position_z * 2.0), 10, 10);
		}
	}
	
	private class UpdateDisplayTask extends TimerTask {
        @Override
        public void run() {
        	repaint();
            timer.schedule(new UpdateDisplayTask(), 500);
        }
    }

}
