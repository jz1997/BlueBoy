package org.blue.boy.main;

import org.blue.boy.key.KeyEventType;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;

/**
 * 按键处理器
 */
public class KeyListener implements java.awt.event.KeyListener {
    private final Logger LOG = Logger.getLogger("KeyHandler");

    GamePanel gp;

    // 方向上键
    public boolean upPressed;
    // 方向下键
    public boolean downPressed;
    // 方向左键
    public boolean leftPressed;
    // 方向右键
    public boolean rightPressed;
    public boolean enterPressed;

    public boolean checkDrawTime = false;

    public KeyListener(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        gp.keyHandlerExecutor.execute(e, KeyEventType.TYPED);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gp.keyHandlerExecutor.execute(e, KeyEventType.PRESSED);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gp.keyHandlerExecutor.execute(e, KeyEventType.RELEASED);
    }

    public void resetMoveKeyState() {
        leftPressed = false;
        rightPressed = false;
        upPressed = false;
        downPressed = false;
        enterPressed = false;
    }
}
