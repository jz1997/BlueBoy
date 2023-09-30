package org.blue.boy.object;

import org.blue.boy.main.GamePanel;

public class OBJ_Heart extends AbstractObject {

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        image = gp.fileUtil.loadImageAndScale("/objects/heart_full.png", GamePanel.tileSize, GamePanel.tileSize);
        image2 = gp.fileUtil.loadImageAndScale("/objects/heart_half.png", GamePanel.tileSize, GamePanel.tileSize);
        image3 = gp.fileUtil.loadImageAndScale("/objects/heart_blank.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Heart";
        collision = true;
    }
}
