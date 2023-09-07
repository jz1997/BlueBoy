package org.blue.boy;

import org.blue.boy.entity.Entity;
import org.blue.boy.entity.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import static org.blue.boy.Direction.*;

public class Player extends Entity {
    private static final Logger LOG = Logger.getLogger("Player");

    public GamePanel gp;
    public KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        // 初始化英雄屏幕中心坐标
        screenX = gp.screenWidth / 2 - GamePanel.tileSize / 2;
        screenY = gp.screenHeight / 2 - GamePanel.tileSize / 2;

        // 初始化英雄碰撞矩形
        solidArea = new Rectangle(8, 14, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        // 设置部分默认值
        setDefaultValue();

        // 加载英雄动作图片
        loadImage();
    }

    public void setDefaultValue() {
        worldX = GamePanel.tileSize * 23;
        worldY = GamePanel.tileSize * 21;
        speed = 4;
        direction = DOWN;
    }

    public void loadImage() {
        up1 = gp.fileUtil.loadImage("/player/boy_up_1.png");
        up2 = gp.fileUtil.loadImage("/player/boy_up_2.png");
        down1 = gp.fileUtil.loadImage("/player/boy_down_1.png");
        down2 = gp.fileUtil.loadImage("/player/boy_down_2.png");
        left1 = gp.fileUtil.loadImage("/player/boy_left_1.png");
        left2 = gp.fileUtil.loadImage("/player/boy_left_2.png");
        right1 = gp.fileUtil.loadImage("/player/boy_right_1.png");
        right2 = gp.fileUtil.loadImage("/player/boy_right_2.png");
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = UP;
            } else if (keyHandler.downPressed) {
                direction = DOWN;
            } else if (keyHandler.leftPressed) {
                direction = LEFT;
            } else if (keyHandler.rightPressed) {
                direction = RIGHT;
            }

            // 检测 TILES 碰撞
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // 检测 Object 碰撞
            int objIndex = gp.collisionChecker.checkObject(this, true);
            handleObjectCollision(objIndex);

            if (!collisionOn) {
                if (keyHandler.upPressed) {
                    worldY -= speed;
                } else if (keyHandler.downPressed) {
                    worldY += speed;
                } else if (keyHandler.leftPressed) {
                    worldX -= speed;
                } else if (keyHandler.rightPressed) {
                    worldX += speed;
                }
            }

            // 动画计数器
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
    }

    /**
     * 处理特殊物品碰撞
     *
     * @param objIndex /
     */
    private void handleObjectCollision(int objIndex) {
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
                break;
            case "Door":
                if (hasKey > 0) {
                    LOG.info(() -> "Open the door");
                    gp.objects[objIndex] = null;
                    hasKey--;
                    gp.seManager.playMusic(3, false);
                } else {
                    LOG.info(() -> "Has no key to open the door");
                }
                break;
            case "Chest":
                LOG.info(() -> "Touch object 'Chest'");
                break;
            case "Boots":
                LOG.info(() -> "Pick up a boots");
                speed += 1;
                gp.objects[objIndex] = null;
                gp.seManager.playMusic(2, false);
                break;
            default:
                LOG.info(() -> "Touch unknown object");
        }
    }

    public void draw(Graphics2D g2d) {
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

        g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);

        // TODO: 记得删除
        g2d.setColor(Color.green);
        g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}