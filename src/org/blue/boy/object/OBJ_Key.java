package org.blue.boy.object;

import org.blue.boy.main.GamePanel;
import org.blue.boy.entity.SuperObject;

public class OBJ_Key extends SuperObject {
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        this.gp = gp;
        image = gp.fileUtil.loadImage("/objects/key.png");
        name = "Key";
    }
}
