import entity.CellEntity;
import entity.Player;
import maze.KeyHandler;
import maze.MazeBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private MazeBoard mazeBoard;
    private KeyHandler keyHandler;
    private Player player;

    @BeforeEach
    void setUp() {
        mazeBoard = new MazeBoard();
        keyHandler = new KeyHandler(mazeBoard);
        player = new Player(mazeBoard, keyHandler);
    }

    @Test
    void testInitialWorldPosition() {
        assertEquals(23 * MazeBoard.CELL_SIZE, player.worldX);
        assertEquals(21 * MazeBoard.CELL_SIZE, player.worldY);
    }

    @Test
    void testInitialDirection() {
        assertEquals("down", player.direction);
    }

    @Test
    void testImagesNotNull() {
        assertNotNull(player.up1);
        assertNotNull(player.up2);
        assertNotNull(player.down1);
        assertNotNull(player.down2);
        assertNotNull(player.left1);
        assertNotNull(player.left2);
        assertNotNull(player.right1);
        assertNotNull(player.right2);
    }

    @Test
    void testIsInstanceOfCellEntity() {
        assertTrue(player instanceof CellEntity);
    }

    @Test
    void testInitialSpeed() {
        assertEquals(4, player.speed);
    }

    @Test
    void testInitialSolidArea() {
        Rectangle expectedSolidArea = new Rectangle(8, 16, 32, 32);
        assertEquals(expectedSolidArea, player.solidArea);
    }

    @Test
    void testInitialPoints() {
        assertEquals(0, player.getPoints());
    }
}
