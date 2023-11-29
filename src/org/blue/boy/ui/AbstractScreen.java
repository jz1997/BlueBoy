package org.blue.boy.ui;

import org.blue.boy.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractScreen implements Screen {
    public GamePanel gp;
    public Map<Integer, KeyFunction> keyPressedFunctions = new HashMap<>(4);
    public KeyFunction defaultKeyFunction = () -> {
    };

    public AbstractScreen(GamePanel gp) {
        this.gp = gp;
    }

    protected void drawSubWindow(Graphics2D g, int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g.setColor(color);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
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
