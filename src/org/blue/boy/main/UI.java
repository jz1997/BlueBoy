package org.blue.boy.main;

import org.blue.boy.screen.DialogueScreen;
import org.blue.boy.screen.PausedScreen;
import org.blue.boy.screen.RoleSelectScreen;
import org.blue.boy.screen.WelcomeScreen;

import java.awt.*;

public class UI {
    GamePanel gp;
    public static Font maruMonica = null;
    public static Font purisaBold = null;

    // 是否展示消息
    public boolean messageOn = false;
    // 消息内容
    public String message = "";
    // 消息计数器
    public int messageCounter = 0;

    public String dialogueContent = "";

    public TitleSubState titleSubState = TitleSubState.WELCOME_COMMAND_SELECTOR;

    // 欢迎页
    public WelcomeScreen welcomeScreen;

    // 角色选择页
    public RoleSelectScreen roleSelectScreen;

    // 暂停页面
    public PausedScreen pausedScreen;

    // 对话窗口
    public DialogueScreen dialogueScreen;

    public UI(GamePanel gp) {
        this.gp = gp;

        welcomeScreen = new WelcomeScreen(gp);
        roleSelectScreen = new RoleSelectScreen(gp);
        pausedScreen = new PausedScreen(gp);
        dialogueScreen = new DialogueScreen(gp);

        // 初始化字体
        initFont();
    }

    public void initFont() {
        maruMonica = gp.fileUtil.loadFont("/fonts/MaruMonica.ttf");
        purisaBold = gp.fileUtil.loadFont("/fonts/PurisaBold.ttf");
    }

    public void draw(Graphics2D g2d) {
        switch (gp.gameState) {
            case PLAY:
                if (messageOn) {
                    drawMessage(g2d);
                }
                break;
            case PAUSED:
                pausedScreen.draw(g2d, null);
                break;
            case DIALOGUE:
                dialogueScreen.draw(g2d, dialogueContent);
                break;
            case TITLE:
                drawTitleScreen(g2d);
        }
    }

    private void drawTitleScreen(Graphics2D g) {
        switch (titleSubState) {
            case WELCOME_COMMAND_SELECTOR: // 绘制欢迎命令选择页
                welcomeScreen.draw(g, null);
                break;
            case ROLE_SELECTOR: // 玩家角色选择
                roleSelectScreen.draw(g, null);
                break;
        }
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

    private void drawMessage(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.setFont(maruMonica.deriveFont(Font.PLAIN, 40));
        g2d.drawString(message, GamePanel.tileSize / 2, GamePanel.tileSize * 5);
        messageCounter++;
        if (messageCounter == 120) {
            messageOn = false;
            messageCounter = 0;
        }
    }
}
