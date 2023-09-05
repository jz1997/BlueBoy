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

        switch (entity.direction) {
            case "up":
                entity.collisionOn = checkCollision(entityTopWorldRow, entityLeftWorldCol) || checkCollision(entityTopWorldRow, entityRightWorldCol);
                break;
            case "down":
                entity.collisionOn = checkCollision(entityBottomWorldRow, entityLeftWorldCol) || checkCollision(entityBottomWorldRow, entityRightWorldCol);
                break;
            case "left":
                entity.collisionOn = checkCollision(entityTopWorldRow, entityLeftWorldCol) || checkCollision(entityBottomWorldRow, entityLeftWorldCol);
                break;
            case "right":
                entity.collisionOn = checkCollision(entityTopWorldRow, entityRightWorldCol) || checkCollision(entityBottomWorldRow, entityRightWorldCol);
                break;
        }
    }

    private boolean checkCollision(int row, int col) {
        int tileNum = gp.tileManager.mapNum[row][col];
        return gp.tileManager.tiles[tileNum].collision;
    }
}
