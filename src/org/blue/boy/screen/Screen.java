package org.blue.boy.screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface Screen {
    void draw(Graphics2D g, Object o);

    void handleKeyPressed(KeyEvent e);

    void handleKeyTyped(KeyEvent e);

    void handleKeyReleased(KeyEvent e);
}
