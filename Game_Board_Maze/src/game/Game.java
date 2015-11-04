package game;

import display.Display;
import game.entities.MainCharacter;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private String title;
    private int hight;
    private int width;

    private Thread thread;
    private boolean isRunning;
    private Display display;

    private BufferStrategy bs;
    private Graphics g; // ��������� �� ��������
    public static MainCharacter player;
    private InputHandler inputHandler;

    private int cropWidth = 40;
    private int cropHeight = 60;

    public Game(String title, int width, int hight) {
        this.title = title;
        this.hight = hight;
        this.width = width;
        this.isRunning = false;

    }

    //���� ����� �� ����� ������  ������,����� �� ������� � ������
    public void init() {
        Assets.init();
        display = new Display(this.title, this.width, this.hight);
        //this.inputHandler = new InputHandler(this.display);
        player = new MainCharacter("Pesho", cropWidth, cropHeight, 100, 200);

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

        BufferedImage img = ImageLoader.loadImage("/images/background.jpg");
        this.g.drawImage(img, 0, 0, null);
        player.render(g);
        this.bs.show();
        this.g.dispose();//������ � ��������� �� �����������



    }

    @Override
    public void run() {
        this.init();


        while (isRunning) {

            this.render();
            this.tick();



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
