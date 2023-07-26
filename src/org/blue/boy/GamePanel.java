package org.blue.boy;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // 屏幕设置
    final int originalTileSize = 16; // 原始区块大小
    final int scale = 3; // 缩放比例    
    final int tileSize = originalTileSize * scale; // 48 * 48
    final int maxScreenCol = 16; // 16 列
    final int maxScreenRow = 12; // 12 行
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    // 帧率
    final int FPS = 60;

    // 按键监听器
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    // 玩家的一些坐标和速度
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    /**
     * 开启游戏线程
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        double lastDrawTime = System.nanoTime();
        double currentTime;
        while (true) {
            currentTime = System.nanoTime();
            // 是否到了一个绘制周期
            delta += (currentTime - lastDrawTime) / drawInterval;
            lastDrawTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    /**
     * 更新
     */
    public void update() {
        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        } else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        } else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    /**
     * 根据信息绘制地图
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.fillRect(playerX, playerY, tileSize, tileSize);
        g2d.dispose();
    }
}
