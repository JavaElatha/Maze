package game.entities;

import gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ff on 11.11.2015 ã..
 */
 public class Quiz {

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

    public static boolean firstQuestionIsVisible;
    public static boolean secondQuestionIsVisible;
    public static boolean thirdQuestionIsVisible;
    public static boolean fourthQuestionIsVisible;
    public static boolean fifthQuestionIsVisible;
    public static boolean sixthQuestionIsVisible;

    public static boolean firstQuestionIsAnswered;
    public static boolean secondQuestionIsAnswered;
    public static boolean thirdQuestionIsAnswered;
    public static boolean fourthQuestionIsAnswered;
    public static boolean fifthQuestionIsAnswered;
    public static boolean sixthQuestionIsAnswered;

    public static BufferedImage coin = ImageLoader.loadImage("/images/coin2.jpg");

    private BufferedImage firstImage = ImageLoader.loadImage("/images/first_question.png");
    private BufferedImage secImage = ImageLoader.loadImage("/images/question_One_Answer.png");
    private BufferedImage thirdImage = ImageLoader.loadImage("/images/rock_cotton.png");
    private BufferedImage fourImage = ImageLoader.loadImage("/images/Second_Question.png");
    private BufferedImage fiveImage = ImageLoader.loadImage("/images/months.jpg");
    private BufferedImage sixImage = ImageLoader.loadImage("/images/rumerAndVal.png");

    public Quiz() {
    }

    //drawing the question marks around the field depending on the level
    public void Questions(BufferedImage coin, Graphics g) {
        if(levels == 1) {
            //1 question
            g.drawImage(coin, 100, 25, null);
            firstQuestionIsVisible = true;

        } else if(levels == 2) {
            //2 questions
            if(!secondQuestionIsAnswered) {
                g.drawImage(coin, 400, 250, null);
                secondQuestionIsVisible = true;
            }

            if(!thirdQuestionIsAnswered) {
                g.drawImage(coin, 600, 370, null);
                thirdQuestionIsVisible = true;
            }
        } else if (levels == 3){
            //3 questions
            if(!fourthQuestionIsAnswered) {
                g.drawImage(coin, 200,350, null);
                fourthQuestionIsVisible = true;
            }

            if(!fifthQuestionIsAnswered) {
                g.drawImage(coin, 370, 430, null);
                fifthQuestionIsVisible = true;
            }

            if(!sixthQuestionIsAnswered) {
                g.drawImage(coin, 50,120, null);
                sixthQuestionIsVisible = true;
            }
        } else {
            //if all the levels are passed successfully
            BufferedImage winPicture = ImageLoader.loadImage("/images/winGameOverPicture.png");
            g.drawImage(winPicture, 150, 150, null);
            String a = "Bravo! You passed all levels!";
            g.drawString(a, 210, 170);
            String b = "Score: " + MainCharacter.score;
            g.drawString(b, 210, 180);
        }
    }
    //Determining whether the given answer is right or wrong. If the answer is correct, the player gets a point.
    // If it is wrong, he loses a life.
    public void Image(Graphics g) {
        if(tookAQuestionFirst) {
            g.drawImage(firstImage, 100, 24, null);
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

            g.drawImage(secImage, 100, 24, null);
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
            g.drawImage(thirdImage, 100, 24, null);
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
            g.drawImage(fourImage, 100, 24, null);
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
            g.drawImage(fiveImage, 100, 24, null);
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
            g.drawImage(sixImage, 100, 24, null);
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

        //if the player makes more than 3 mistakes, the game is over.
        if(MainCharacter.lives == 0) {
            BufferedImage winPicture = ImageLoader.loadImage("/images/winGameOverPicture.png");
            g.drawImage(winPicture, 150, 150, null);
            String a = "Game over!";
            g.drawString(a, 210, 170);
            String b = "Score: " + MainCharacter.score;
            g.drawString(b, 210, 180);
        }
    }
}
