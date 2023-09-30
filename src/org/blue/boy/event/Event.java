package org.blue.boy.event;

import org.blue.boy.main.GamePanel;

import java.awt.*;

public class Event {
    public int row;
    public int col;
    public Rectangle cellRect;
    public Rectangle worldRect;
    public boolean disabled;

    public Event(int row, int col, int x, int y, int width, int height) {
        this.row = row;
        this.col = col;
        this.disabled = false;
        this.cellRect = new Rectangle(x, y, width, height);
        this.worldRect = new Rectangle(col * GamePanel.tileSize + x, row * GamePanel.tileSize + y, width, height);
    }

    public Rectangle getWorldRectangle() {
        return this.worldRect;
    }

    public Rectangle getCellRectangle() {
        return cellRect;
    }

    public boolean isEnable() {
        return !disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }
}
