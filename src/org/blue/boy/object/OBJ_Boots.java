package org.blue.boy.object;

import org.blue.boy.main.GamePanel;
import org.blue.boy.entity.SuperObject;

public class OBJ_Boots extends SuperObject {
    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        this.gp = gp;
        image = gp.fileUtil.loadImageAndScale("/objects/boots.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Boots";
    }
}
