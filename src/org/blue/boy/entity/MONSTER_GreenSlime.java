package org.blue.boy.entity;

import org.blue.boy.main.GamePanel;

import java.awt.image.BufferedImage;

public class MONSTER_GreenSlime extends AbstractNPC {
    public MONSTER_GreenSlime(GamePanel gp) {
        super(gp);

        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;

        init();
    }

    private void init() {
        BufferedImage greenSlimeDown01 = gp.fileUtil.loadImageAndScale("/monster/greenslime_down_1.png", GamePanel.tileSize, GamePanel.tileSize);
        BufferedImage greenSlimeDown02 = gp.fileUtil.loadImageAndScale("/monster/greenslime_down_2.png", GamePanel.tileSize, GamePanel.tileSize);
        up1 = greenSlimeDown01;
        up2 = greenSlimeDown02;

        left1 = greenSlimeDown01;
        left2 = greenSlimeDown02;

        right1 = greenSlimeDown01;
        right2 = greenSlimeDown01;

        down1 = greenSlimeDown01;
        down2 = greenSlimeDown02;
    }
}
