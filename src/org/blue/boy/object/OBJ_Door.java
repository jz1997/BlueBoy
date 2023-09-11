package org.blue.boy.object;

import org.blue.boy.main.GamePanel;
import org.blue.boy.entity.SuperObject;

public class OBJ_Door extends SuperObject {
    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        this.gp = gp;
        image = gp.fileUtil.loadImageAndScale("/objects/door.png", GamePanel.tileSize, GamePanel.tileSize);
        name = "Door";
        collision = true;
    }
}
