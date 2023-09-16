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

    public int commandNum = 0;
    public int maxCommandNum = 3;

    public TitleSubState tileSubState = TitleSubState.WELCOME_COMMAND_SELECTOR;
    public int roleSelectorCommandNum = 0;
    public int maxRoleSelectorCommand = 4;

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
            case TITLE:
                drawTitleScreen(g2d);
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

    private void drawTitleScreen(Graphics2D g) {
        switch (tileSubState) {
            case WELCOME_COMMAND_SELECTOR: // 绘制欢迎命令选择页
                drawWelcomeCommandSelector(g);
                break;
            case ROLE_SELECTOR: // 玩家角色选择
                drawPlayerRoleSelector(g);
                break;
        }
    }

    private void drawPlayerRoleSelector(Graphics2D g) {
        g.setFont(maruMonica.deriveFont(Font.PLAIN, 40F));
        g.setColor(Color.white);

        // Selector your role
        String text = "Selector your role:";
        int x = Graphics2DUtil.getDrawStringCenterX(text, gp.screenWidth, gp.screenHeight, g);
        int y = 2 * GamePanel.tileSize;
        g.drawString(text, x, y);


        // Fighter
        text = "Fighter";
        x = Graphics2DUtil.getDrawStringCenterX(text, gp.screenWidth, gp.screenHeight, g);
        y += 3 * GamePanel.tileSize;
        g.drawString(text, x, y);
        if (roleSelectorCommandNum == 0) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

        // Assassin
        text = "Assassin";
        x = Graphics2DUtil.getDrawStringCenterX(text, gp.screenWidth, gp.screenHeight, g);
        y += GamePanel.tileSize;
        g.drawString(text, x, y);
        if (roleSelectorCommandNum == 1) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

        // Sorcerer
        text = "Sorcerer";
        x = Graphics2DUtil.getDrawStringCenterX(text, gp.screenWidth, gp.screenHeight, g);
        y += GamePanel.tileSize;
        g.drawString(text, x, y);
        if (roleSelectorCommandNum == 2) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

        // Back
        text = "Back";
        x = Graphics2DUtil.getDrawStringCenterX(text, gp.screenWidth, gp.screenHeight, g);
        y += 3 * GamePanel.tileSize;
        g.drawString(text, x, y);
        if (roleSelectorCommandNum == 3) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

    }

    /**
     * 欢迎命令选择页
     * @param g /
     */
    private void drawWelcomeCommandSelector(Graphics2D g) {
        // BACKGROUND
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        String title = "Blue Boy Adventure";

        // TITLE
        g.setFont(maruMonica.deriveFont(Font.BOLD, 96F));
        Rectangle2D titleStringBounds = Graphics2DUtil.getStringBounds(title, g);

        int x = (int) (gp.screenWidth - titleStringBounds.getWidth()) / 2;
        int y = GamePanel.tileSize * 3;

        // SHADOW
        g.setColor(Color.gray);
        g.drawString(title, x + 5, y + 5);

        // TITLE NAME
        g.setColor(Color.white);
        g.drawString(title, x, y);

        // Draw Player
        x = gp.screenWidth / 2 - GamePanel.tileSize;
        y += 2 * GamePanel.tileSize;
        g.drawImage(gp.player.down1, x, y, 2 * GamePanel.tileSize, 2 * GamePanel.tileSize, null);

        // Draw Menu
        g.setColor(Color.white);
        g.setFont(maruMonica.deriveFont(Font.BOLD, 48f));
        // New Game
        String text = "NEW GAME";
        x = (int) (gp.screenWidth / 2 - Graphics2DUtil.getStringWidth(text, g) / 2);
        y += (int) (GamePanel.tileSize * 3.5);
        g.drawString(text, x, y);
        if (commandNum == 0) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

        // Load Game
        text = "LOAD GAME";
        x = (int) (gp.screenWidth / 2 - Graphics2DUtil.getStringWidth(text, g) / 2);
        y += GamePanel.tileSize;
        g.drawString(text, x, y);
        if (commandNum == 1) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

        // Quit
        text = "QUIT";
        x = (int) (gp.screenWidth / 2 - Graphics2DUtil.getStringWidth(text, g) / 2);
        y += GamePanel.tileSize;
        g.drawString(text, x, y);
        if (commandNum == 2) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }
    }

    /**
     * 上一条命令
     */
    public void prevCommand() {
        if (commandNum == 0) {
            commandNum = maxCommandNum - 1;
        } else {
            commandNum--;
        }
    }

    /**
     * 吓一跳命令
     */
    public void nextCommand() {
        if (commandNum == maxCommandNum - 1) {
            commandNum = 0;
        } else {
            commandNum++;
        }
    }


    /**
     * 执行命令
     */
    public void executeCommand() {
        if (commandNum < 0 || commandNum >= maxCommandNum) {
            return;
        }

        switch (commandNum) {
            case 0: // 开始新游戏
                tileSubState = TitleSubState.ROLE_SELECTOR;
                break;
            case 1: // 加载游戏
                // TODO 完成加载游戏记录功能
                break;
            case 2: // 退出
                System.exit(0);
                break;
        }
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
