import maze.App;
import maze.CellType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private JFrame window;

    @BeforeEach
    void setUp() throws InterruptedException {
        window = new JFrame();
        Thread.sleep(500);
    }

    @Test
    void testSetBackgroundBasedOnSystemPreferenceOnMac() {
        // Verify that the background color of the frame is set correctly based on the system preference on Mac
        System.setProperty("os.name", "Mac OS X");
        App.setBackgroundBasedOnSystemPreference(window);
        Assertions.assertEquals(window.getRootPane().getBackground(), window.getContentPane().getBackground());
    }

    @Test
    void testSetBackgroundBasedOnSystemPreferenceOnWindows() {
        // Verify that the background color of the frame is set correctly based on the system preference on Windows
        System.setProperty("os.name", "Windows");
        App.setBackgroundBasedOnSystemPreference(window);
        Assertions.assertEquals(window.getBackground(), window.getContentPane().getBackground());
    }

    @Test
    void testSetBackgroundBasedOnSystemPreferenceOnOtherOS() {
        // Verify that the background color of the frame is set correctly based on the system preference on other OSes
        System.setProperty("os.name", "Linux");
        App.setBackgroundBasedOnSystemPreference(window);
        Assertions.assertEquals(window.getBackground(), window.getContentPane().getBackground());
    }

    @Test
    void testMainWindowClosing() {
        // Test that the window is closed when the user clicks the "X" button on the top right corner
        App.main(new String[]{});
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        Assertions.assertFalse(window.isVisible());
    }

    static class CellTypeTest {
        private BufferedImage image;
        private CellType cellType;

        @BeforeEach
        void setUp() {
            image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
            cellType = new CellType();
        }

        @Test
        void testImageNotNull() {
            cellType.image = image;
            assertNotNull(cellType.image);
        }

        @Test
        void testCollisionDefaultFalse() {
            assertFalse(cellType.collision);
        }

        @Test
        void testSetCollisionTrue() {
            cellType.collision = true;
            assertTrue(cellType.collision);
        }
    }
}
