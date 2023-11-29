package org.blue.boy.ui;

import org.blue.boy.main.GamePanel;
import org.blue.boy.utils.Graphics2DUtil;

import java.awt.*;

public class DialogueScreen extends AbstractScreen {
    public DialogueScreen(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g, Object o) {
        if (o == null || o.toString().isEmpty()) {
            return;
        }
        String message = o.toString();
        int x = GamePanel.tileSize * 2;
        int y = GamePanel.tileSize / 2;
        int width = gp.screenWidth - GamePanel.tileSize * 4;
        int height = GamePanel.tileSize * 4;

        drawSubWindow(g, x, y, width, height);

        x += GamePanel.tileSize;
        y += GamePanel.tileSize;
        g.setFont(UI.maruMonica.deriveFont(Font.PLAIN, 28F));

        String[] dialogueParts = message.split("");
        StringBuilder sb = new StringBuilder();
        for (String dialoguePart : dialogueParts) {
            if (Graphics2DUtil.getStringBounds(sb + dialoguePart, g).getWidth() <= width - 2 * GamePanel.tileSize) {
                sb.append(dialoguePart);
            } else {
                g.drawString(sb.toString(), x, y);
                y += 40;
                sb = new StringBuilder(dialoguePart);
            }
        }
        g.drawString(sb.toString(), x, y);
    }
}
