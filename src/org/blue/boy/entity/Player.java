package org.blue.boy.entity;

import org.blue.boy.main.EntityType;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.KeyListener;
import org.blue.boy.object.OBJ_Shield_Wood;
import org.blue.boy.object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.blue.boy.main.Direction.*;

public class Player extends Entity {
    public KeyListener keyListener;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public Entity currentInteractNPC = null;

    public Player(GamePanel gp, KeyListener keyListener) {
        super(gp);
        this.keyListener = keyListener;

        // 初始化英雄屏幕中心坐标
        screenX = gp.screenWidth / 2 - GamePanel.tileSize / 2;
        screenY = gp.screenHeight / 2 - GamePanel.tileSize / 2;

        // 初始化英雄碰撞矩形
        solidArea = new Rectangle(8, 14, 32, 32);

        // 初始化英雄攻击碰撞矩形
        attackArea.width = 36;
        attackArea.height = 36;
    }

    @Override
    public void setup() {
        worldX = gp.worldUtil.calcWorldDistance(23); // GamePanel.tileSize * 23;
        worldY = gp.worldUtil.calcWorldDistance(21); // GamePanel.tileSize * 21;
        speed = 4;
        direction = DOWN;
        type = EntityType.PLAYER;

        // 设置生命
        level = 1;
        maxLife = 6;
        life = 6;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack();           // 总的攻击力 strength * weapon.attackValue
        defense = getDefense();         // 总的防御力 dexterity * shield.defenseValue

        // 加载正常的图片
        up1 = gp.fileUtil.loadImageAndScale("/player/boy_up_1.png", GamePanel.tileSize, GamePanel.tileSize);
        up2 = gp.fileUtil.loadImageAndScale("/player/boy_up_2.png", GamePanel.tileSize, GamePanel.tileSize);
        down1 = gp.fileUtil.loadImageAndScale("/player/boy_down_1.png", GamePanel.tileSize, GamePanel.tileSize);
        down2 = gp.fileUtil.loadImageAndScale("/player/boy_down_2.png", GamePanel.tileSize, GamePanel.tileSize);
        left1 = gp.fileUtil.loadImageAndScale("/player/boy_left_1.png", GamePanel.tileSize, GamePanel.tileSize);
        left2 = gp.fileUtil.loadImageAndScale("/player/boy_left_2.png", GamePanel.tileSize, GamePanel.tileSize);
        right1 = gp.fileUtil.loadImageAndScale("/player/boy_right_1.png", GamePanel.tileSize, GamePanel.tileSize);
        right2 = gp.fileUtil.loadImageAndScale("/player/boy_right_2.png", GamePanel.tileSize, GamePanel.tileSize);

        // 加载攻击图片
        attackUp1 = gp.fileUtil.loadImageAndScale("/player/attacking_sprites/boy_attack_up_1.png", GamePanel.tileSize, GamePanel.tileSize * 2);
        attackUp2 = gp.fileUtil.loadImageAndScale("/player/attacking_sprites/boy_attack_up_2.png", GamePanel.tileSize, GamePanel.tileSize * 2);
        attackDown1 = gp.fileUtil.loadImageAndScale("/player/attacking_sprites/boy_attack_down_1.png", GamePanel.tileSize, GamePanel.tileSize * 2);
        attackDown2 = gp.fileUtil.loadImageAndScale("/player/attacking_sprites/boy_attack_down_2.png", GamePanel.tileSize, GamePanel.tileSize * 2);
        attackLeft1 = gp.fileUtil.loadImageAndScale("/player/attacking_sprites/boy_attack_left_1.png", GamePanel.tileSize * 2, GamePanel.tileSize);
        attackLeft2 = gp.fileUtil.loadImageAndScale("/player/attacking_sprites/boy_attack_left_2.png", GamePanel.tileSize * 2, GamePanel.tileSize);
        attackRight1 = gp.fileUtil.loadImageAndScale("/player/attacking_sprites/boy_attack_right_1.png", GamePanel.tileSize * 2, GamePanel.tileSize);
        attackRight2 = gp.fileUtil.loadImageAndScale("/player/attacking_sprites/boy_attack_right_2.png", GamePanel.tileSize * 2, GamePanel.tileSize);
    }

    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    @Override
    public void update() {
        if (attacking) {
            attacking();
        } else if (keyListener.upPressed || keyListener.downPressed || keyListener.leftPressed || keyListener.rightPressed || keyListener.enterPressed) {
            // 更新方向
            updateDirection();

            // 碰撞检测
            checkCollision();

            // 移动
            if (!collisionOn && !keyListener.enterPressed) {
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

    /**
     * 进行怪物攻击
     */
    public void attacking() {
        attackSpriteCounter++;
        if (attackSpriteCounter <= 2) {
            spriteNum = 1;
        }
        if (attackSpriteCounter > 2 && attackSpriteCounter <= 10) {
            spriteNum = 2;
            int monsterIndex = gp.collisionChecker.checkPlayerAttack(gp.monsters);
            if (monsterIndex == -1) {
                // System.out.println("Miss");
            } else {
                Entity monster = gp.monsters[monsterIndex];
                if (!monster.invincible) {
                    gp.musicManager.playHitMonsterMusic();
                    monster.invincible = true;

                    // 扣血
                    int damage = this.attack - monster.defense;
                    if (damage < 0) {
                        damage = 0;
                    }
                    monster.life -= damage;
                    monster.hpBarOn = true;
                    monster.damageReaction();
                    if (monster.isDead()) {
                        monster.dying = true;
                    }
                }
            }
        }
        if (attackSpriteCounter > 10) {
            spriteNum = 1;
            attackSpriteCounter = 0;
            attacking = false;
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
     * 碰撞到怪物
     *
     * @param monsterIndex /
     */
    private void interactMonster(int monsterIndex) {
        if (monsterIndex == -1) {
            return;
        }

        // 减少生命
        Entity monster = gp.monsters[monsterIndex];
        if (!invincible && monster.alive && !monster.dying) {
            int damage = monster.attack - this.defense;
            if (damage < 0) {
                damage = 0;
            }
            subLife(damage);
            gp.musicManager.playReceiveDamageMusic();
            invincible = true;
        }
    }


    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = getSpriteImage();
        int drawScreenX = screenX, drawScreenY = screenY;
        if (attacking) {
            switch (direction) {
                case UP:
                    drawScreenY -= GamePanel.tileSize;
                    break;
                case DOWN:
                    break;
                case LEFT:
                    drawScreenX -= GamePanel.tileSize;
                    break;
                case RIGHT:
                    break;
            }
        }
        if (invincible) {
            // 设置透明
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2d.drawImage(image, drawScreenX, drawScreenY, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
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

    public void underAttack(Entity monster) {
        if (invincible) {
            return;
        }
        int damage = monster.attack - this.defense;
        if (damage < 0) {
            damage = 0;
        }
        subLife(damage);
        invincible = true;
    }

    public Rectangle getAttackWorldRectangle() {
        int x = worldX;
        int y = worldY;
        switch (direction) {
            case UP:
                y -= solidArea.height;
                break;
            case DOWN:
                y += solidArea.height;
                break;
            case LEFT:
                x -= solidArea.height;
                break;
            case RIGHT:
                x += solidArea.height;
                break;
        }
        return new Rectangle(x + attackArea.x, y + attackArea.y, attackArea.width, attackArea.height);
    }
}