package game.entities;

import display.Display;
import game.Game;
import game.InputHandler;
import game.Launcher;
import gfx.Assets;
import gfx.SpriteSheet;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.PackedColorModel;

/**
 * Created by ff on 3.11.2015 ã..
 */
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

            if(this.x+this.speed <= Launcher.game.getWidth() - this.width) {
                this.x+=this.speed;
            }
        }

        if(isMovingLeft) {
            if(this.x-this.speed >= 0) {
                this.x -= speed;
            }
        }

        if(isMovingUp) {
            if (this.y - this.speed >= 0) {
                this.y -= speed;
            }
        }

        if(isMovingDown){
            if (this.y + this.speed <= Launcher.game.getHeight() - this.height) {
                this.y += speed;
            }
        }

        if(isMovingRight) {
            colMovingRight++;

            if(colMovingRight>=6) {
                colMovingRight = 3;
            }

            row = rowMovingRight;
            col = colMovingRight;
        }

        if(isMovingLeft) {

            colMovingLeft++;

            if(colMovingLeft>=6) {
                colMovingLeft = 3;
            }

            row = rowMovingLeft;
            col = colMovingLeft;
        }

        if(isMovingUp) {
            colMovingUp++;

            if(colMovingUp>=6) {
                colMovingUp = 3;
            }

            row = rowMovingUp;
            col = colMovingUp;
        }


        if(isMovingDown) {
            colMovingDown++;

            if(colMovingDown>=6) {
                colMovingDown = 3;
            }

            row = rowMovingDown;
            col = colMovingDown;
        }
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
