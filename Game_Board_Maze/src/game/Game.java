package game;

import display.Display;
import gfx.ImageLoader;

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
    private Graphics g; // позволява да рисуваме

    public Game(String title, int width, int hight) {
        this.title = title;
        this.hight = hight;
        this.width = width;
        this.isRunning = false;

    }

    //този метод се пишат всички  обекти,който ще работчт в играта
    public void init() {
        display = new Display(this.title, this.width, this.hight);

    }

    private void tick() {

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

        BufferedImage img = ImageLoader.loadImage("/images/background.jpg");
        this.g.drawImage(img, 0, 0, null);
        this.bs.show();
        this.g.dispose();//всичко в графиките ще визуализира


    }

    @Override
    public void run() {
        init();
        while (isRunning) {
            tick();
            render();
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
        this.isRunning = false;
        //при сливане на тредовете може да възникне грешка
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
