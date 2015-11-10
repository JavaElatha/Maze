package game;

import java.awt.*;
import java.util.ArrayList;

public class Launcher {
    public static Game game;

    public static void main(String[] args) {
         game = new Game("Maze", 800, 600);

        game.start();
    }
}
