package org.blue.boy.utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Graphics2DUtil {
    public static Rectangle2D getStringBounds(String str, Graphics2D g) {
        return g.getFontMetrics().getStringBounds(str, g);
    }

    public static double getStringWidth(String str, Graphics2D g) {
        return getStringBounds(str, g).getWidth();
    }

    public static double getStringRightX(String str, double rightX, Graphics2D g) {
        double strWidth = getStringWidth(str, g);
        return rightX - strWidth;
    }

    public static void drawRightString(String str, double rightX, double y, double paddingRight, Graphics2D g) {
        double strWidth = getStringWidth(str, g);
        g.drawString(str, (float) (rightX - strWidth - paddingRight), (int) y);
    }

    public static void drawRightImage(BufferedImage image, double rightX, double y, double width, double height, double paddingRight, Graphics2D g) {
        g.drawImage(image, (int) (rightX - width - paddingRight), (int) y, (int) width, (int) height, null);
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
