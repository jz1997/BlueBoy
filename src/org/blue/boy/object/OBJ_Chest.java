package org.blue.boy.object;

import org.blue.boy.main.GamePanel;
import org.blue.boy.entity.SuperObject;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        this.gp = gp;
        image = gp.fileUtil.loadImageAndScale("/objects/chest.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Chest";
    }
}
