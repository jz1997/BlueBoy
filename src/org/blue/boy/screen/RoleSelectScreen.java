package org.blue.boy.screen;

import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;
import org.blue.boy.main.TitleSubState;
import org.blue.boy.main.UI;
import org.blue.boy.utils.Graphics2DUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RoleSelectScreen extends AbstractScreen {
    public int commandNum = 0;
    public int maxCommandNum = 4;

    public RoleSelectScreen(GamePanel gp) {
        super(gp);

        initKeyPressedFunctions();
    }

    public void initKeyPressedFunctions() {
        keyPressedFunctions.put(KeyEvent.VK_W, this::prevCommand);
        keyPressedFunctions.put(KeyEvent.VK_S, this::nextCommand);
        keyPressedFunctions.put(KeyEvent.VK_ENTER, this::executeCommand);
    }

    @Override
    public void draw(Graphics2D g, Object o) {
        g.setFont(UI.maruMonica.deriveFont(Font.PLAIN, 40F));
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
        if (commandNum == 0) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

        // Assassin
        text = "Assassin";
        x = Graphics2DUtil.getDrawStringCenterX(text, gp.screenWidth, gp.screenHeight, g);
        y += GamePanel.tileSize;
        g.drawString(text, x, y);
        if (commandNum == 1) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

        // Sorcerer
        text = "Sorcerer";
        x = Graphics2DUtil.getDrawStringCenterX(text, gp.screenWidth, gp.screenHeight, g);
        y += GamePanel.tileSize;
        g.drawString(text, x, y);
        if (commandNum == 2) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }

        // Back
        text = "Back";
        x = Graphics2DUtil.getDrawStringCenterX(text, gp.screenWidth, gp.screenHeight, g);
        y += 3 * GamePanel.tileSize;
        g.drawString(text, x, y);
        if (commandNum == 3) {
            g.drawString(">", x - GamePanel.tileSize, y);
        }
    }


    @Override
    public void handleKeyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                prevCommand();
                break;
            case KeyEvent.VK_S:
                nextCommand();
                break;
            case KeyEvent.VK_ENTER:
                executeCommand();
                break;
        }
    }

    public void nextCommand() {
        if (commandNum == maxCommandNum - 1) {
            commandNum = 0;
        } else {
            commandNum++;
        }
    }

    public void prevCommand() {
        if (commandNum == 0) {
            commandNum = maxCommandNum - 1;
        } else {
            commandNum--;
        }
    }

    public void executeCommand() {
        switch (commandNum) {
            case 0:
                // Fighter
                // todo: 设置角色数据
                // gp.gameState = GameState.PLAY;
                gp.startGame();
                break;
            case 1:
                // Assassin
                // todo: 设置角色数据
                gp.startGame();
                break;
            case 2:
                // Sorcerer
                // todo: 设置角色数据
                gp.startGame();
                break;
            case 3:
                gp.ui.titleSubState = TitleSubState.WELCOME_COMMAND_SELECTOR;
                break;
        }
    }
}
