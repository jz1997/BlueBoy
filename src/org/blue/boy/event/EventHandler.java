package org.blue.boy.event;

import org.blue.boy.main.Direction;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;

import java.awt.*;

/**
 * 事件处理器
 * @author jiangzheng
 * @createDate 2023/10/1
 * @version 0001
 */
public class EventHandler {
    public GamePanel gp;

    public EventManager eventManager;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventManager = new EventManager(gp);
    }

    public void checkEvent() {
        if (hit(16, 27, Direction.RIGHT)) {
            fallIntoPit(16, 27);
            // Teleport
            // teleport();
        } else if (hit(12, 23, Direction.UP)) {
            drinkWater();
        }
    }

    private void fallIntoPit(int row, int col) {
        gp.keyListener.resetMoveKeyState();
        gp.gameState = GameState.DIALOGUE;
        gp.ui.dialogueContent = "You fall into a pit!";
        gp.player.subLife(1);
        eventManager.disableEvent(row, col);
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
            gp.musicManager.playPowerUpMusic();
            gp.keyListener.enterPressed = false;
        }
    }

    /**
     * 检测事件是否和玩家碰撞
     * @param row 事件行
     * @param col 事件列
     * @param requireDirection 事件发生需要的移动方向 {@link Direction}
     * @return /
     */
    public boolean hit(int row, int col, Direction requireDirection) {
        boolean hit = false;
        Event event = eventManager.getEvent(row, col);

        Rectangle playerWorldRect = gp.player.getWorldRectangle();
        Rectangle eventWorldRect = event.getWorldRectangle();
        if (playerWorldRect.intersects(eventWorldRect)) {
            if (gp.player.direction.equals(requireDirection) || gp.player.direction.equals(Direction.ANY)) {
                hit = !event.disabled;
            }
        } else {
            event.disabled = false;
        }

        return hit;
    }
}
