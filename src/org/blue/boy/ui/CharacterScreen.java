package org.blue.boy.ui;

import org.blue.boy.entity.Entity;
import org.blue.boy.main.GamePanel;
import org.blue.boy.utils.Graphics2DUtil;

import java.awt.*;

public class CharacterScreen extends AbstractScreen {
    public CharacterScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g, Object o) {
        int frameX = GamePanel.tileSize;
        int frameY = GamePanel.tileSize;
        int frameWidth = GamePanel.tileSize * 5;
        int frameHeight = GamePanel.tileSize * 10;

        // 绘制背景
        drawSubWindow(g, frameX, frameY, frameWidth, frameHeight);

        // 绘制属性名和属性值
        drawPlayerProperties(frameX, frameY, frameWidth, frameHeight, g);
    }

    private void drawPlayerProperties(int frameX, int frameY, int frameWidth, int frameHeight, Graphics2D g) {
        int textX = frameX + 20;
        int textY = frameY + GamePanel.tileSize;
        int textRightX = frameX + frameWidth;
        int lineHeight = 40;

        // Draw Property Name
        g.setColor(Color.white);
        g.setFont(UI.maruMonica.deriveFont(Font.PLAIN, 32f));
        g.drawString("Level", textX, textY);
        textY += lineHeight;
        g.drawString("Life", textX, textY);
        textY += lineHeight;
        g.drawString("Strength", textX, textY);
        textY += lineHeight;
        g.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g.drawString("Attack", textX, textY);
        textY += lineHeight;
        g.drawString("Defense", textX, textY);
        textY += lineHeight;
        g.drawString("Exp", textX, textY);
        textY += lineHeight;
        g.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g.drawString("Coin", textX, textY);
        textY += lineHeight;
        g.drawString("Weapon", textX, textY);
        textY += lineHeight;
        g.drawString("Shield", textX, textY);

        // Draw Property Value

        // Draw level value
        String value;
        textY = frameY + GamePanel.tileSize;
        // Level
        value = String.valueOf(gp.player.level);
        // drawRightString(g, value, textRightX, textY);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Life
        value = String.format("%d/%d", gp.player.life, gp.player.maxLife);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Strength
        value = String.valueOf(gp.player.strength);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Dexterity
        value = String.valueOf(gp.player.dexterity);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Attack
        value = String.valueOf(gp.player.attack);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Defense
        value = String.valueOf(gp.player.defense);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Exp
        value = String.valueOf(gp.player.exp);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Next Level
        value = String.valueOf(gp.player.nextLevelExp);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Coin
        value = String.valueOf(gp.player.coin);
        Graphics2DUtil.drawRightString(value, textRightX, textY, 20, g);
        textY += lineHeight;
        // Weapon
        Graphics2DUtil.drawRightImage(gp.player.currentWeapon.down1, textRightX, textY - 20, (double) GamePanel.tileSize / 2, (double) GamePanel.tileSize / 2, 20, g);
        textY += lineHeight;
        // Shield
        Graphics2DUtil.drawRightImage(gp.player.currentShield.down1, textRightX, textY - 20, (double) GamePanel.tileSize / 2, (double) GamePanel.tileSize / 2, 20, g);
    }

}
