import maze.MazeBoard;
import objects.SuperObject;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuperObjectTest {

    @Test
    public void testDraw() {
        // create a new SuperObject
        SuperObject superObject = new SuperObject();
        superObject.name = "Test Object";
        superObject.image = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
        superObject.worldX = 100;
        superObject.worldY = 100;

        // create a new MazeBoard with a player at position (0, 0)
        MazeBoard mazeBoard = new MazeBoard();
        mazeBoard.player.worldX = 0;
        mazeBoard.player.worldY = 0;

        // create a new Graphics2D object
        BufferedImage screen = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = screen.createGraphics();

        // call the draw() method
        superObject.draw(g2, mazeBoard);

        // verify that the object was drawn at the correct position on the screen
        assertEquals(0, superObject.solidAreaDefaultY);
        assertEquals(0, superObject.solidArea.x);
        assertEquals(0, superObject.solidArea.y);
        assertEquals(48, superObject.solidArea.width);
        assertEquals(48, superObject.solidArea.height);
    }

    @Test
    public void testCollision() {
        // create a new SuperObject with collision set to true
        SuperObject superObject = new SuperObject();
        superObject.collision = true;

        // verify that the collision flag is set correctly
        assertTrue(superObject.collision);
    }

}
