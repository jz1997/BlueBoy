package org.blue.boy.utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Graphics2DUtil {
    public static Rectangle2D getStringBounds(String str, Graphics2D g) {
        return g.getFontMetrics().getStringBounds(str, g);
    }

    public static double getStringWidth(String str, Graphics2D g) {
        return getStringBounds(str, g).getWidth();
    }

    public static Point getDrawCenterPoint(int screenWidth, int screenHeight, int width, int height) {
        return new Point((screenWidth - width) / 2, (screenHeight - height) / 2);
    }

    public static int getDrawStringCenterX(String text, int screenWidth, int screenHeight, Graphics2D g) {
        int stringWidth = (int) getStringWidth(text, g);
        return getDrawCenterPoint(screenWidth, screenHeight, stringWidth, 0).x;
    }

    public static void setAlpha(float alpha, Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}
