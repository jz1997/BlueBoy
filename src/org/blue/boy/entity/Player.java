package org.blue.boy.entity;

import org.blue.boy.main.GamePanel;
import org.blue.boy.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import static org.blue.boy.main.Direction.*;

public class Player extends Entity {
    private static final Logger LOG = Logger.getLogger("Player");

    public KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.keyHandler = keyHandler;

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
        worldX = GamePanel.tileSize * 23;
        worldY = GamePanel.tileSize * 21;
        speed = 4;
        direction = DOWN;

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
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
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
    }

    @Override
    public void updateDirection() {
        if (keyHandler.upPressed) {
            direction = UP;
        } else if (keyHandler.downPressed) {
            direction = DOWN;
        } else if (keyHandler.leftPressed) {
            direction = LEFT;
        } else if (keyHandler.rightPressed) {
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

        SuperObject object = gp.objects[objIndex];
        if (object == null) {
            return;
        }

        // 接触的特殊物品
        switch (object.name) {
            case "Key":
                LOG.info(() -> "Pick up a key");
                hasKey++;
                gp.objects[objIndex] = null;
                gp.seManager.playMusic(1, false);
                gp.ui.showMessage("获得一把钥匙！");
                break;
            case "Door":
                if (hasKey > 0) {
                    LOG.info(() -> "Open the door");
                    gp.objects[objIndex] = null;
                    hasKey--;
                    gp.seManager.playMusic(3, false);
                    gp.ui.showMessage("门已被打开！");
                } else {
                    LOG.info(() -> "Has no key to open the door");
                    gp.ui.showMessage("缺少钥匙！");
                }
                break;
            case "Chest":
                LOG.info(() -> "Find the chest");
                gp.musicManager.stopMusic();
                gp.seManager.playMusic(4, false);
                gp.gameFinished = true;
                gp.gameThread = null;
                break;
            case "Boots":
                LOG.info(() -> "Pick up a boots");
                speed += 1;
                gp.objects[objIndex] = null;
                gp.seManager.playMusic(2, false);
                gp.ui.showMessage("移速增加！");
                break;
            default:
                LOG.info(() -> "Touch unknown object");
        }
    }

    /**
     * 和 npc 交互
     * @param index npc 在 npc 数组中的索引
     */
    private void interactNPC(int index) {
        if (index == -1) {
            return;
        }

        // TODO 增加NPC交互
    }

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = getSpriteImage();
        g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
    }
}