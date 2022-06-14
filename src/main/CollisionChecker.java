package main;

import entity.Entity;

public class CollisionChecker {
	
	gamePanel gPanel;
	
	public CollisionChecker(gamePanel gPanel) {
		this.gPanel = gPanel;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		
		int entityLeftCol = entityLeftWorldX/gPanel.tilesize;
		int entityRightCol = entityRightWorldX/gPanel.tilesize;
		int entityTopRow = entityTopWorldY/gPanel.tilesize;
		int entityBottomRow = entityBottomWorldY/gPanel.tilesize;
		
		int tilenum1,tilenum2;
		
		
		switch (entity.direction){
		case "up": {
			entityTopRow =(entityTopWorldY-entity.speed)/gPanel.tilesize;
			tilenum1 =gPanel.tileM.MaptileNum[entityLeftCol][entityTopRow];
			tilenum2 =gPanel.tileM.MaptileNum[entityRightCol][entityTopRow];
			
			if(gPanel.tileM.tile[tilenum1].collision == true || gPanel.tileM.tile[tilenum2].collision == true ) {
				entity.collisionOn = true;
			}
			
			break;
		}
		case "down": {
			entityTopRow =(entityTopWorldY + entity.speed)/gPanel.tilesize;
			tilenum1 =gPanel.tileM.MaptileNum[entityLeftCol][entityBottomRow];
			tilenum2 =gPanel.tileM.MaptileNum[entityRightCol][entityBottomRow];
			
			if(gPanel.tileM.tile[tilenum1].collision == true || gPanel.tileM.tile[tilenum2].collision == true ) {
				entity.collisionOn = true;
			}
			
			break;
		}
		case "left": {
			entityLeftCol =(entityLeftWorldX-entity.speed)/gPanel.tilesize;
			tilenum1 =gPanel.tileM.MaptileNum[entityLeftCol][entityTopRow];
			tilenum2 =gPanel.tileM.MaptileNum[entityLeftCol][entityBottomRow];
			
			if(gPanel.tileM.tile[tilenum1].collision == true || gPanel.tileM.tile[tilenum2].collision == true ) {
				entity.collisionOn = true;
			}
			
			break;
		}
		case "right": {
			entityRightCol =(entityRightWorldX + entity.speed)/gPanel.tilesize;
			tilenum1 =gPanel.tileM.MaptileNum[entityRightCol][entityTopRow];
			tilenum2 =gPanel.tileM.MaptileNum[entityRightCol][entityBottomRow];
			
			if(gPanel.tileM.tile[tilenum1].collision == true || gPanel.tileM.tile[tilenum2].collision == true ) {
				entity.collisionOn = true;
			}
			
			break;
		}

		
		
	}

	}
}
