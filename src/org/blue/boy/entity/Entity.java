package org.blue.boy.entity;

import java.awt.image.BufferedImage;

public class Entity {
    // 坐标信息
    public int worldX, worldY;
    // 移动速度
    public int speed;

    public String direction;

    public BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;

    public int spriteCounter = 0;
    public int spriteNum = 1;

}