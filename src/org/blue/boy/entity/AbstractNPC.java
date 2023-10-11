package org.blue.boy.entity;

import org.blue.boy.main.Direction;
import org.blue.boy.main.EntityType;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class AbstractNPC extends Entity {
    protected int directionCounter = 0;
    protected final int directionInterval = 120;

    public AbstractNPC(GamePanel gp) {
        super(gp);
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
    }

    @Override
    public void update() {
        if (!alive || dying) {
            return ;
        }
        // 每 directionInterval 帧后修改方向
        directionCounter++;
        if (directionInterval == directionCounter) {
            updateDirection();
            directionCounter = 0;
        }

        // 检查碰撞
        checkCollision();

        // 没有碰撞
        if (!collisionOn) {
            move();
        }

        // 更新精灵动画
        updateSprite();

        // 更新无敌计数器
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
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

        // 检测 Tile
        gp.collisionChecker.checkTile(this);

        // 检测和 objects 碰撞
        gp.collisionChecker.checkObject(this, false);

        // 检测 npc 碰撞
        gp.collisionChecker.checkEntity(this, gp.npcs);

        // 检测 monster 碰撞
        gp.collisionChecker.checkEntity(this, gp.monsters);

        // 检测和 player 的碰撞
        boolean checkPlayer = gp.collisionChecker.checkPlayer(this);
        if (checkPlayer && type == EntityType.MONSTER) {
            gp.player.underAttack(this);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (gp.tileManager.isInRenderRectangle(worldX, worldY)) {
            BufferedImage image = getSpriteImage();
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (invincible) {
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            if (dying) {
                drawDyingAnimation(g2d);
            }
            g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    protected void drawDyingAnimation(Graphics2D g) {
        int duration = 10;
        dyingCounter++;
        if (dyingCounter <= duration) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
        }
        if (dyingCounter > duration && dyingCounter <= duration * 2) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        if (dyingCounter > duration * 2 && dyingCounter <= duration * 3) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
        }
        if (dyingCounter > duration * 3 && dyingCounter <= duration * 4) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        if (dyingCounter > duration * 4 && dyingCounter <= duration * 5) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
        }
        if (dyingCounter > duration * 5) {
            dying = false;
            alive = false;
        }
    }

    @Override
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
            gp.gameState = GameState.PLAY;
            return;
        }
        gp.ui.dialogueContent = dialogues[dialogueIndex];
        dialogueIndex++;
        updateDirectionToPlayer();
    }

    private void updateDirectionToPlayer() {
        Direction playerDirection = gp.player.direction;
        switch (playerDirection) {
            case UP:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.UP;
                break;
            case LEFT:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.LEFT;
                break;
        }
    }
}
