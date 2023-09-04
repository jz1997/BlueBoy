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

        try (
                InputStream inputStream = getClass().getResourceAsStream(filePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
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
        try {
            // 草地
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            // 墙壁
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tiles[1].collision = true;

            // 水
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tiles[2].collision = true;

            // 地球
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            // 树
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tiles[4].collision = true;

            // 沙漠
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void draw(Graphics2D g2d) {
        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                int worldX = gp.tileSize * col;
                int worldY = gp.tileSize * row;
                if (isInRenderRectangle(worldX, worldY)) {
                    int screenX = gp.tileSize * col - gp.player.worldX + gp.player.screenX;
                    int screenY = gp.tileSize * row - gp.player.worldY + gp.player.screenY;
                    BufferedImage image = tiles[mapNum[row][col]].image;
                    g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }

    private boolean isInRenderRectangle(int x, int y) {
        return x + gp.tileSize >= gp.player.worldX - gp.player.screenX &&
                x - gp.tileSize <= gp.player.worldX + gp.player.screenX &&
                y - gp.tileSize <= gp.player.worldY + gp.player.screenY &&
                y + gp.tileSize >= gp.player.worldY - gp.player.screenY;
    }
}
