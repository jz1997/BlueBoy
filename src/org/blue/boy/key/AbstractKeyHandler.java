package org.blue.boy.key;

import org.blue.boy.main.GamePanel;

public abstract class AbstractKeyHandler implements KeyHandler {
    public GamePanel gp;
    // 方向上键
    public boolean upPressed;
    // 方向下键
    public boolean downPressed;
    // 方向左键
    public boolean leftPressed;
    // 方向右键
    public boolean rightPressed;
    // Enter
    public boolean enterPressed;

    public boolean checkDrawTime;

    public AbstractKeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void reset() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        enterPressed = false;
    }
}
