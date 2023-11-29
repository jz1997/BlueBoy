package org.blue.boy.key.state;

import org.blue.boy.key.AbstractKeyHandler;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;

import java.awt.event.KeyEvent;

public class CharacterStateKeyHandler extends AbstractKeyHandler {
    public CharacterStateKeyHandler(GamePanel gp) {
        super(gp);
    }

    @Override
    public void handleTyped(KeyEvent e) {

    }

    @Override
    public void handlePressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            if (gp.gameState == GameState.CHARACTER) {
                gp.gameState = GameState.PLAY;
            }
        }
    }

    @Override
    public void handleReleased(KeyEvent e) {

    }
}
