package org.blue.boy.entity;

import org.blue.boy.main.GamePanel;
import org.blue.boy.main.KeyListener;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.blue.boy.main.Direction.*;

public class Player extends Entity {
    public KeyListener keyListener;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public Entity currentInteractNPC = null;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    public Player(GamePanel gp, KeyListener keyListener) {
        super(gp);
        this.keyListener = keyListener;

        // 初始化英雄屏幕中心坐标
        screenX = gp.screenWidth / 2 - GamePanel.tileSize / 2;
        screenY = gp.screenHeight / 2 - GamePanel.tileSize / 2;

        // 初始化英雄碰撞矩形
        solidArea = new Rectangle(8, 14, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void setup() {
        worldX = gp.worldUtil.calcWorldDistance(23); // GamePanel.tileSize * 23;
        worldY = gp.worldUtil.calcWorldDistance(21); // GamePanel.tileSize * 21;
        // worldX = GamePanel.tileSize * 10;
        // worldY = GamePanel.tileSize * 13;
        speed = 4;
        direction = DOWN;

        // 设置生命
        maxLife = 6;
        life = 6;

        up1 = gp.fileUtil.loadImageAndScale("/player/boy_up_1.png", GamePanel.tileSize, GamePanel.tileSize);
        up2 = gp.fileUtil.loadImageAndScale("/player/boy_up_2.png", GamePanel.tileSize, GamePanel.tileSize);
        down1 = gp.fileUtil.loadImageAndScale("/player/boy_down_1.png", GamePanel.tileSize, GamePanel.tileSize);
        down2 = gp.fileUtil.loadImageAndScale("/player/boy_down_2.png", GamePanel.tileSize, GamePanel.tileSize);
        left1 = gp.fileUtil.loadImageAndScale("/player/boy_left_1.png", GamePanel.tileSize, GamePanel.tileSize);
        left2 = gp.fileUtil.loadImageAndScale("/player/boy_left_2.png", GamePanel.tileSize, GamePanel.tileSize);
        right1 = gp.fileUtil.loadImageAndScale("/player/boy_right_1.png", GamePanel.tileSize, GamePanel.tileSize);
        right2 = gp.fileUtil.loadImageAndScale("/player/boy_right_2.png", GamePanel.tileSize, GamePanel.tileSize);

    }

    @Override
    public void update() {
        if (keyListener.upPressed || keyListener.downPressed || keyListener.leftPressed || keyListener.rightPressed) {
            // 更新方向
            updateDirection();

            // 碰撞检测
            checkCollision();

            // 移动
            if (!collisionOn) {
                move();
            }

            // 动画计数器
            updateSprite();
        }

        // 更新无敌计数器
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    @Override
    public void updateDirection() {
        if (keyListener.upPressed) {
            direction = UP;
        } else if (keyListener.downPressed) {
            direction = DOWN;
        } else if (keyListener.leftPressed) {
            direction = LEFT;
        } else if (keyListener.rightPressed) {
            direction = RIGHT;
        }
    }

    /**
     * 碰撞检测
     */
    @Override
    public void checkCollision() {
        // 检测 TILES 碰撞
        collisionOn = false;
        gp.collisionChecker.checkTile(this);

        // 检测 Object 碰撞
        int objIndex = gp.collisionChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // 检测 NPC 碰撞
        int npcIndex = gp.collisionChecker.checkEntity(this, gp.npcs);
        interactNPC(npcIndex);

        // 检测 monster 碰撞
        int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monsters);
        interactMonster(monsterIndex);

        // 检查事件
        gp.eventHandler.checkEvent();
    }

    /**
     * 处理特殊物品碰撞
     *
     * @param objIndex /
     */
    private void pickUpObject(int objIndex) {
        if (objIndex == -1) {
            return;
        }

        Entity object = gp.objects[objIndex];
        if (object == null) {
            return;
        }

        // TODO: 处理特殊物品
    }

    /**
     * 和 npc 交互
     * @param index npc 在 npc 数组中的索引
     */
    private void interactNPC(int index) {
        if (index == -1) {
            currentInteractNPC = null;
            return;
        }
        // gp.gameState = GameState.DIALOGUE;
        keyListener.resetMoveKeyState();
        currentInteractNPC = gp.npcs[index];
        // gp.npcs[index].speak();
    }

    /**
     * 攻击怪物
     *
     * @param monsterIndex /
     */
    private void interactMonster(int monsterIndex) {
        if (monsterIndex == -1) {
            return;
        }

        // 减少生命
        if (!invincible) {
            subLife(1);
            invincible = true;
        }
    }


    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = getSpriteImage();
        g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
    }

    @Override
    public void speak() {

    }

    public void subLife(int subLife) {
        if (life > 0) {
            this.life -= subLife;
        } else {
            // TODO 进行死亡操作
        }
    }

    public void restoreMaxLife() {
        life = maxLife;
    }

    /**
     * 传送
     *
     * @param row 行
     * @param col 列
     */
    public void teleport(int row, int col) {
        worldX = GamePanel.tileSize * col;
        worldY = GamePanel.tileSize * row;
    }
}