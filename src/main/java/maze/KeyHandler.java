package maze;

/**
 * KeyHandler class that implements KeyListener to handle key events for maze movement.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    // Variables to keep track of key presses
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    private MazeBoard mb;

    public KeyHandler(MazeBoard mb) {
        this.mb = mb;
    }

    /**
     * Invoked when a key has been typed.
     * 
     * @param e The KeyEvent object that contains information about the event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not implemented
    }

    /**
     * Invoked when a key has been pressed.
     * 
     * @param e The KeyEvent object that contains information about the event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Title State
        if (mb.gameState == mb.titleState) {
            if (code == KeyEvent.VK_ENTER) {
                mb.gameState = mb.playState;
            }
        }

        // Set the corresponding key press flag to true
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            if (mb.gameState != mb.overState && mb.gameState != mb.titleState) {
                if (mb.gameState == mb.playState) {
                    mb.gameState = mb.pauseState;
                } else if (mb.gameState == mb.pauseState) {
                    mb.gameState = mb.playState;
                }
            }
        }
    }

    /**
     * Invoked when a key has been released.
     * 
     * @param e The KeyEvent object that contains information about the event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // Set the corresponding key press flag to false
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

}
