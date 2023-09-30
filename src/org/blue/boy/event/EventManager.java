package org.blue.boy.event;

import org.blue.boy.main.GamePanel;

public class EventManager {
    public GamePanel gp;
    public Event[][] events;

    public EventManager(GamePanel gp) {
        this.gp = gp;
        init();
    }


    private void init() {
        events = new Event[GamePanel.maxWorldRow][GamePanel.maxWorldCol];
        for (int row = 0; row < GamePanel.maxWorldRow; row++) {
            for (int col = 0; col < GamePanel.maxWorldCol; col++) {
                events[row][col] = new Event(row, col, 23, 23, 2, 2);
            }
        }
    }

    public Event getEvent(int row, int col) {
        checkRange(row, col);
        return events[row][col];
    }

    public void disableEvent(int row, int col) {
        checkRange(row, col);
        events[row][col].disabled = true;
    }

    public void enableEvent(int row, int col) {
        checkRange(row, col);
        events[row][col].disabled = false;
    }

    private void checkRange(int row, int col) {
        if (row < 0 || row >= GamePanel.maxWorldRow) {
            throw new IndexOutOfBoundsException(String.format("row 的有效范围为 [0, %d), 当前 row = %d", GamePanel.maxWorldRow, row));
        }

        if (col < 0 || col >= GamePanel.maxWorldCol) {
            throw new IndexOutOfBoundsException(String.format("col 的有效范围为 [0, %d), 当前 col = %d", GamePanel.maxWorldCol, col));
        }
    }
}
