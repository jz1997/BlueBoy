package org.blue.boy.object;

import org.blue.boy.GamePanel;
import org.blue.boy.entity.SuperObject;

public class OBJ_Boots extends SuperObject {
    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        this.gp = gp;
        image = gp.fileUtil.loadImage("/objects/boots.png");
        name = "Boots";
    }
}
