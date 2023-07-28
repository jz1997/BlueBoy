package org.blue.boy;

import org.blue.boy.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // 屏幕设置
    final int originalTileSize = 16; // 原始区块大小
    final int scale = 3; // 缩放比例    
    public final int tileSize = originalTileSize * scale; // 48 * 48
    final int maxScreenCol = 16; // 16 列
    final int maxScreenRow = 12; // 12 行
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    // 帧率
    final int FPS = 60;

    // 按键监听器
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyHandler);

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
        player.update();
    }

    /**
     * 根据信息绘制地图
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        player.draw(g2d);
    }
}
