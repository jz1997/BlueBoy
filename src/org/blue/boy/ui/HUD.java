package org.blue.boy.ui;

import org.blue.boy.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    public BufferedImage heartFull, heartHalf, heartBlank;

    public GamePanel gp;

    public HUD(GamePanel gp) {
        this.gp = gp;

        heartFull = gp.fileUtil.loadImageAndScale("/objects/heart_full.png", GamePanel.tileSize, GamePanel.tileSize);
        heartHalf = gp.fileUtil.loadImageAndScale("/objects/heart_half.png", GamePanel.tileSize, GamePanel.tileSize);
        heartBlank = gp.fileUtil.loadImageAndScale("/objects/heart_blank.png", GamePanel.tileSize, GamePanel.tileSize);
    }

    public void draw(Graphics2D g) {
        drawHeart(g);
    }

    private void drawHeart(Graphics2D g2d) {
        int i = 0;
        int x = GamePanel.tileSize / 2, y = GamePanel.tileSize / 2;
        while (i < gp.player.maxLife) {
            g2d.drawImage(heartBlank, x, y, null);
            i += 2;
            x += GamePanel.tileSize;
        }

        i = 0;
        x = GamePanel.tileSize / 2;
        while (i < gp.player.life) {
            g2d.drawImage(heartFull, x, y, null);
            i += 2;
            x += GamePanel.tileSize;
        }
        if (gp.player.life % 2 != 0) {
            x -= GamePanel.tileSize;
            g2d.drawImage(heartHalf, x, y, null);
        }
    }
}
