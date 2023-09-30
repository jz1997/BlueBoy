package org.blue.boy.object;

import org.blue.boy.main.GamePanel;

public class OBJ_Door extends AbstractObject {

    public OBJ_Door(GamePanel gp) {
        super(gp);
        image = gp.fileUtil.loadImageAndScale("/objects/door.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Door";
        collision = true;
    }
}
