package display;

import game.InputHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Margarita on 02-Nov-15.
 */
public class Display extends Canvas {
    private int width;
    private int hight;
    private String title;

    private InputHandler inputHandler;

    private JFrame frame;
    private Canvas canvas;


    public Display(String title, int width, int hight) {
        this.width = width;
        this.hight = hight;
        this.title = title;

        createFrame();
    }

    // class Canvas is public because we need to yoy from other classes
    public Canvas getCanvas() {
        return this.canvas;
    }

    private void createFrame() {
        frame = new JFrame(this.title);
        frame.setSize(this.width, this.hight);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(this.width, this.hight));
        canvas.setMaximumSize(new Dimension(this.width, this.hight));
        canvas.setMinimumSize(new Dimension(this.width, this.hight));
        this.inputHandler = new InputHandler(this);
        frame.addKeyListener(inputHandler);
        frame.add(canvas);
        frame.pack(); //packet all
    }
}
