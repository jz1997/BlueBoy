package org.blue.boy.key;

import org.blue.boy.main.GamePanel;

public abstract class AbstractKeyHandler implements KeyHandler {
    public GamePanel gp;

    public AbstractKeyHandler(GamePanel gp) {
        this.gp = gp;
    }
}
