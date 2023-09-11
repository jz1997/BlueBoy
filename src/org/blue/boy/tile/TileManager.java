package org.blue.boy.tile;

import org.blue.boy.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * tile 管理器
 */
public class TileManager {
    GamePanel gp;
    public Tile[] tiles = new Tile[10];

    public int[][] mapNum = null;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        loadTileImage();
        loadMap("/maps/world01.txt");
    }

    public void loadMap(String filePath) {
        mapNum = new int[gp.maxWorldRow][gp.maxWorldCol];

        try (InputStream inputStream = gp.fileUtil.loadFile(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line = null;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] tileNumbers = line.split(" ");

                for (int i = 0; i < tileNumbers.length; i++) {
                    mapNum[row][i] = Integer.parseInt(tileNumbers[i]);
                }
                row++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTileImage() {
        // 草地
        loadTile(0, "grass", false);

        // 墙壁/
        loadTile(1, "wall", true);

        // 水
        loadTile(2, "water", true);

        // 地球
        loadTile(3, "earth", false);

        // 树
        loadTile(4, "tree", true);

        // 沙漠
        loadTile(5, "sand", false);
    }

    public void loadTile(int index, String tileName, boolean collision) {
        tiles[index] = new Tile();
        tiles[index].image = gp.fileUtil.loadImage("/tiles/" + tileName + ".png");
        tiles[index].collision = collision;
        gp.fileUtil.scaleImage(tiles[index].image, GamePanel.tileSize, GamePanel.tileSize);
    }


    public void draw(Graphics2D g2d) {
        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                int worldX = GamePanel.tileSize * col;
                int worldY = GamePanel.tileSize * row;
                if (isInRenderRectangle(worldX, worldY)) {
                    int screenX = GamePanel.tileSize * col - gp.player.worldX + gp.player.screenX;
                    int screenY = GamePanel.tileSize * row - gp.player.worldY + gp.player.screenY;
                    BufferedImage image = tiles[mapNum[row][col]].image;
                    g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
                }
            }
        }
    }

    public boolean isInRenderRectangle(int x, int y) {
        return x + GamePanel.tileSize >= gp.player.worldX - gp.player.screenX &&
                x - GamePanel.tileSize <= gp.player.worldX + gp.player.screenX &&
                y - GamePanel.tileSize <= gp.player.worldY + gp.player.screenY &&
                y + GamePanel.tileSize >= gp.player.worldY - gp.player.screenY;
    }
}
