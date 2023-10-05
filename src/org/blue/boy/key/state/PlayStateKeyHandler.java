package org.blue.boy.key.state;

import org.blue.boy.key.AbstractKeyHandler;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;

import java.awt.event.KeyEvent;

public class PlayStateKeyHandler extends AbstractKeyHandler {
    public PlayStateKeyHandler(GamePanel gp) {
        super(gp);
    }

    @Override
    public void handleTyped(KeyEvent e) {

    }

    @Override
    public void handlePressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            gp.keyListener.upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            gp.keyListener.downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            gp.keyListener.leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            gp.keyListener.rightPressed = true;
        }
        if (code == KeyEvent.VK_T) {
            gp.keyListener.checkDrawTime = !gp.keyListener.checkDrawTime;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = GameState.PAUSED;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.keyListener.enterPressed = true;

            // 如果有靠近的 NPC 则进行对话
            if (gp.player.currentInteractNPC != null) {
                gp.gameState = GameState.DIALOGUE;
                gp.player.currentInteractNPC.speak();
            }
        }
        if (code == KeyEvent.VK_J) {
            // 进行攻击
            gp.player.attacking = true;
        }
    }

    @Override
    public void handleReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            gp.keyListener.upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            gp.keyListener.downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            gp.keyListener.leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            gp.keyListener.rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.keyListener.enterPressed = false;
        }
    }
}
