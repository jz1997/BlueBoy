package org.blue.boy.main;

import org.blue.boy.entity.SuperObject;
import org.blue.boy.object.AssetSetter;
import org.blue.boy.sound.SoundManager;
import org.blue.boy.tile.TileManager;
import org.blue.boy.utils.FileUtil;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // 屏幕设置
    public static final int originalTileSize = 16; // 原始区块大小
    public static final int scale = 3; // 缩放比例
    public static final int tileSize = originalTileSize * scale; // 48 * 48
    public final int maxScreenCol = 16; // 16 列
    public final int maxScreenRow = 12; // 12 行
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    // 帧率
    final int FPS = 60;
    // World Setting
    public final int maxWorldRow = 50;
    public final int maxWorldCol = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;
    public boolean gameFinished = false;

    // 按键监听器
    KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    // Collision Checker
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public FileUtil fileUtil = new FileUtil();
    public TileManager tileManager = new TileManager(this);
    public SoundManager musicManager = new SoundManager(this);
    public SoundManager seManager = new SoundManager(this);
    public UI ui = new UI(this);
    public SuperObject[] objects = new SuperObject[10];
    public Player player = new Player(this, keyHandler);

    public GameState gameState = GameState.PLAY;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    void setUpGame() {
        assetSetter.setObject();

        // 播放背景音乐
        musicManager.playMusic(0, true);
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
        while (!gameFinished) {
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
        if (gameState == GameState.PLAY) {
            player.update();
        }
    }

    /**
     * 根据信息绘制地图
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        // DEBUG
        long drawStartTime = 0;
        if (keyHandler.checkDrawTime) {
            drawStartTime = System.nanoTime();
        }

        // draw tiles
        tileManager.draw(g2d);

        // draw objects, such as key, door ...
        for (SuperObject object : objects) {
            if (object != null) {
                object.draw(g2d, this);
            }
        }

        // draw player
        player.draw(g2d);

        // ui
        ui.draw(g2d);


        // DEBUG
        if (keyHandler.checkDrawTime) {
            long drawEndTime = System.nanoTime();
            g2d.setFont(ui.messageFont);
            g2d.setColor(Color.white);
            g2d.drawString("渲染时间：" + (drawEndTime - drawStartTime) + " ns", tileSize / 2, 400);
        }

        g2d.dispose();
    }
}
