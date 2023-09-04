package org.blue.boy;

import org.blue.boy.entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftWorldCol = entityLeftWorldX / gp.tileSize;
        int entityRightWorldCol = entityRightWorldX / gp.tileSize;
        int entityTopWorldRow = entityTopWorldY / gp.tileSize;
        int entityBottomWorldRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                tileNum1 = gp.tileManager.mapNum[entityTopWorldRow][entityLeftWorldCol];
                tileNum2 = gp.tileManager.mapNum[entityTopWorldRow][entityRightWorldCol];
                entity.collisionOn = gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision;
                break;
            case "down":
                tileNum1 = gp.tileManager.mapNum[entityBottomWorldRow][entityLeftWorldCol];
                tileNum2 = gp.tileManager.mapNum[entityBottomWorldRow][entityRightWorldCol];
                entity.collisionOn = gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision;
                break;
            case "left":
                tileNum1 = gp.tileManager.mapNum[entityTopWorldRow][entityLeftWorldCol];
                tileNum2 = gp.tileManager.mapNum[entityBottomWorldRow][entityLeftWorldCol];
                entity.collisionOn = gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision;
                break;
            case "right":
                tileNum1 = gp.tileManager.mapNum[entityTopWorldRow][entityRightWorldCol];
                tileNum2 = gp.tileManager.mapNum[entityTopWorldRow][entityRightWorldCol];
                entity.collisionOn = gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision;
                break;
        }
    }
}
