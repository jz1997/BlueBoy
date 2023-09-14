package org.blue.boy.entity;

import org.blue.boy.main.Direction;
import org.blue.boy.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class AbstractNPC extends Entity {
    protected int directionCounter = 0;
    protected final int directionInterval = 120;

    public AbstractNPC(GamePanel gp) {
        super(gp);
    }

    @Override
    public void update() {
        // 每 directionInterval 帧后修改方向
        directionCounter++;
        if (directionInterval == directionCounter) {
            updateDirection();
            directionCounter = 0;
        }

        checkCollision();

        if (!collisionOn) {
            move();
        }

        // 更新精灵动画
        updateSprite();
    }

    @Override
    public void updateDirection() {
        Random random = new Random();
        int val = random.nextInt(100) + 1;
        if (val <= 25) {
            direction = Direction.UP;
        } else if (val <= 50) {
            direction = Direction.DOWN;
        } else if (val <= 75) {
            direction = Direction.LEFT;
        } else {
            direction = Direction.RIGHT;
        }
    }

    @Override
    public void checkCollision() {
        // 检测和 tiles 碰撞
        collisionOn = false;
        gp.collisionChecker.checkTile(this);

        // 检测和 objects 碰撞
        gp.collisionChecker.checkObject(this, false);

        // 检测和 player 的碰撞
        gp.collisionChecker.checkPlayer(this);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (gp.tileManager.isInRenderRectangle(worldX, worldY)) {
            BufferedImage image = getSpriteImage();
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }
}
