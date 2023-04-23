/**
 * The App class is the main class that runs the maze game. It implements the GUI in a high-level using java Swing.
 */
package maze;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class App {
    /**
     * The main method sets up the game window, initializes the maze board, and sets the background color based on
     * the system preference.
     *
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame window = new JFrame();
        MazeBoard mazeBoard = new MazeBoard();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.getContentPane().add(mazeBoard); // add the mazeBoard component to the content pane
        window.pack();
        window.setLocationRelativeTo(null);
        window.setTitle("Steve's Trippy Adventure");
        setBackgroundBasedOnSystemPreference(window);
        window.setVisible(true);
    }

    /**
     * Sets the background color of the given JFrame based on the system preference.
     *
     * @param frame the JFrame to set the background color for
     */
    public static void setBackgroundBasedOnSystemPreference(JFrame frame) {
        Color defaultBgColor = UIManager.getColor("control");

        if (UIManager.getLookAndFeel().getName().equals("Windows")) {
            frame.getContentPane().setBackground(defaultBgColor);
            frame.setBackground(defaultBgColor);
        } else if (System.getProperty("os.name").startsWith("Mac")) {
            // Change the window background color using AquaLookAndFeel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                JRootPane root = frame.getRootPane();
                root.putClientProperty("Window.style", "small");
                root.setBackground(defaultBgColor);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            frame.getContentPane().setBackground(defaultBgColor);
        } else {
            Color bg = defaultBgColor instanceof ColorUIResource ?
                    new Color(defaultBgColor.getRGB(), true) :
                    defaultBgColor;
            frame.getContentPane().setBackground(bg);
            frame.setBackground(bg);
        }
    }
}
