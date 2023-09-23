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
        switch (gp.ui.titleSubState) {
            case WELCOME_COMMAND_SELECTOR:
                gp.ui.welcomeScreen.handleKeyPressed(e);
                break;
            case ROLE_SELECTOR:
                gp.ui.roleSelectScreen.handleKeyPressed(e);
                break;
        }

    }

    @Override
    public void handleReleased(KeyEvent e) {
    }
}
