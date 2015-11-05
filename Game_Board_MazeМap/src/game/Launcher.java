package game;

import display.Display;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Maze", 700, 480);
        game.start();
    }
}
