package org.blue.boy.entity;

import org.blue.boy.main.GamePanel;

import static org.blue.boy.main.Direction.DOWN;

public class NPC_OldMan extends AbstractNPC {
    public NPC_OldMan(GamePanel gp) {
        super(gp);
    }

    @Override
    public void setup() {
        speed = 1;
        direction = DOWN;

        up1 = gp.fileUtil.loadImageAndScale("/npc/oldman_up_1.png", GamePanel.tileSize, GamePanel.tileSize);
        up2 = gp.fileUtil.loadImageAndScale("/npc/oldman_up_2.png", GamePanel.tileSize, GamePanel.tileSize);
        down1 = gp.fileUtil.loadImageAndScale("/npc/oldman_down_1.png", GamePanel.tileSize, GamePanel.tileSize);
        down2 = gp.fileUtil.loadImageAndScale("/npc/oldman_down_2.png", GamePanel.tileSize, GamePanel.tileSize);
        left1 = gp.fileUtil.loadImageAndScale("/npc/oldman_left_1.png", GamePanel.tileSize, GamePanel.tileSize);
        left2 = gp.fileUtil.loadImageAndScale("/npc/oldman_left_2.png", GamePanel.tileSize, GamePanel.tileSize);
        right1 = gp.fileUtil.loadImageAndScale("/npc/oldman_right_1.png", GamePanel.tileSize, GamePanel.tileSize);
        right2 = gp.fileUtil.loadImageAndScale("/npc/oldman_right_2.png", GamePanel.tileSize, GamePanel.tileSize);
    }
}
