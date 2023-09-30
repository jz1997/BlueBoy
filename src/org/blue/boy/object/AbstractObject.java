package org.blue.boy.object;

import org.blue.boy.entity.Entity;
import org.blue.boy.main.GamePanel;

import java.awt.*;

public class AbstractObject extends Entity {
    public AbstractObject(GamePanel gp) {
        super(gp);
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateDirection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkCollision() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (gp.tileManager.isInRenderRectangle(worldX, worldY)) {
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }

    @Override
    public void speak() {
        throw new UnsupportedOperationException();
    }
}
