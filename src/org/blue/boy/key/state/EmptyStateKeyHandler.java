package org.blue.boy.key.state;

import org.blue.boy.key.AbstractKeyHandler;
import org.blue.boy.main.GamePanel;

import java.awt.event.KeyEvent;

public class EmptyStateKeyHandler extends AbstractKeyHandler {
    public EmptyStateKeyHandler(GamePanel gp) {
        super(gp);
    }

    @Override
    public void handleTyped(KeyEvent e) {

    }

    @Override
    public void handlePressed(KeyEvent e) {

    }

    @Override
    public void handleReleased(KeyEvent e) {

    }
}
