package org.blue.boy.screen;

import org.blue.boy.main.GamePanel;
import org.blue.boy.main.TitleSubState;
import org.blue.boy.main.UI;
import org.blue.boy.utils.Graphics2DUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class WelcomeScreen extends AbstractScreen {
    public int commandNum = 0;
    public int maxCommandNum = 3;

    public WelcomeScreen(GamePanel gp) {
        super(gp);

        initKeyPressedFunctions();
    }

    public void initKeyPressedFunctions() {
        keyPressedFunctions.put(KeyEvent.VK_S, this::nextCommand);
        keyPressedFunctions.put(KeyEvent.VK_W, this::prevCommand);
        keyPressedFunctions.put(KeyEvent.VK_ENTER, this::executeCommand);
    }

    @Override
    public void draw(Graphics2D g, Object o) {
        // BACKGROUND
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        String title = "Blue Boy Adventure";

        // TITLE
        g.setFont(UI.maruMonica.deriveFont(Font.BOLD, 96F));
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
        g.setFont(UI.maruMonica.deriveFont(Font.BOLD, 48f));
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

    public void handleKeyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        KeyFunction pressedKeyFunction = getPressedKeyFunction(code);
        pressedKeyFunction.execute();
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
                gp.ui.titleSubState = TitleSubState.ROLE_SELECTOR;
                break;
            case 1: // 加载游戏
                // TODO 完成加载游戏记录功能
                break;
            case 2: // 退出
                System.exit(0);
                break;
        }
    }

    public KeyFunction getPressedKeyFunction(int keyCode) {
        return getKeyFunction(keyPressedFunctions, keyCode);
    }

    public KeyFunction getKeyFunction(Map<Integer, KeyFunction> functions, int keyCode) {
        return functions.getOrDefault(keyCode, defaultKeyFunction);
    }
}
