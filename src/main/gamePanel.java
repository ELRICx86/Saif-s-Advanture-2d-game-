package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.nio.DoubleBuffer;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class gamePanel extends JPanel implements Runnable{
	
	//screen settings
	
	final int originalTileSize =16;
	final int scale =3;
	public final int tilesize = originalTileSize * scale; //48x48
	
	public final int maxScreenCol =16;
	public final int maxScreenRow=12;
	public final int screenWidth =tilesize*maxScreenCol; //768 px
	public final int screenHeight= tilesize*maxScreenRow;//576 px
	
	int FPS =60;
	
	
	TileManager tileM= new TileManager(this);
	
	
	KeyHandler keyH =new KeyHandler();
	Thread gameThread;
	
	Player player =new Player(this, keyH);
	
	
	//set player default position
	
	
	
	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startgamethread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta =0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		
		while(gameThread !=null) {
			
			currentTime = System.nanoTime();
			
			delta +=(currentTime-lastTime)/drawInterval;
			//System.out.println("game loop running");
			lastTime =currentTime;
			
			//Update:update information such as character position.
			
			
			if (delta>=1) {
			
			update();
			
			
			//Draw:draw the screen with the updated information.
			repaint();
			delta--;
			}
		}
		
		
		
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);

		g2.dispose();
		
		
		
	}
}
