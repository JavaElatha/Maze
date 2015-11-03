package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

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

    private SpriteSheet spriteSheet;

    private int cropWidth = 40;
    private int cropHeight = 60;

    int row;
    int col;

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

        this.spriteSheet = new SpriteSheet(Assets.player, cropWidth, cropHeight);
    }

    private void tick() {

        col++;
        if(col==5) {
            row++;
            col=0;

            if(row==2) {
                row=0;
            }
        }
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

        this.g.drawImage(this.spriteSheet.crop(col,row), 0, 0, null);

        this.bs.show();
        this.g.dispose();//������ � ��������� �� �����������



    }

    @Override
    public void run() {
        init();
        while (isRunning) {
            tick();
            render();
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
        this.isRunning = false;
        //��� ������� �� ��������� ���� �� �������� ������
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
