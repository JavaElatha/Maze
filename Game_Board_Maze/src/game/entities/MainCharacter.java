package game.entities;

import game.InputHandler;
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
            this.x+=this.speed;
        }

        if(isMovingLeft) {
            this.x-=speed;
        }

        if(isMovingUp) {
            this.y-=speed;
        }

        if(isMovingDown){
            this.y +=speed;
        }
    }

    public void render(Graphics g) {

        g.drawImage(this.image.crop(0, 0), this.x, this.y, null);
    }

    public boolean contactEnemy (Rectangle badGuy) {
        if(this.contactBox.contains(badGuy.x, badGuy.y)) {
            return true;
        }

        return false;
    }
}
