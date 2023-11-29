package org.blue.boy.object;

import org.blue.boy.main.GamePanel;

public class OBJ_Sword_Normal extends AbstractObject {
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        down1 = gp.fileUtil.loadImageAndScale("/objects/sword_normal.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Normal Sword";
        attackValue = 1;
    }
}
