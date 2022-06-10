package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.nio.DoubleBuffer;

import javax.swing.JPanel;

public class gamePanel extends JPanel implements Runnable{
	
	//screen settings
	
	final int originalTileSize =16;
	final int scale =3;
	final int tilesize = originalTileSize * scale; //48x48
	
	final int maxScreenCol =16;
	final int maxScreenRow=12;
	final int screenWidth =tilesize*maxScreenCol; //768 px
	final int screenHeight= tilesize*maxScreenRow;//576 px
	
	int FPS =60;
	
	
	KeyHandler keyH =new KeyHandler();
	Thread gameThread;
	
	
	//set player default position
	
	int playerX = 100;
	int playerY =100;
	int playerSpeed=4;
	
	
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
		
		if(keyH.upPressed==true) {
			playerY-=playerSpeed;
		}
		else if (keyH.downPressed==true) {
			playerY+=playerSpeed;
		}
		else if(keyH.leftPressed==true) {
			playerX-=playerSpeed;
		}
		else if (keyH.rightPressed==true) {
			playerX+=playerSpeed;
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		g2.setColor(Color.WHITE);
		
		g2.fillRect(playerX, playerY, tilesize, tilesize);
		g2.dispose();
		
		
		
	}
}