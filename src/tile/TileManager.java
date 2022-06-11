package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamePanel;

public class TileManager {
	
	gamePanel gPanel;
	Tile[] tile;
	
	public TileManager(gamePanel gPanel) {
		this.gPanel = gPanel;
		tile = new Tile[10];
		getTileimage();
	}
	
	public void getTileimage() {
		
		try {
			tile[0]=new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1]=new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2]=new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		int row =0;
		int col =0;
		
		int x=0;
		int y=0;
		
		while(col<gPanel.maxScreenCol && row <gPanel.maxScreenRow) {
			g2.drawImage(tile[0].image, x, y, gPanel.tilesize, gPanel.tilesize, null);
			col++;
			x+=gPanel.tilesize;
			
			if(col==gPanel.maxScreenCol) {
				col=0;
				x=0;
				row++;
				y+=gPanel.tilesize;
			}
			
		}
		
		
	}

}
