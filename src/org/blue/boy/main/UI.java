package org.blue.boy.main;

import org.blue.boy.utils.Graphics2DUtil;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UI {
    GamePanel gp;
    Font maruMonica = null;
    Font purisaBold = null;

    int halfTileSize = GamePanel.tileSize / 2;

    // 是否展示消息
    public boolean messageOn = false;
    // 消息内容
    public String message = "";
    // 消息计数器
    public int messageCounter = 0;

    public String dialogueContent = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        // 初始化字体
        initFont();
    }

    public void initFont() {
        maruMonica = gp.fileUtil.loadFont("/fonts/MaruMonica.ttf");
        purisaBold = gp.fileUtil.loadFont("/fonts/PurisaBold.ttf");
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
        switch (gp.gameState) {
            case PLAY:
                if (messageOn) {
                    drawMessage(g2d);
                }
                break;
            case PAUSED:
                drawPaused(g2d);
                break;
            case DIALOGUE:
                drawDialogueScreen(g2d);
                break;
        }

        // if (gp.gameFinished) {
        //     drawCongratulations(g2d);
        // } else {
        //     g2d.setFont(tipFont);
        //     g2d.setColor(Color.white);
        //     g2d.drawImage(keyImage, halfTileSize, halfTileSize, GamePanel.tileSize, GamePanel.tileSize, null);
        //     g2d.drawString(String.format(" x %d", gp.player.hasKey), halfTileSize + GamePanel.tileSize, halfTileSize + 40);
        //
        //     // 显示消息
        //     if (messageOn) {
        //         drawMessage(g2d);
        //     }
        // }

    }

    private void drawDialogueScreen(Graphics2D g) {
        int x = GamePanel.tileSize * 2;
        int y = GamePanel.tileSize / 2;
        int width = gp.screenWidth - GamePanel.tileSize * 4;
        int height = GamePanel.tileSize * 4;

        drawSubWindow(g, x, y, width, height);

        x += GamePanel.tileSize;
        y += GamePanel.tileSize;
        g.setFont(maruMonica.deriveFont(Font.PLAIN, 28F));

        String[] dialogueParts = dialogueContent.split("");
        StringBuilder sb = new StringBuilder();
        for (String dialoguePart : dialogueParts) {
            if (Graphics2DUtil.getStringBounds(sb + dialoguePart, g).getWidth() <= width - 2 * GamePanel.tileSize) {
                sb.append(dialoguePart);
            } else {
                g.drawString(sb.toString(), x, y);
                y += 40;
                sb = new StringBuilder(dialoguePart);
            }
        }
        g.drawString(sb.toString(), x, y);

    }

    private void drawSubWindow(Graphics2D g, int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g.setColor(color);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }


    private void drawPaused(Graphics2D g) {
        g.setFont(maruMonica.deriveFont(Font.PLAIN, 80F));
        g.setColor(Color.white);
        String text = "PAUSED";

        Rectangle2D stringBounds = Graphics2DUtil.getStringBounds(text, g);
        Point centerPoint = Graphics2DUtil.getDrawCenterPoint(gp.screenWidth, gp.screenHeight, (int) stringBounds.getWidth(), 0);

        g.drawString(text, centerPoint.x, centerPoint.y);
    }

    private void drawMessage(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.setFont(maruMonica.deriveFont(Font.PLAIN, 40));
        g2d.drawString(message, halfTileSize, GamePanel.tileSize * 5);
        messageCounter++;
        if (messageCounter == 120) {
            messageOn = false;
            messageCounter = 0;
        }
    }
}
