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
    public Tile[] tiles = new Tile[50];

    public int[][] mapNum = null;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        loadTileImage();
        loadMap("/maps/worldV2.txt");
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
        // 占位符
        loadTile(0, "grass00", false);
        loadTile(1, "grass00", false);
        loadTile(2, "grass00", false);
        loadTile(3, "grass00", false);
        loadTile(4, "grass00", false);
        loadTile(5, "grass00", false);
        loadTile(6, "grass00", false);
        loadTile(7, "grass00", false);
        loadTile(8, "grass00", false);
        loadTile(9, "grass00", false);

        loadTile(10, "grass00", false);
        loadTile(11, "grass01", false);
        loadTile(12, "water00", true);
        loadTile(13, "water01", true);
        loadTile(14, "water02", true);
        loadTile(15, "water03", true);
        loadTile(16, "water04", true);
        loadTile(17, "water05", true);
        loadTile(18, "water06", true);
        loadTile(19, "water07", true);
        loadTile(20, "water08", true);
        loadTile(21, "water09", true);
        loadTile(22, "water10", true);
        loadTile(23, "water11", true);
        loadTile(24, "water12", true);
        loadTile(25, "water13", true);
        loadTile(26, "road00", false);
        loadTile(27, "road01", false);
        loadTile(28, "road02", false);
        loadTile(29, "road03", false);
        loadTile(30, "road04", false);
        loadTile(31, "road05", false);
        loadTile(32, "road06", false);
        loadTile(33, "road07", false);
        loadTile(34, "road08", false);
        loadTile(35, "road09", false);
        loadTile(36, "road10", false);
        loadTile(37, "road11", false);
        loadTile(38, "road12", false);
        loadTile(39, "earth", false);
        loadTile(40, "wall", true);
        loadTile(41, "tree", true);
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
