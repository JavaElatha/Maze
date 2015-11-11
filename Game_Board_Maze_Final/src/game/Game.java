package game;

import display.Display;
import game.entities.MainCharacter;
import game.entities.Quiz;
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
    public static Quiz questions;

    private int cropWidth = 32;
    private int cropHeight = 32;

    public static ArrayList<Rectangle> bricksCollection = new ArrayList<>();
    public static ArrayList<Rectangle> questionsCollection = new ArrayList<>();


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


    //all of the objects for the game will be in this method
    public void init() {
        Assets.init();
        display = new Display(this.title, this.width, this.hight);
        player = new MainCharacter("Pesho", cropWidth, cropHeight, 100, 100);
        questions = new Quiz();
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
        questions.Questions(questions.coin, g);
        questions.Image(g);
        player.render(g);
        this.bs.show();
        this.g.dispose();//������ � ��������� �� �����������

    }

    private void printMap(BufferedImage img) {
        BufferedImage brick = ImageLoader.loadImage("/images/bricks.jpg");
        BufferedImage scoreBackground = ImageLoader.loadImage("/images/ScoreBackground.png");
        BufferedImage scoreBackgroundDown = ImageLoader.loadImage("/images/BackgroundDown.png");

        this.g.drawImage(scoreBackground, 700, 0, null);
        this.g.drawImage(scoreBackgroundDown, 0, 460, null);

        String a = "Score: " + MainCharacter.score;
        this.g.drawString(a, 720, 50);

        String b = "Lives: " + MainCharacter.lives;
        this.g.drawString(b, 720, 100);

        this.g.drawImage(img, 0, 0, null);
        //left
        //brick 0
        for (int i = 0; i < 460; i += 1) {
            this.g.drawImage(brick, 0, i, null);

        }

        // up
        // brick 1
        for (int i = 0; i <= 680; i += 1) {
            this.g.drawImage(brick, i, 0, null);
        }

        //down
        //brick 2
        for (int i = 688; i >= 0; i -= 1) {
            this.g.drawImage(brick, i, 460, null);
        }

        //right
        //brick 3
        for (int i = 460; i >= 0; i -= 1) {
            this.g.drawImage(brick, 688, i, null);
        }

        //middle
        //brick 4
        for (int i = 550; i >= 0; i -= 1) {
            this.g.drawImage(brick, i, 400, null);
        }

        //brick 5
        for (int i = 200; i <= 688; i += 1) {
            this.g.drawImage(brick, i, 300, null);
        }

        //brick 6
        for (int i = 550; i >= 0; i -= 1) {
            this.g.drawImage(brick, i, 200, null);
        }

        //brick 7
        for (int i = 200; i <= 688; i += 1) {
            this.g.drawImage(brick, i, 140, null);
        }

        //brick 8
        for (int i = 550; i >= 0; i -= 1) {
            this.g.drawImage(brick, i, 60, null);
        }
    }

    @Override
    public void run() {
        this.init();
        addingQuestionsContactZones();

        addingBricksContactZones();


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

    private void addingBricksContactZones() {
        //brick 0
        bricksCollection.add(new Rectangle(0, 0, 5, 460));
        //brick 1
        bricksCollection.add(new Rectangle(0, 0, 680, 23));
        //brick 2
        bricksCollection.add(new Rectangle(0, 460, 688, 23));
        //brick 3
        bricksCollection.add(new Rectangle(688, 0, 5, 460));
        //brick 4
        bricksCollection.add(new Rectangle(-1, 400, 560, 23));
        //brick 5
        bricksCollection.add(new Rectangle(205, 300, 688, 23));
        //brick 6
        bricksCollection.add(new Rectangle(-1, 200, 560, 23));
        //brick 7
        bricksCollection.add(new Rectangle(205, 140, 688, 23));
        //brick 8
        bricksCollection.add(new Rectangle(-1, 60, 560, 23));
    }

    private void addingQuestionsContactZones() {
        //question 1 contact zone
        questionsCollection.add(new Rectangle(100, 25, 48, 28));

        //question 2 contact zone
        questionsCollection.add(new Rectangle(400, 250, 48, 28));

        //question 3 contact zone
        questionsCollection.add(new Rectangle(600, 370, 48, 28));

        //question 4 contact zone
        questionsCollection.add(new Rectangle(200, 350, 48, 28));

        //question 5 contact zone
        questionsCollection.add(new Rectangle(370, 430, 48, 28));

        //question 6 contact zone
        questionsCollection.add(new Rectangle(50, 120, 48, 28));
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
