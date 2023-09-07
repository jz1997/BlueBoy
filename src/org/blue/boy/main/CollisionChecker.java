package org.blue.boy.main;

import org.blue.boy.entity.Entity;
import org.blue.boy.entity.SuperObject;

import java.awt.*;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * 检测 entity 是否和 周围的 tile 碰撞
     * @param entity {@link Entity} {@link Player} ...
     */
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        switch (entity.direction) {
            case UP:
                entity.collisionOn = getTitleCollision(entityLeftWorldX, entityTopWorldY, entity.direction, entity.speed) ||
                        getTitleCollision(entityRightWorldX, entityTopWorldY, entity.direction, entity.speed);
                break;
            case DOWN:
                entity.collisionOn = getTitleCollision(entityLeftWorldX, entityBottomWorldY, entity.direction, entity.speed) ||
                        getTitleCollision(entityRightWorldX, entityBottomWorldY, entity.direction, entity.speed);
                break;
            case LEFT:
                entity.collisionOn = getTitleCollision(entityLeftWorldX, entityTopWorldY, entity.direction, entity.speed) ||
                        getTitleCollision(entityLeftWorldX, entityBottomWorldY, entity.direction, entity.speed);
                break;
            case RIGHT:
                entity.collisionOn = getTitleCollision(entityRightWorldX, entityTopWorldY, entity.direction, entity.speed) ||
                        getTitleCollision(entityRightWorldX, entityBottomWorldY, entity.direction, entity.speed);
                break;
        }
    }

    private boolean getTileCollision(int row, int col) {
        int tileNum = gp.tileManager.mapNum[row][col];
        return gp.tileManager.tiles[tileNum].collision;
    }

    private boolean getTitleCollision(int x, int y, Direction direction, int speed) {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
        return getTileCollision(y / GamePanel.tileSize, x / GamePanel.tileSize);
    }


    public int checkObject(Entity entity, boolean isPlayer) {
        Rectangle entityWorldRectangle = entity.getWorldNextStepRectangle();
        for (int i = 0; i < gp.objects.length; i++) {
            SuperObject obj = gp.objects[i];
            if (obj == null) {
                continue;
            }

            Rectangle objWorldRectangle = obj.getWorldRectangle();
            // 碰撞
            if (entityWorldRectangle.intersects(objWorldRectangle)) {
                // 是玩家
                if (isPlayer) {
                    if (obj.collision) {
                        entity.collisionOn = true;
                    }
                }
                return i;
            }
        }
        return -1;
    }
}
