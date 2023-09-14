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
    public boolean enterPressed;

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
        if (gp.gameState == GameState.PLAY) {
            handlePlayKeyPressed(code);
        } else if (gp.gameState == GameState.PAUSED) {
            handlePausedKeyPressed(code);
        } else if (gp.gameState == GameState.DIALOGUE) {
            handleDialogueKeyPressed(code);
        }
    }

    private void handlePlayKeyPressed(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_T) {
            checkDrawTime = !checkDrawTime;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = GameState.PAUSED;
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.player.currentInteractNPC != null) {
                gp.gameState = GameState.DIALOGUE;
                gp.player.currentInteractNPC.speak();
            }
        }
    }

    private void handlePausedKeyPressed(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = GameState.PLAY;
        }
    }

    private void handleDialogueKeyPressed(int code) {
        if (code == KeyEvent.VK_ENTER) {
            if (gp.player.currentInteractNPC != null) {
                gp.player.currentInteractNPC.speak();
            }
        }
    }

    public void resetMoveKeyPressed() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == GameState.PLAY) {
            handlePlayKeyReleased(code);
        }
    }

    private void handlePlayKeyReleased(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
