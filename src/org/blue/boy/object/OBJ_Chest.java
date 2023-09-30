package org.blue.boy.object;

import org.blue.boy.main.GamePanel;

public class OBJ_Chest extends AbstractObject {
    public OBJ_Chest(GamePanel gp) {
        super(gp);
        image = gp.fileUtil.loadImageAndScale("/objects/chest.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Chest";
    }
}
