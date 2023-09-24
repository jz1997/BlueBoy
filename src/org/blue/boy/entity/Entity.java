package org.blue.boy.entity;

import org.blue.boy.main.Direction;
import org.blue.boy.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public GamePanel gp;
    // 坐标信息
    public int worldX, worldY;

    // 碰撞矩形
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;

    // 移动速度
    public int speed;

    public Direction direction;

    public BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public String[] dialogues = new String[20];
    public int dialogueIndex = 0;

    // 属性
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;

        setup();
    }

    public void setup() {
    }

    /**
     * 属性更新
     */
    public abstract void update();

    public abstract void updateDirection();

    /**
     * 检测碰撞
     */
    public abstract void checkCollision();

    public abstract void draw(Graphics2D g2d);

    public abstract void speak();

    /**
     * 移动
     */
    public void move() {
        switch (direction) {
            case UP:
                worldY -= speed;
                break;
            case DOWN:
                worldY += speed;
                break;
            case LEFT:
                worldX -= speed;
                break;
            case RIGHT:
                worldX += speed;
                break;
        }
    }

    /**
     * 更新精灵动画计数器
     */
    public void updateSprite() {
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }

            spriteCounter = 0;
        }
    }

    /**
     * 获取当前精灵动画图片
     * @return /
     */
    public BufferedImage getSpriteImage() {
        BufferedImage image = null;
        switch (direction) {
            case UP:
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case DOWN:
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case LEFT:
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case RIGHT:
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        return image;
    }

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