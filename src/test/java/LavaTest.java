import entity.Lava;
import maze.MazeBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LavaTest {
    private final int worldX = 2;
    private final int worldY = 3;
    private MazeBoard mazeBoard;
    private Lava testLava;

    @BeforeEach
    void setUp() {
        mazeBoard = new MazeBoard();
        testLava = new Lava(mazeBoard, worldX, worldY);
    }

    @Test
    void testInitialWorldPosition() {
        assertEquals(worldX * MazeBoard.CELL_SIZE, testLava.worldX);
        assertEquals(worldY * MazeBoard.CELL_SIZE, testLava.worldY);
    }

    @Test
    void testInitialSolidArea() {
        assertNotNull(testLava.solidArea);
        assertEquals(new Rectangle(4, 18, 42, 30), testLava.solidArea);
    }

    @Test
    void testInitialImage() {
        assertNotNull(testLava.returnImage());
    }
}
