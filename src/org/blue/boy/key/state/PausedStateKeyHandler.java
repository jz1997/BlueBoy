package org.blue.boy.key.state;

import org.blue.boy.key.AbstractKeyHandler;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;

import java.awt.event.KeyEvent;

public class PausedStateKeyHandler extends AbstractKeyHandler {
    public PausedStateKeyHandler(GamePanel gp) {
        super(gp);
    }

    @Override
    public void handleTyped(KeyEvent e) {

    }

    @Override
    public void handlePressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_P) {
            gp.gameState = GameState.PLAY;
        }
    }

    @Override
    public void handleReleased(KeyEvent e) {

    }
}
