package game;

import display.Display;
import game.entities.MainCharacter;
import game.entities.Quiz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ff on 3.11.2015 �..
 */
public class InputHandler implements KeyListener {

        public InputHandler(Display display) {

            display.getCanvas().addKeyListener(this);
        }
        @Override
        public void keyTyped (KeyEvent e){
        }

        @Override
        public void keyPressed (KeyEvent e){
            System.out.println("JJJ");
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_RIGHT) {
                MainCharacter.isMovingRight = true;
            }

            if (keyCode == KeyEvent.VK_LEFT) {
                MainCharacter.isMovingLeft = true;
            }

            if (keyCode == KeyEvent.VK_UP) {
                MainCharacter.isMovingUp = true;
            }

            if (keyCode == KeyEvent.VK_DOWN) {
                MainCharacter.isMovingDown = true;
            }

            if(keyCode == KeyEvent.VK_A) {
                Quiz.pressedA = true;
            }

            if(keyCode == KeyEvent.VK_B) {
                Quiz.pressedB = true;
            }

            if(keyCode == KeyEvent.VK_C) {
                Quiz.pressedC = true;
            }
        }

        @Override
        public void keyReleased (KeyEvent e){
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_RIGHT) {
                MainCharacter.isMovingRight = false;
            }

            if (keyCode == KeyEvent.VK_LEFT) {
                MainCharacter.isMovingLeft = false;
            }

            if (keyCode == KeyEvent.VK_UP) {
                MainCharacter.isMovingUp = false;
            }

            if (keyCode == KeyEvent.VK_DOWN) {
                MainCharacter.isMovingDown = false;
            }

            if(keyCode == KeyEvent.VK_A) {
                Quiz.pressedA = false;
            }

            if(keyCode == KeyEvent.VK_B) {
                Quiz.pressedB = false;
            }

            if(keyCode == KeyEvent.VK_C) {
                Quiz.pressedC = false;
            }
        }
    }