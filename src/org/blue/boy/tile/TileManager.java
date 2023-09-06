package org.blue.boy.tile;

import org.blue.boy.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

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
        tiles[0] = new Tile();
        tiles[0].image = gp.fileUtil.loadImage("/tiles/grass.png");

        // 墙壁
        tiles[1] = new Tile();
        tiles[1].image = gp.fileUtil.loadImage("/tiles/wall.png");
        tiles[1].collision = true;

        // 水
        tiles[2] = new Tile();
        tiles[2].image = gp.fileUtil.loadImage("/tiles/water.png");
        tiles[2].collision = true;

        // 地球
        tiles[3] = new Tile();
        tiles[3].image = gp.fileUtil.loadImage("/tiles/earth.png");

        // 树
        tiles[4] = new Tile();
        tiles[4].image = gp.fileUtil.loadImage("/tiles/tree.png");
        tiles[4].collision = true;

        // 沙漠
        tiles[5] = new Tile();
        tiles[5].image = gp.fileUtil.loadImage("/tiles/sand.png");
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

                    // TODO: 测试用
                    g2d.setColor(Color.red);
                    g2d.drawRect(screenX, screenY, GamePanel.tileSize, GamePanel.tileSize);
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
