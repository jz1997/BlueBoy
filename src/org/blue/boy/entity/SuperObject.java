package org.blue.boy.entity;

import org.blue.boy.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public Rectangle solidArea = new Rectangle(0, 0, GamePanel.tileSize, GamePanel.tileSize);
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2d, GamePanel gp) {
        if (gp.tileManager.isInRenderRectangle(worldX, worldY)) {
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }

    public Rectangle getWorldRectangle() {
        return new Rectangle(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }
}
