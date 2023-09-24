package org.blue.boy.ui;

import org.blue.boy.main.GamePanel;
import org.blue.boy.utils.Graphics2DUtil;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PausedScreen extends AbstractScreen {
    public PausedScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g, Object o) {
        g.setFont(UI.maruMonica.deriveFont(Font.PLAIN, 80F));
        g.setColor(Color.white);
        String text = "PAUSED";

        Rectangle2D stringBounds = Graphics2DUtil.getStringBounds(text, g);
        Point centerPoint = Graphics2DUtil.getDrawCenterPoint(gp.screenWidth, gp.screenHeight, (int) stringBounds.getWidth(), 0);

        g.drawString(text, centerPoint.x, centerPoint.y);
    }
}
