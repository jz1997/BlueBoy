package org.blue.boy.utils;

import org.blue.boy.main.GamePanel;

public class WorldUtil {
    public int calcWorldDistance(int cells) {
        return GamePanel.tileSize * cells;
    }
}
