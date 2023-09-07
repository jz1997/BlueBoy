package org.blue.boy.entity;

import org.blue.boy.main.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // 坐标信息
    public int worldX, worldY;

    // 碰撞矩形
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;

    // 移动速度
    public int speed;

    public Direction direction;

    public BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle getWorldRectangle() {
        return new Rectangle(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }

    public Rectangle getWorldNextStepRectangle() {
        switch (direction) {
            case UP:
                return new Rectangle(worldX + solidArea.x, worldY + solidArea.y - speed, solidArea.width, solidArea.height);
            case DOWN:
                return new Rectangle(worldX + solidArea.x, worldY + solidArea.y + speed, solidArea.width, solidArea.height);
            case LEFT:
                return new Rectangle(worldX + solidArea.x - speed, worldY + solidArea.y, solidArea.width, solidArea.height);
            case RIGHT:
                return new Rectangle(worldX + solidArea.x + speed, worldY + solidArea.y, solidArea.width, solidArea.height);
            default:
                return getWorldRectangle();
        }
    }
}