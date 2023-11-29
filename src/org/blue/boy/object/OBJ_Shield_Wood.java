package org.blue.boy.object;

import org.blue.boy.main.GamePanel;

public class OBJ_Shield_Wood extends AbstractObject {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        down1 = gp.fileUtil.loadImageAndScale("/objects/shield_wood.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Wood Shield";
        defenseValue = 1;
    }
}
