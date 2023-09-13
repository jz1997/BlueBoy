package org.blue.boy.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;

/**
 * 按键处理器
 */
public class KeyHandler implements KeyListener {
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

    public boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
            // LOG.info(() -> "Key 'VK_W' pressed");
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            // LOG.info(() -> "Key 'VK_S' pressed");
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            // LOG.info(() -> "Key 'VK_A' pressed");
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            // LOG.info(() -> "Key 'VK_D' pressed");
        }
        if (code == KeyEvent.VK_T) {
            checkDrawTime = !checkDrawTime;
        }
        if (code == KeyEvent.VK_P) {
            switch (gp.gameState) {
                case PLAY:
                    LOG.info("Pause the game.");
                    gp.gameState = GameState.PAUSED;
                    break;
                case PAUSED:
                    LOG.info("Play the game.");
                    gp.gameState = GameState.PLAY;
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
            // LOG.info(() -> "Key 'VK_W' released");
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
            // LOG.info(() -> "Key 'VK_S' released");
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
            // LOG.info(() -> "Key 'VK_A' released");
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
            // LOG.info(() -> "Key 'VK_D' released");
        }
    }
}
