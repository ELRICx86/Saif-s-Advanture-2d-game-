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
		
		MaptileNum =new int[gPanel.worldCol][gPanel.worldRow];
		getTileimage();
		loadmap("/maps/world01.txt");
	}
	
	public void getTileimage() {
		
		try {
			tile[0]=new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1]=new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2]=new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tile[3]=new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4]=new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			
			tile[5]=new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
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
			
			while(col<gPanel.worldCol && row < gPanel.worldRow) {
				String line= br.readLine();
				while(col<gPanel.worldCol ) {
					String[] number = line.split(" ");
					int num =Integer.parseInt(number[col]);
					
					MaptileNum[col][row]=num;
					col++;
				}
				if(col==gPanel.worldCol) {
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
		
		int wrow =0;
		int wcol =0;
		
		
		
		
		while(wcol<gPanel.worldCol && wrow <gPanel.worldRow) {
			
			int tilenum=MaptileNum[wcol][wrow];
			
			int worldX=wcol * gPanel.tilesize;
			int worldY=wrow * gPanel.tilesize;
			int screenX=worldX - gPanel.player.worldX +gPanel.player.screenX;
			int screenY=worldY - gPanel.player.worldY +gPanel.player.screenY;
			
			
			if(worldX + gPanel.tilesize >gPanel.player.worldX - gPanel.player.screenX &&
					worldX - gPanel.tilesize < gPanel.player.worldX + gPanel.player.screenX &&
					worldY + gPanel.tilesize> gPanel.player.worldY - gPanel.player.screenY &&
					worldY - gPanel.tilesize< gPanel.player.worldY + gPanel.player.screenY ) {
			g2.drawImage(tile[tilenum].image, screenX, screenY, gPanel.tilesize, gPanel.tilesize, null);
			}
			wcol++;
		
			
			if(wcol==gPanel.worldCol) {
				wcol=0;
				wrow++;
			}
			
		}
		
		
	}

}
