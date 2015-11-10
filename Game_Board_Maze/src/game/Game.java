package game;

import display.Display;
import game.entities.MainCharacter;
import gfx.Assets;
import gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game implements Runnable {

    private String title;
    private int hight;
    private int width;
    //coin coordinates
    private int coinX;
    private int coinY;

    private Thread thread;
    private boolean isRunning;
    private Display display;

    private BufferStrategy bs;
    private Graphics g; // allow us to draw
    public static MainCharacter player;
    public static ArrayList<Rectangle> bricksCollection = new ArrayList<>();
    private InputHandler inputHandler;

    private int cropWidth = 32;
    private int cropHeight = 32;

    private Rectangle b = new Rectangle(0, 200, 550, 23);


    public Game(String title, int width, int hight, int coinX, int coinY) {
        this.title = title;
        this.hight = hight;
        this.width = width;

        this.coinX = coinX;
        this.coinY = coinY;
        this.isRunning = false;


    }

    public int getHeight() {
        return hight;
    }

    public int getWidth() {
        return width;
    }

    public Display getDisplay() {
        return display;
    }


    //all of the obejcts for the game will be in this method
    public void init() {
        Assets.init();
        display = new Display(this.title, this.width, this.hight);
        //this.inputHandler = new InputHandler(this.display);
        player = new MainCharacter("Pesho", cropWidth, cropHeight, 100, 100);

    }

    private void tick() {
        player.tick();
    }

    private void render() {
        this.bs = display.getCanvas().getBufferStrategy();
        //ако няма да създадем
        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);//числото зависи от буферите
            this.bs = display.getCanvas().getBufferStrategy();
        }
        //начина,по които се рисува
        this.g = this.bs.getDrawGraphics();

        BufferedImage img = ImageLoader.loadImage("/images/backgroundLast2.jpg");
        printMap(img);
        //coins
        BufferedImage coin = ImageLoader.loadImage("/images/FINASL.png");
        this.g.drawImage(coin, 10, 10, null);

        player.render(g);
        this.bs.show();
        this.g.dispose();//всичко в графиките ще визуализира


    }

    private void printMap(BufferedImage img) {
        BufferedImage brick = ImageLoader.loadImage("/images/bricks.jpg");
        this.g.drawImage(img, 0, 0, null);
        //left
        for (int i = 0; i < 460; i += 1) {
            this.g.drawImage(brick, 0, i, null);
        }
        //brick 0
        bricksCollection.add(new Rectangle(0, 0, 5, 460));

        // up
        for (int i = 0; i <= 680; i += 1) {
            this.g.drawImage(brick, i, 0, null);
        }
        //brick 1
        bricksCollection.add(new Rectangle(0, 0, 680, 23));

        //down
        for (int i = 688; i >= 0; i -= 1) {
            this.g.drawImage(brick, i, 460, null);
        }
        //brick 2
        bricksCollection.add(new Rectangle(0, 460, 688, 23));

        //right
        for (int i = 460; i >= 0; i -= 1) {
            this.g.drawImage(brick, 688, i, null);
        }
        //brick 3
        bricksCollection.add(new Rectangle(688, 0, 5, 460));

        //middle
        for (int i = 550; i >= 0; i -= 1) {
            this.g.drawImage(brick, i, 400, null);
        }
        //brick 4
        bricksCollection.add(new Rectangle(-1, 400, 560, 23));

        for (int i = 200; i <= 688; i += 1) {
            this.g.drawImage(brick, i, 300, null);
        }
        //brick 5
        bricksCollection.add(new Rectangle(205, 300, 688, 23));

        for (int i = 550; i >= 0; i -= 1) {
            this.g.drawImage(brick, i, 200, null);
        }
        //brick 6
        bricksCollection.add(new Rectangle(-1, 200, 560, 23));

        for (int i = 200; i <= 688; i += 1) {
            this.g.drawImage(brick, i, 140, null);
        }
        //brick 7
        bricksCollection.add(new Rectangle(205, 140, 688, 23));

        for (int i = 550; i >= 0; i -= 1) {
            this.g.drawImage(brick, i, 60, null);
        }
        //brick 8
        bricksCollection.add(new Rectangle(-1, 60, 560, 23));

    }

    @Override
    public void run() {
        this.init();


        int fps = 15;
        double timePerTick = 1_000_000_000.0 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1_000_000_000) {
                ticks = 0;
                timer = 0;
            }


        }
        stop(); //за да се затворят всички нишки
    }


    //нишките,по който ще работи програмата
    public synchronized void start() {

        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        this.thread = new Thread(this);//"сочи" към класа Game
        this.thread.start();
    }

    public synchronized void stop() {
        if (!this.isRunning) {
            return;
        }
        //при сливане на тредовете може да възникне грешка

        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
