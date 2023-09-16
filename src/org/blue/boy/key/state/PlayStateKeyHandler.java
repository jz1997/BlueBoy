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

    @Override
    public void handleReleased(KeyEvent e) {
        int code = e.getKeyCode();
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
