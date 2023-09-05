package org.blue.boy.object;

import org.blue.boy.GamePanel;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Key
        gp.objects[0] = new OBJ_Key(gp);
        gp.objects[0].worldX = 23 * gp.tileSize;
        gp.objects[0].worldY = 7 * gp.tileSize;

        gp.objects[1] = new OBJ_Key(gp);
        gp.objects[1].worldX = 23 * gp.tileSize;
        gp.objects[1].worldY = 40 * gp.tileSize;

        gp.objects[2] = new OBJ_Key(gp);
        gp.objects[2].worldX = 38 * gp.tileSize;
        gp.objects[2].worldY = 8 * gp.tileSize;

        // Door
        gp.objects[3] = new OBJ_Door(gp);
        gp.objects[3].worldX = 10 * gp.tileSize;
        gp.objects[3].worldY = 11 * gp.tileSize;


        gp.objects[4] = new OBJ_Door(gp);
        gp.objects[4].worldX = 8 * gp.tileSize;
        gp.objects[4].worldY = 28 * gp.tileSize;


        gp.objects[5] = new OBJ_Door(gp);
        gp.objects[5].worldX = 12 * gp.tileSize;
        gp.objects[5].worldY = 22 * gp.tileSize;

        // 宝箱
        gp.objects[6] = new OBJ_Chest(gp);
        gp.objects[6].worldX = 10 * gp.tileSize;
        gp.objects[6].worldY = 7 * gp.tileSize;
    }
}
