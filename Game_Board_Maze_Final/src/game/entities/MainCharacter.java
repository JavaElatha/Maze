package game.entities;

import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;

public class MainCharacter {
    private String name;

    private int width, height, x, y, speed;
    public static int lives = 3;
    public static int score = 0;


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

    private SpriteSheet image;
    private Rectangle contactBox;

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
            //check if there is a brick border to the right
            for (int i = 0; i < Game.bricksCollection.size(); i++) {
                crossesABorder = checkIfCrossesBorderToTheRight(i, Game.bricksCollection);
                if(crossesABorder == true) {
                    break;
                }
            }

            if(!crossesABorder) {
                this.x+=this.speed;
            }

            boolean crossesAQuestion = false;
            //check if there is a question mark to the right
            for (int i = 0; i < Game.questionsCollection.size(); i++) {

                crossesAQuestion = checkIfCrossesBorderToTheRight(i, Game.questionsCollection);

                if(crossesAQuestion) {
                    launchAQuestion(i);
                    break;
                }
            }
        }

        if(this.isMovingLeft) {
            boolean crossesABorder = false;
            //check if there is a brick border to the left
            for (int i = 0; i < Game.bricksCollection.size(); i++) {
                crossesABorder = checkIfCrossesABorderToTheLeft(i, Game.bricksCollection);

                if(crossesABorder == true) {
                    break;
                }
            }

            if(!crossesABorder) {
                this.x -= speed;
            }

            boolean crossesAQuestion = false;
            //check if there is a question mark to the left
            for (int i = 0; i < Game.questionsCollection.size(); i++) {

                crossesAQuestion = checkIfCrossesABorderToTheLeft(i, Game.questionsCollection);

                if(crossesAQuestion) {
                    launchAQuestion(i);
                    break;
                }
            }
        }

        if(this.isMovingUp) {
            boolean crossesABorder = false;
            //check if there is a brick border above
            for (int i = 0; i < Game.bricksCollection.size(); i++) {
                crossesABorder = checkIfCrossesABorderUp(i, Game.bricksCollection);

                if(crossesABorder == true) {
                    break;
                }
            }

            if(!crossesABorder) {
                this.y -= speed;
            }

            boolean crossesAQuestion = false;
            //check if there is a question mark above
            for (int i = 0; i < Game.questionsCollection.size(); i++) {

                crossesAQuestion = checkIfCrossesABorderUp(i, Game.questionsCollection);

                if(crossesAQuestion) {
                    launchAQuestion(i);
                    break;
                }
            }

        }

        if(this.isMovingDown){

            boolean crossesABorder = false;
            //check if there is a brick border bellow
            for (int i = 0; i < Game.bricksCollection.size(); i++) {
                crossesABorder = checkIfCrossesBorderBellow(i, Game.bricksCollection);
                if(crossesABorder == true) {
                    break;
                }
            }

            if(!crossesABorder) {
                this.y += speed;
            }

            boolean crossesAQuestion = false;
            //check if there is a question mark bellow
            for (int i = 0; i < Game.questionsCollection.size(); i++) {

                crossesAQuestion = checkIfCrossesBorderBellow(i, Game.questionsCollection);

                if(crossesAQuestion) {
                    launchAQuestion(i);
                    break;
                }
            }
        }

        //checking the direction of movement and choosing the crop positions of the sprite sheet.
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

    private void launchAQuestion(int i) {
        if(i==0) {
            if (!Quiz.firstQuestionIsAnswered) {
                Quiz.tookAQuestionFirst = true;
            }
        } else if(i==1) {
            if(!Quiz.secondQuestionIsAnswered && Quiz.secondQuestionIsVisible) {
                Quiz.tookAQuestionSecond = true;
            }
        } else if(i==2) {
            if(!Quiz.thirdQuestionIsAnswered && Quiz.thirdQuestionIsVisible) {
                Quiz.tookAQuestionThird = true;
            }
        } else if(i==3) {
            if(!Quiz.fourthQuestionIsAnswered && Quiz.fourthQuestionIsVisible) {
                Quiz.tookAQuestionFourth = true;
            }
        } else if (i==4) {
            if(!Quiz.fifthQuestionIsAnswered && Quiz.fifthQuestionIsVisible) {
                Quiz.tookAQuestionFifth = true;
            }
        } else if (i==5) {
            if(!Quiz.sixthQuestionIsAnswered && Quiz.sixthQuestionIsVisible) {
                Quiz.tookAQuestionSixth = true;
            }
        }
    }

    private boolean checkIfCrossesABorderUp(int i, ArrayList<Rectangle> rectangles) {
        boolean crossesABorder;
        crossesABorder = this.y- this.speed > rectangles.get(i).y
                && this.y - this.speed < rectangles.get(i).y + rectangles.get(i).height
                && ((this.x  > rectangles.get(i).x && this.x < rectangles.get(i).x + rectangles.get(i).width)
                || (this.x + this.width > rectangles.get(i).x && this.x + this.width < rectangles.get(i).x+rectangles.get(i).width));
        return crossesABorder;
    }

    private boolean checkIfCrossesABorderToTheLeft(int i, ArrayList<Rectangle> rectangles) {
        boolean crossesABorder;

        crossesABorder = ((this.y + this.height >= rectangles.get(i).y
                && this.y + this.height <= rectangles.get(i).y + rectangles.get(i).height)
                || (this.y >= rectangles.get(i).y && this.y <= rectangles.get(i).y + rectangles.get(i).height)
                || ( this.y < rectangles.get(i).y && this.y+ this.height > rectangles.get(i).y + rectangles.get(i).height))
                && this.x - this.speed  <= rectangles.get(i).x + rectangles.get(i).width
                && this.x - this.speed >= rectangles.get(i).x;

        return crossesABorder;
    }

    private boolean checkIfCrossesBorderToTheRight(int i, ArrayList<Rectangle> rectangles) {
        boolean crossesABorder;
        crossesABorder = ((this.y + this.height >= rectangles.get(i).y
                && this.y + this.height <= rectangles.get(i).y + rectangles.get(i).height)
                || (this.y >= rectangles.get(i).y && this.y <= rectangles.get(i).y + rectangles.get(i).height)
                || (this.y < rectangles.get(i).y && this.y+ this.height > rectangles.get(i).y + rectangles.get(i).height))
                && this.x + this.speed + this.width >= rectangles.get(i).x
                && this.x + this.speed <= rectangles.get(i).x+rectangles.get(i).width;
        return crossesABorder;
    }

    private boolean checkIfCrossesBorderBellow(int i, ArrayList <Rectangle> rectangles) {
        boolean crossesABorder;
        crossesABorder = this.y+ this.speed + this.height > rectangles.get(i).y
                && this.y + this.speed + this.height < rectangles.get(i).y + rectangles.get(i).height
                && ((this.x  >= rectangles.get(i).x
                && this.x <= rectangles.get(i).x+rectangles.get(i).width) || (this.x + this.width > rectangles.get(i).x
                && this.x + this.width < rectangles.get(i).x+rectangles.get(i).width));
        return crossesABorder;
    }

    //draw the main character.
    public void render(Graphics g) {

        g.drawImage(this.image.crop(col, row), this.x, this.y, null);

    }
}
