package org.blue.boy.key.state;

import org.blue.boy.key.AbstractKeyHandler;
import org.blue.boy.main.GamePanel;

import java.awt.event.KeyEvent;

public class TitleStateKeyHandler extends AbstractKeyHandler {
    public TitleStateKeyHandler(GamePanel gp) {
        super(gp);
    }

    @Override
    public void handleTyped(KeyEvent e) {
    }

    @Override
    public void handlePressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W: gp.ui.prevCommand(); break;
            case KeyEvent.VK_S: gp.ui.nextCommand(); break;
            case KeyEvent.VK_ENTER: gp.ui.executeCommand(); break;
        }
    }

    @Override
    public void handleReleased(KeyEvent e) {
    }
}
