package org.blue.boy.main;

import org.blue.boy.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    BufferedImage keyImage;
    public Font tipFont = new Font("Arial", Font.PLAIN, 32);
    public Font messageFont = new Font("Arial", Font.PLAIN, 24);
    public Font congratulationFont = new Font("Arial", Font.BOLD, 60);
    public Font congratulationTipFont = new Font("Arial", Font.PLAIN, 34);
    int halfTileSize = GamePanel.tileSize / 2;

    // 是否展示消息
    public boolean messageOn = false;
    // 消息内容
    public String message = "";
    // 消息计数器
    public int messageCounter = 0;

    // 游戏使用时间
    public double playTime = 0.0d;
    public DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        OBJ_Key objKey = new OBJ_Key(gp);
        keyImage = objKey.image;
    }

    /**
     * 展示消息
     *
     * @param msg 消息内容
     */
    public void showMessage(String msg) {
        message = msg;
        messageOn = true;
        messageCounter = 0;
    }

    public void draw(Graphics2D g2d) {
        if (gp.gameFinished) {
            drawCongratulations(g2d);
        } else {
            g2d.setFont(tipFont);
            g2d.setColor(Color.white);
            g2d.drawImage(keyImage, halfTileSize, halfTileSize, GamePanel.tileSize, GamePanel.tileSize, null);
            g2d.drawString(String.format(" x %d", gp.player.hasKey), halfTileSize + GamePanel.tileSize, halfTileSize + 40);

            // 显示消息
            if (messageOn) {
                drawMessage(g2d);
            }
        }

        // 绘制游戏时间
        playTime += 1 / 60.0;
        drawPlayTime(g2d);
    }

    public void drawPlayTime(Graphics2D g2d) {
        g2d.setFont(tipFont);
        g2d.setColor(Color.white);
        String formattedPlayTime = decimalFormat.format(playTime);
        g2d.drawString("Time: " + formattedPlayTime, GamePanel.tileSize * 11, halfTileSize + 40);
    }

    private void drawMessage(Graphics2D g2d) {
        g2d.setFont(messageFont);
        g2d.drawString(message, halfTileSize, GamePanel.tileSize * 5);
        messageCounter++;
        if (messageCounter == 120) {
            messageOn = false;
            messageCounter = 0;
        }
    }

    private void drawCongratulations(Graphics2D g2d) {
        String text = "找到宝物, 顺利通关！";
        g2d.setFont(congratulationFont);
        g2d.setColor(Color.yellow);
        double textWidth = g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        double textHeight = g2d.getFontMetrics().getStringBounds(text, g2d).getHeight();

        g2d.drawString(text, (float) (gp.screenWidth - textWidth) / 2, (float) (gp.screenHeight - textHeight) / 2);

        g2d.setFont(congratulationTipFont);
        g2d.setColor(Color.white);
        text = "使用时间：" + decimalFormat.format(playTime) + " 秒";
        textWidth = g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        textHeight = g2d.getFontMetrics().getStringBounds(text, g2d).getHeight();
        g2d.drawString(text, (float) (gp.screenWidth - textWidth) / 2, (float) (gp.screenHeight - textHeight) / 2 + GamePanel.tileSize * 2);
    }
}
