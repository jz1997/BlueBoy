package org.blue.boy.screen;

import org.blue.boy.main.GamePanel;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractScreen implements Screen {
    public GamePanel gp;
    public Map<Integer, KeyFunction> keyPressedFunctions = new HashMap<>(4);
    public KeyFunction defaultKeyFunction = () -> {};

    public AbstractScreen(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void handleKeyPressed(KeyEvent e) {
        // do nothing
    }

    @Override
    public void handleKeyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void handleKeyReleased(KeyEvent e) {
        // do nothing
    }
}
