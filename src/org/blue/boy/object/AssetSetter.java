package org.blue.boy.object;

import org.blue.boy.entity.MONSTER_GreenSlime;
import org.blue.boy.entity.NPC_OldMan;
import org.blue.boy.main.GamePanel;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Key
        gp.objects[0] = new OBJ_Key(gp);
        gp.objects[0].worldX = 23 * GamePanel.tileSize;
        gp.objects[0].worldY = 7 * GamePanel.tileSize;

        gp.objects[1] = new OBJ_Key(gp);
        gp.objects[1].worldX = 23 * GamePanel.tileSize;
        gp.objects[1].worldY = 40 * GamePanel.tileSize;

        gp.objects[2] = new OBJ_Key(gp);
        gp.objects[2].worldX = 38 * GamePanel.tileSize;
        gp.objects[2].worldY = 8 * GamePanel.tileSize;

        // Door
        gp.objects[3] = new OBJ_Door(gp);
        gp.objects[3].worldX = 10 * GamePanel.tileSize;
        gp.objects[3].worldY = 12 * GamePanel.tileSize;


        gp.objects[4] = new OBJ_Door(gp);
        gp.objects[4].worldX = 8 * GamePanel.tileSize;
        gp.objects[4].worldY = 28 * GamePanel.tileSize;


        gp.objects[5] = new OBJ_Door(gp);
        gp.objects[5].worldX = 12 * GamePanel.tileSize;
        gp.objects[5].worldY = 23 * GamePanel.tileSize;

        // 宝箱
        gp.objects[6] = new OBJ_Chest(gp);
        gp.objects[6].worldX = 10 * GamePanel.tileSize;
        gp.objects[6].worldY = 9 * GamePanel.tileSize;

        // 鞋子
        gp.objects[7] = new OBJ_Boots(gp);
        gp.objects[7].worldX = 37 * GamePanel.tileSize;
        gp.objects[7].worldY = 42 * GamePanel.tileSize;
    }

    public void setNPC() {
        gp.npcs[0] = new NPC_OldMan(gp);
        gp.npcs[0].worldX = GamePanel.tileSize * 21;
        gp.npcs[0].worldY = GamePanel.tileSize * 21;
        // gp.npcs[0].worldX = GamePanel.tileSize * 10;
        // gp.npcs[0].worldY = GamePanel.tileSize * 10;
    }

    public void setMonsters() {
        gp.monsters[0] = new MONSTER_GreenSlime(gp);
        gp.monsters[0].worldX = gp.worldUtil.calcWorldDistance(23);
        gp.monsters[0].worldY = gp.worldUtil.calcWorldDistance(36);
        // gp.monsters[0].worldX = gp.worldUtil.calcWorldDistance(10);
        // gp.monsters[0].worldY = gp.worldUtil.calcWorldDistance(11);


        gp.monsters[1] = new MONSTER_GreenSlime(gp);
        gp.monsters[1].worldX = gp.worldUtil.calcWorldDistance(23);
        gp.monsters[1].worldY = gp.worldUtil.calcWorldDistance(37);
        // gp.monsters[1].worldX = gp.worldUtil.calcWorldDistance(10);
        // gp.monsters[1].worldY = gp.worldUtil.calcWorldDistance(9);
    }
}
