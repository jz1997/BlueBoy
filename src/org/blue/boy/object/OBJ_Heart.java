package org.blue.boy.object;

import org.blue.boy.entity.SuperObject;
import org.blue.boy.main.GamePanel;

public class OBJ_Heart extends SuperObject {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        this.gp = gp;
        image = gp.fileUtil.loadImageAndScale("/objects/heart_full.png", GamePanel.tileSize, GamePanel.tileSize);
        image2 = gp.fileUtil.loadImageAndScale("/objects/heart_half.png", GamePanel.tileSize, GamePanel.tileSize);
        image3 = gp.fileUtil.loadImageAndScale("/objects/heart_blank.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Heart";
        collision = true;
    }
}
