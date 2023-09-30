package org.blue.boy.object;

import org.blue.boy.main.GamePanel;

public class OBJ_Key extends AbstractObject {
    public OBJ_Key(GamePanel gp) {
        super(gp);
        image = gp.fileUtil.loadImageAndScale("/objects/key.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Key";
    }
}
