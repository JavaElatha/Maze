package game;

import display.Display;
import game.entities.MainCharacter;
import gfx.Assets;
import gfx.ImageLoader;
import javafx.scene.canvas.GraphicsContext;

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
    public static ArrayList<Rectangle> questionsCollection = new ArrayList<>();

    private InputHandler inputHandler;

    public static int levels = 1;
    public static boolean tookAQuestionFirst;
    public static boolean tookAQuestionSecond;
    public static boolean tookAQuestionThird;
    public static boolean tookAQuestionFourth;
    public static boolean tookAQuestionFifth;
    public static boolean tookAQuestionSixth;
    public static boolean pressedA;
    public static boolean pressedB;
    public static boolean pressedC;
    public static boolean firstQuestionIsAnswered;
    public static boolean secondQuestionIsAnswered;
    public static boolean thirdQuestionIsAnswered;
    public static boolean fourthQuestionIsAnswered;
    public static boolean fifthQuestionIsAnswered;
    public static boolean sixthQuestionIsAnswered;

    public static boolean firstQuestionIsVisible;
    public static boolean secondQuestionIsVisible;
    public static boolean thirdQuestionIsVisible;
    public static boolean fourthQuestionIsVisible;
    public static boolean fifthQuestionIsVisible;
    public static boolean sixthQuestionIsVisible;

    private int cropWidth = 32;
    private int cropHeight = 32;

    private Rectangle b = new Rectangle(0, 200, 550, 23);

    BufferedImage firstImage = ImageLoader.loadImage("/images/first_question.png");
    BufferedImage secImage = ImageLoader.loadImage("/images/question_One_Answer.png");
    BufferedImage thirdImage = ImageLoader.loadImage("/images/rock_cotton.png");
    BufferedImage fourImage = ImageLoader.loadImage("/images/Second_Question.png");
    BufferedImage fiveImage = ImageLoader.loadImage("/images/months.jpg");
    BufferedImage sixImage = ImageLoader.loadImage("/images/rumerAndVal.png");


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


        Image();
        player.render(g);
        this.bs.show();
        this.g.dispose();//������ � ��������� �� �����������

    }

    private void Image() {

        if(tookAQuestionFirst) {
            this.g.drawImage(firstImage, 100, 24, null);
            if(pressedA) {
                MainCharacter.lives--;
                tookAQuestionFirst = false;
                levels++;
            } else if(pressedB) {
                MainCharacter.score++;
                tookAQuestionFirst = false;
                levels++;
            } else if(pressedC) {
                MainCharacter.lives--;
                tookAQuestionFirst = false;
                levels++;
            }

            firstQuestionIsAnswered = true;

        } else if(tookAQuestionSecond) {

            this.g.drawImage(secImage, 100, 24, null);
            if(pressedA) {
                MainCharacter.lives--;
                tookAQuestionSecond = false;
            } else if(pressedB) {
                MainCharacter.lives--;
                tookAQuestionSecond = false;
            } else if(pressedC) {
                MainCharacter.score++;
                tookAQuestionSecond = false;
            }

            secondQuestionIsAnswered = true;

        } else if (tookAQuestionThird) {
            this.g.drawImage(thirdImage, 100, 24, null);
            if(pressedA) {
                MainCharacter.score++;
                tookAQuestionThird = false;
                levels++;
            } else if(pressedB) {
                MainCharacter.lives--;
                tookAQuestionThird = false;
                levels++;
            } else if(pressedC) {
                MainCharacter.lives--;
                tookAQuestionThird = false;
                levels++;
            }

            thirdQuestionIsAnswered = true;

        } else if (tookAQuestionFourth) {
            this.g.drawImage(fourImage, 100, 24, null);
            if(pressedA) {
                MainCharacter.lives--;
                tookAQuestionFourth = false;
                fourthQuestionIsAnswered = true;
            } else if(pressedB) {
                MainCharacter.score++;
                tookAQuestionFourth = false;
                fourthQuestionIsAnswered = true;
            } else if(pressedC) {
                MainCharacter.lives--;
                tookAQuestionFourth = false;
                fourthQuestionIsAnswered = true;
            }

        } else if(tookAQuestionFifth) {
            this.g.drawImage(fiveImage, 100, 24, null);
            if(pressedA) {
                MainCharacter.score++;
                tookAQuestionFifth = false;
                fifthQuestionIsAnswered = true;
            } else if(pressedB) {
                MainCharacter.lives--;
                tookAQuestionFifth = false;
                fifthQuestionIsAnswered = true;
            } else if(pressedC) {
                MainCharacter.lives--;
                tookAQuestionFifth = false;
                fifthQuestionIsAnswered = true;
            }

        } else if (tookAQuestionSixth) {
            this.g.drawImage(sixImage, 100, 24, null);
            if(pressedA) {
                MainCharacter.lives--;
                tookAQuestionSixth = false;
                sixthQuestionIsAnswered = true;
            } else if(pressedB) {
                MainCharacter.score++;
                tookAQuestionSixth = false;
                sixthQuestionIsAnswered = true;
            } else if(pressedC) {
                MainCharacter.lives--;
                tookAQuestionSixth = false;
                sixthQuestionIsAnswered = true;
            }
        }

        if(fourthQuestionIsAnswered && fifthQuestionIsAnswered && sixthQuestionIsAnswered) {
            levels++;
        }
    }




    private void Questions(BufferedImage coin) {
        if(levels == 1) {
            //1 question
            this.g.drawImage(coin, 100, 25, null);
            firstQuestionIsVisible = true;

        } else if(levels == 2) {
            //2 questions
            if(!secondQuestionIsAnswered) {
                this.g.drawImage(coin, 400, 250, null);
                secondQuestionIsVisible = true;
            }

            if(!thirdQuestionIsAnswered) {
                this.g.drawImage(coin, 600, 370, null);
                thirdQuestionIsVisible = true;
            }
        } else if (levels == 3){
            //3 questions
            if(!fourthQuestionIsAnswered) {
                this.g.drawImage(coin, 200,350, null);
                fourthQuestionIsVisible = true;
            }

            if(!fifthQuestionIsAnswered) {
                this.g.drawImage(coin, 370, 430, null);
                fifthQuestionIsVisible = true;
            }

            if(!sixthQuestionIsAnswered) {
                this.g.drawImage(coin, 50,120, null);
                sixthQuestionIsVisible = true;
            }
        } else {
            BufferedImage winPicture = ImageLoader.loadImage("/images/winPicture.png");
            this.g.drawImage(winPicture, 150, 150, null);
            String a = "Bravo! You passed all levels!";
            this.g.drawString(a, 210, 170);
            String b = "Score: " + MainCharacter.score;
            this.g.drawString(b, 210, 180);
        }
    }

    private void printMap(BufferedImage img) {
        BufferedImage brick = ImageLoader.loadImage("/images/bricks.jpg");
        BufferedImage scoreBackground = ImageLoader.loadImage("/images/ScoreBackground.png");
        this.g.drawImage(scoreBackground, 700, 0, null);

        String a = "Score: " + MainCharacter.score;
        this.g.drawString(a, 720, 50);

        String b = "Lives: " + MainCharacter.lives;
        this.g.drawString(b, 720, 100);

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
        questionsCollection.add(new Rectangle(100, 25, 48, 28));
        questionsCollection.add(new Rectangle(400, 250, 48, 28));
        questionsCollection.add(new Rectangle(600, 370, 48, 28));
        questionsCollection.add(new Rectangle(200, 350, 48, 28));
        questionsCollection.add(new Rectangle(370, 430, 48, 28));
        questionsCollection.add(new Rectangle(50, 120, 48, 28));


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
