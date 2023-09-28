package org.blue.boy.event;

import org.blue.boy.main.Direction;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;

import java.awt.*;

public class EventHandler {
    public Rectangle eventRect;
    public GamePanel gp;
    public boolean eventState = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle(23, 23, 2, 2);
    }

    public void checkEvent() {
        if (hit(16, 27, Direction.RIGHT) && eventState) {
            // fallIntoPit();
            // Teleport
            teleport();
        } else if (hit(12, 23, Direction.UP)) {
            drinkWater();
        }
    }

    private void fallIntoPit() {
        gp.keyListener.resetMoveKeyState();
        gp.gameState = GameState.DIALOGUE;
        gp.ui.dialogueContent = "You fall into a pit!";
        gp.player.subLife(1);
        eventState = false;
    }

    private void teleport() {
        gp.keyListener.resetMoveKeyState();
        gp.gameState = GameState.DIALOGUE;
        gp.ui.dialogueContent = "Teleport!";
        gp.player.teleport(9, 37);
    }

    /**
     * 喝水回复生命
     */
    private void drinkWater() {
        if (gp.keyListener.enterPressed) {
            gp.keyListener.resetMoveKeyState();
            gp.gameState = GameState.DIALOGUE;
            gp.ui.dialogueContent = "You drink the water. Your life has been recovered.";
            gp.player.restoreMaxLife();
            gp.keyListener.enterPressed = false;
        }
    }

    /**
     * 检测事件是否和玩家碰撞
     * @param eventRow 事件行
     * @param eventCol 事件列
     * @param requireDirection 事件发生需要的移动方向 {@link Direction}
     * @return /
     */
    public boolean hit(int eventRow, int eventCol, Direction requireDirection) {
        boolean hit = false;

        Rectangle playerWorldRect = gp.player.getWorldRectangle();
        Rectangle eventWorldRect = getWorldRectangle(eventRow, eventCol);
        if (playerWorldRect.intersects(eventWorldRect)) {
            if (gp.player.direction.equals(requireDirection) || gp.player.direction.equals(Direction.ANY)) {
                hit = true;
            }
        } else {
            eventState = true;
        }

        return hit;
    }

    public Rectangle getWorldRectangle(int row, int col) {
        int x = col * GamePanel.tileSize;
        int y = row * GamePanel.tileSize;

        return new Rectangle(x + eventRect.x, y + eventRect.y, eventRect.width, eventRect.height);
    }
}
