package org.blue.boy.object;

import org.blue.boy.GamePanel;
import org.blue.boy.entity.SuperObject;

public class OBJ_Door extends SuperObject {
    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        this.gp = gp;
        image = gp.fileUtil.loadImage("/objects/door.png");
        name = "Door";
    }
}
