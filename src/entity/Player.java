package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.security.auth.login.FailedLoginException;
import javax.sound.midi.VoiceStatus;

import main.KeyHandler;
import main.gamePanel;

public class Player extends Entity{
	
	gamePanel gPanel;
	KeyHandler kHandler;
	
	public final int screenX,screenY;
	
	public Player(gamePanel gPanel,KeyHandler kHandler) {
		this.gPanel =gPanel;
		this.kHandler=kHandler;
		
		//To make a collision rectangle object object
		solidArea = new Rectangle();
		solidArea.x=8;
		solidArea.y=16;
		solidArea.width=32;
		solidArea.height=32;
		
		
		screenX=(gPanel.screenWidth/2)-gPanel.tilesize/2;
		screenY=(gPanel.screenHeight/2)-gPanel.tilesize/2;
		setdefaultValues();
		getplayerImage();
	}
	
	public void setdefaultValues() {
		worldX=gPanel.tilesize*23;
		worldY=gPanel.tilesize*21;
		speed=4;
		direction="down";
	}
	
	public void getplayerImage() {
		try {
			
			up1 =ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 =ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 =ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 =ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 =ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 =ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 =ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void update() {
		
		if(kHandler.upPressed==true ||kHandler.downPressed==true ||kHandler.leftPressed==true ||kHandler.rightPressed==true) {
			if(kHandler.upPressed==true) {
				direction="up";
				
			}
			else if (kHandler.downPressed==true) {
				direction="down";
				
			}
			else if(kHandler.leftPressed==true) {
				direction="left";
				
			}
			else if (kHandler.rightPressed==true) {
				direction="right";
				
				
			}
			
			//check collision
			collisionOn =false;
			gPanel.cChecker.checkTile(this);
			//if collision false then player can move
			
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":{
					worldY-=speed;
					break;
				}
				case "down":{
					worldY+=speed;
					break;
				}
				case "left":{
					worldX-=speed;
					break;
				}
				case "right":{
					worldX+=speed;
					break;
				}
				}
			}
			
			
			spriteCounter++;
			if(spriteCounter>15) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if(spriteNum==2) {
					spriteNum=1;
				}
				
				spriteCounter=0;
			}
		}
	
	}
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.WHITE);
		
		//g2.fillRect(x, y, gPanel.tilesize, gPanel.tilesize);
		
		BufferedImage image = null;
		
		switch (direction) {
		case "up": {
			
			if(spriteNum ==1) {
			image = up1;
			}
			if(spriteNum ==2) {
			image = up2;
			}
			break;
		}
		case "down": {
			
			if(spriteNum ==1) {
			image = down1;
			}
			if(spriteNum ==2) {
			image = down2;
			}
			break;
		}
		case "left": {
			
			if(spriteNum ==1) {
			image = left1;
			}
			if(spriteNum ==2) {
			image = left2;
			}
			break;
		}
		case "right": {
			
			if(spriteNum ==1) {
			image = right1;
			}
			if(spriteNum ==2) {
			image = right2;
			}
			break;
		}
	}
		
		g2.drawImage(image,screenX,screenY,gPanel.tilesize,gPanel.tilesize,null);
		

}
	
}
