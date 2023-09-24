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
            gp.keyListener.resetKeyFlagState();
            gp.gameState = GameState.DIALOGUE;
            gp.ui.dialogueContent = "You fall into a pit!";
            gp.player.life -= 1;
            eventState = false;
        }
    }

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
