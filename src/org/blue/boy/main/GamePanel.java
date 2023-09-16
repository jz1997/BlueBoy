package org.blue.boy.main;

import org.blue.boy.entity.Entity;
import org.blue.boy.entity.Player;
import org.blue.boy.entity.SuperObject;
import org.blue.boy.key.KeyHandlerExecutor;
import org.blue.boy.object.AssetSetter;
import org.blue.boy.sound.SoundManager;
import org.blue.boy.tile.TileManager;
import org.blue.boy.utils.CollisionChecker;
import org.blue.boy.utils.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

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
    public final int FPS = 60;

    // World Setting
    public final int maxWorldRow = 50;
    public final int maxWorldCol = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // 游戏线程
    public Thread gameThread;

    // 按键监听器
    KeyHandlerExecutor keyHandlerExecutor = new KeyHandlerExecutor(this);
    KeyListener keyListener = new KeyListener(this);
    // Collision Checker
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public FileUtil fileUtil = new FileUtil();
    public TileManager tileManager = new TileManager(this);
    public SoundManager musicManager = new SoundManager(this);
    public SoundManager seManager = new SoundManager(this);
    public UI ui = new UI(this);
    public SuperObject[] objects = new SuperObject[10];
    public Entity[] npcs = new Entity[10];
    public Player player = new Player(this, keyListener);

    // 游戏状态
    public GameState gameState = GameState.TITLE;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyListener);
        this.setFocusable(true);
    }

    /**
     * 初始化项目
     */
    void setUpGame() {
        assetSetter.setObject();
        assetSetter.setNPC();

        // TODO 播放背景音乐
        // musicManager.playMusic(0, true);
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
        if (gameState == GameState.PLAY) {
            // Player update
            player.update();

            // NPC update
            Arrays.stream(npcs).filter(Objects::nonNull).forEach(Entity::update);
        } else if (gameState == GameState.PAUSED) {
            // 游戏暂停需要进行的操作
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
        if (keyListener.checkDrawTime) {
            drawStartTime = System.nanoTime();
        }

        // DRAW
        if (gameState == GameState.TITLE) {
            ui.draw(g2d);
        } else {
            // draw tiles
            tileManager.draw(g2d);

            // draw objects, such as key, door ...
            for (SuperObject object : objects) {
                if (object != null) {
                    object.draw(g2d, this);
                }
            }

            // 绘制 npc
            for (Entity npc : npcs) {
                if (npc != null) {
                    npc.draw(g2d);
                }
            }

            // draw player
            player.draw(g2d);

            // ui
            ui.draw(g2d);
        }

        // DEBUG
        if (keyListener.checkDrawTime) {
            long drawEndTime = System.nanoTime();
            g2d.setFont(ui.maruMonica.deriveFont(Font.PLAIN, 28F));
            g2d.setColor(Color.white);
            g2d.drawString("RENDER INTERVAL：" + (drawEndTime - drawStartTime) + " ns", tileSize / 2, 400);
        }

        g2d.dispose();
    }
}
