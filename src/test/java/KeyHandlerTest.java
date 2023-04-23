import maze.KeyHandler;
import maze.MazeBoard;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeyHandlerTest {

    @Test
    public void testKeyPressed() {
        KeyHandler kh = new KeyHandler(new MazeBoard());
        KeyEvent ke1 = new KeyEvent(new FakeComponent(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        KeyEvent ke2 = new KeyEvent(new FakeComponent(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        kh.keyPressed(ke1);
        kh.keyPressed(ke2);
        assertTrue(kh.upPressed);
        assertTrue(kh.leftPressed);
        assertFalse(kh.downPressed);
        assertFalse(kh.rightPressed);
    }

    @Test
    public void testKeyReleased() {
        KeyHandler kh = new KeyHandler(new MazeBoard());
        KeyEvent ke1 = new KeyEvent(new FakeComponent(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        KeyEvent ke2 = new KeyEvent(new FakeComponent(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        kh.keyPressed(ke1);
        kh.keyPressed(ke2);
        kh.keyReleased(ke1);
        assertFalse(kh.upPressed);
        assertTrue(kh.leftPressed);
        assertFalse(kh.downPressed);
        assertFalse(kh.rightPressed);
    }

    // Helper class for creating fake components to pass to KeyEvents
    class FakeComponent extends java.awt.Component {
    }
}
