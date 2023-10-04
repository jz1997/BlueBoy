package org.blue.boy.entity;

import org.blue.boy.main.EntityType;
import org.blue.boy.main.GamePanel;
import org.blue.boy.main.GameState;

import static org.blue.boy.main.Direction.DOWN;

public class NPC_OldMan extends AbstractNPC {
    public NPC_OldMan(GamePanel gp) {
        super(gp);
    }

    @Override
    public void setup() {
        speed = 1;
        direction = DOWN;
        type = EntityType.NPC;

        initImages();

        initDialogues();
    }

    private void initImages() {
        up1 = gp.fileUtil.loadImageAndScale("/npc/oldman_up_1.png", GamePanel.tileSize, GamePanel.tileSize);
        up2 = gp.fileUtil.loadImageAndScale("/npc/oldman_up_2.png", GamePanel.tileSize, GamePanel.tileSize);
        down1 = gp.fileUtil.loadImageAndScale("/npc/oldman_down_1.png", GamePanel.tileSize, GamePanel.tileSize);
        down2 = gp.fileUtil.loadImageAndScale("/npc/oldman_down_2.png", GamePanel.tileSize, GamePanel.tileSize);
        left1 = gp.fileUtil.loadImageAndScale("/npc/oldman_left_1.png", GamePanel.tileSize, GamePanel.tileSize);
        left2 = gp.fileUtil.loadImageAndScale("/npc/oldman_left_2.png", GamePanel.tileSize, GamePanel.tileSize);
        right1 = gp.fileUtil.loadImageAndScale("/npc/oldman_right_1.png", GamePanel.tileSize, GamePanel.tileSize);
        right2 = gp.fileUtil.loadImageAndScale("/npc/oldman_right_2.png", GamePanel.tileSize, GamePanel.tileSize);
    }

    private void initDialogues() {
        dialogues[0] = "Hello lad.";
        dialogues[1] = "So you've come to this island to find the treasure.";
        dialogues[2] = "I used to be a great wizard but now... I'm a bit too old for taking adventure.";
        dialogues[3] = "Well, good luck on you.";

        dialogueIndex = 0;
    }
}
