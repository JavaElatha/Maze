package game.entities;

import game.Game;
import game.InputHandler;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class MainCharacter {
    private String name;

    private int width, height, x, y, speed;
    private int lives = 3;

    private int row = 4;
    private int col = 3;

    private int rowMovingRight = 6;
    private int colMovingRight = 3;

    private int rowMovingLeft = 5;
    private int colMovingLeft = 3;

    private int rowMovingUp = 7;
    private int colMovingUp = 3;

    private int rowMovingDown = 4;
    private int colMovingDown = 3;

    public static boolean isMovingRight;
    public static boolean isMovingLeft;
    public static boolean isMovingUp;
    public static boolean isMovingDown;

    private Graphics g;
    private SpriteSheet image;
    private Rectangle contactBox;

    private InputHandler inputHandler;


    public MainCharacter (String name, int width, int height, int x, int y) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.contactBox = new Rectangle(x, y, width, height);
        this.image = new SpriteSheet(Assets.player, width, height);
    }

    public void tick() {
        this.contactBox.setBounds(this.x, this.y, this.width, this.height);
        if(this.isMovingRight) {
            boolean crossesABorder = false;

            for (int i = 0; i < Game.bricksCollection.size(); i++) {
                crossesABorder = checkIfCrossesBorderToTheRight(i);
                if(crossesABorder == true) {
                    break;
                }
            }

            if(!crossesABorder) {
                this.x+=this.speed;
            }
        }

        if(this.isMovingLeft) {
            boolean crossesABorder = false;

            for (int i = 0; i < Game.bricksCollection.size(); i++) {
                crossesABorder = checkIfCrossesABorderToTheLeft(i);

                if(crossesABorder == true) {
                    break;
                }
            }

            if(!crossesABorder) {
                this.x -= speed;
            }
        }

        if(this.isMovingUp) {
            boolean crossesABorder = false;

            for (int i = 0; i < Game.bricksCollection.size(); i++) {
                crossesABorder = checkIfCrossesABorderUp(i);

                if(crossesABorder == true) {
                    break;
                }
            }

            if(!crossesABorder) {
                this.y -= speed;
            }

        }

        if(this.isMovingDown){

            boolean crossesABorder = false;

            for (int i = 0; i < Game.bricksCollection.size(); i++) {
                crossesABorder = checkIfCrossesBorderBellow(i);
                if(crossesABorder == true) {
                        break;
                }
            }

            if(!crossesABorder) {
                this.y += speed;
            }
        }

        if(this.isMovingRight) {
            colMovingRight++;

            if(colMovingRight>=6) {
                colMovingRight = 3;
            }

            row = rowMovingRight;
            col = colMovingRight;
        }

        if(this.isMovingLeft) {

            colMovingLeft++;

            if(colMovingLeft>=6) {
                colMovingLeft = 3;
            }

            row = rowMovingLeft;
            col = colMovingLeft;
        }

        if(this.isMovingUp) {
            colMovingUp++;

            if(colMovingUp>=6) {
                colMovingUp = 3;
            }

            row = rowMovingUp;
            col = colMovingUp;
        }


        if(this.isMovingDown) {
            colMovingDown++;

            if(colMovingDown>=6) {
                colMovingDown = 3;
            }

            row = rowMovingDown;
            col = colMovingDown;
        }
    }

    private boolean checkIfCrossesABorderUp(int i) {
        boolean crossesABorder;
        crossesABorder = this.y- this.speed > Game.bricksCollection.get(i).y
                && this.y - this.speed < Game.bricksCollection.get(i).y + Game.bricksCollection.get(i).height
                && ((this.x  > Game.bricksCollection.get(i).x
                && this.x < Game.bricksCollection.get(i).x+Game.bricksCollection.get(i).width) || (this.x + this.width > Game.bricksCollection.get(i).x
                && this.x + this.width < Game.bricksCollection.get(i).x+Game.bricksCollection.get(i).width));
        return crossesABorder;
    }

    private boolean checkIfCrossesABorderToTheLeft(int i) {
        boolean crossesABorder;
        crossesABorder = ((this.y + this.height >= Game.bricksCollection.get(i).y
                && this.y + this.height <= Game.bricksCollection.get(i).y + Game.bricksCollection.get(i).height)
                ||
                (this.y >= Game.bricksCollection.get(i).y && this.y <= Game.bricksCollection.get(i).y + Game.bricksCollection.get(i).height)
                ||
                (this.y < Game.bricksCollection.get(i).y && this.y+ this.height > Game.bricksCollection.get(i).y + Game.bricksCollection.get(i).height))
                && this.x - this.speed  <= Game.bricksCollection.get(i).x + Game.bricksCollection.get(i).width
                && this.x - this.speed >= Game.bricksCollection.get(i).x;
        return crossesABorder;
    }

    private boolean checkIfCrossesBorderToTheRight(int i) {
        boolean crossesABorder;
        crossesABorder = ((this.y + this.height >= Game.bricksCollection.get(i).y
                && this.y + this.height <= Game.bricksCollection.get(i).y + Game.bricksCollection.get(i).height)
                ||
                (this.y >= Game.bricksCollection.get(i).y && this.y <= Game.bricksCollection.get(i).y + Game.bricksCollection.get(i).height)
                ||
                (this.y < Game.bricksCollection.get(i).y && this.y+ this.height > Game.bricksCollection.get(i).y + Game.bricksCollection.get(i).height))
                && this.x + this.speed + this.width >= Game.bricksCollection.get(i).x
                && this.x + this.speed <= Game.bricksCollection.get(i).x+Game.bricksCollection.get(i).width;
        return crossesABorder;
    }

    private boolean checkIfCrossesBorderBellow(int i) {
        boolean crossesABorder;
        crossesABorder = this.y+ this.speed + this.height > Game.bricksCollection.get(i).y
                && this.y + this.speed + this.height < Game.bricksCollection.get(i).y + Game.bricksCollection.get(i).height
                && ((this.x  >= Game.bricksCollection.get(i).x
                && this.x <= Game.bricksCollection.get(i).x+Game.bricksCollection.get(i).width) || (this.x + this.width > Game.bricksCollection.get(i).x
                && this.x + this.width < Game.bricksCollection.get(i).x+Game.bricksCollection.get(i).width));
        return crossesABorder;
    }


    public void render(Graphics g) {

        g.drawImage(this.image.crop(col, row), this.x, this.y, null);

    }

    public boolean contactEnemy (Rectangle badGuy) {
        if(this.contactBox.contains(badGuy.x, badGuy.y)) {
            return true;
        }

        return false;
    }
}
