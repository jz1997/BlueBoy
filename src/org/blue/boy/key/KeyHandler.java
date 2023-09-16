package org.blue.boy.key;

import java.awt.event.KeyEvent;

public interface KeyHandler {
    void handleTyped(KeyEvent e);

    void handlePressed(KeyEvent e);

    void handleReleased(KeyEvent e);

    void reset();
}
