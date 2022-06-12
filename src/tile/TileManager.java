package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.gamePanel;

public class TileManager {
	
	gamePanel gPanel;
	Tile[] tile;
	
	int MaptileNum[][];
	
	public TileManager(gamePanel gPanel) {
		this.gPanel = gPanel;
		tile = new Tile[10];
		
		MaptileNum =new int[gPanel.maxScreenCol][gPanel.maxScreenRow];
		getTileimage();
		loadmap("/maps/map01.txt");
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
	
	public void loadmap(String mapPath) {
		try {
			InputStream iStream = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
			
			int col=0;
			int row =0;
			
			while(col<gPanel.maxScreenCol && row < gPanel.maxScreenRow) {
				String line= br.readLine();
				while(col<gPanel.maxScreenCol ) {
					String[] number = line.split(" ");
					int num =Integer.parseInt(number[col]);
					
					MaptileNum[col][row]=num;
					col++;
				}
				if(col==gPanel.maxScreenCol) {
					col=0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int row =0;
		int col =0;
		
		int x=0;
		int y=0;
		
		while(col<gPanel.maxScreenCol && row <gPanel.maxScreenRow) {
			
			int tilenum=MaptileNum[col][row];
			g2.drawImage(tile[tilenum].image, x, y, gPanel.tilesize, gPanel.tilesize, null);
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
