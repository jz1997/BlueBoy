package org.blue.boy.object;

import org.blue.boy.main.GamePanel;

public class OBJ_Boots extends AbstractObject {
    public OBJ_Boots(GamePanel gp) {
        super(gp);
        image = gp.fileUtil.loadImageAndScale("/objects/boots.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Boots";
    }
}
