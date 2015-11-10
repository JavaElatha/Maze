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
    BufferedImage firstImage;
    BufferedImage secImage;
    BufferedImage thirdImage;
    BufferedImage fourImage;
    BufferedImage fiveImage;


    public Game(String title, int width, int hight) {
        this.title = title;
        this.hight = hight;
        this.width = width;
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
        //��� ���� �� ��������
        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);//������� ������ �� ��������
            this.bs = display.getCanvas().getBufferStrategy();
        }
        //������,�� ����� �� ������
        this.g = this.bs.getDrawGraphics();

        BufferedImage img = ImageLoader.loadImage("/images/backgroundLast2.jpg");
        printMap(img);
        BufferedImage coin = ImageLoader.loadImage("/images/coin2.jpg");
        Questions(coin);
        firstImage = ImageLoader.loadImage("/images/first_question.png");
        secImage = ImageLoader.loadImage("/images/question_One_Answer.png");
        thirdImage = ImageLoader.loadImage("/images/rock_cotton.png");
        fourImage = ImageLoader.loadImage("/images/Second_Question.png");
        fiveImage = ImageLoader.loadImage("/images/months.jpg");

        Image(firstImage);
        Image(secImage);
        Image(thirdImage);
        Image(fourImage);
        Image(fiveImage);

        player.render(g);
        this.bs.show();
        this.g.dispose();//������ � ��������� �� �����������

    }

    private void Image(BufferedImage imageQ) {
        this.g.drawImage(firstImage, 100, 24, null);
        this.g.drawImage(secImage, 100, 24, null);
        this.g.drawImage(thirdImage, 100, 24, null);
        this.g.drawImage(fourImage, 100, 24, null);
        this.g.drawImage(fiveImage, 100, 24, null);
    }

    private void Questions(BufferedImage coin) {
        //1 question
        this.g.drawImage(coin, 100, 25, null);
        // this.g.drawImage(fImage, 100, 25, null);
//2 questions
        this.g.drawImage(coin, 600, 370, null);
        this.g.drawImage(coin, 400, 250, null);
//3 questions
        this.g.drawImage(coin, 200, 350, null);
        this.g.drawImage(coin, 370, 430, null);
        this.g.drawImage(coin, 50, 120, null);
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
        stop(); //�� �� �� �������� ������ �����
    }


    //�������,�� ����� �� ������ ����������
    public synchronized void start() {

        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        this.thread = new Thread(this);//"����" ��� ����� Game
        this.thread.start();
    }

    public synchronized void stop() {
        if (!this.isRunning) {
            return;
        }
        //��� ������� �� ��������� ���� �� �������� ������

        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
