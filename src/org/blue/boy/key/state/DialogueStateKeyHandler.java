package org.blue.boy.key.state;

import org.blue.boy.key.AbstractKeyHandler;
import org.blue.boy.main.GamePanel;

import java.awt.event.KeyEvent;

public class DialogueStateKeyHandler extends AbstractKeyHandler {
    public DialogueStateKeyHandler(GamePanel gp) {
        super(gp);
    }

    @Override
    public void handleTyped(KeyEvent e) {

    }

    @Override
    public void handlePressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER) {
            if (gp.player.currentInteractNPC != null) {
                gp.player.currentInteractNPC.speak();
            }
        }
    }

    @Override
    public void handleReleased(KeyEvent e) {

    }
}
