package org.blue.boy.utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Graphics2DUtil {
    public static Rectangle2D getStringBounds(String str, Graphics2D g) {
        return g.getFontMetrics().getStringBounds(str, g);
    }

    public static Point getDrawCenterPoint(int screenWidth, int screenHeight, int width, int height) {
        return new Point((screenWidth - width) / 2, (screenHeight - height) / 2);
    }
}
