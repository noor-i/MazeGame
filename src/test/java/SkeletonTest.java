import entity.CellEntity;
import entity.monster.Skeleton;
import maze.MazeBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SkeletonTest {
    private MazeBoard mazeBoard;
    private Skeleton skeleton;

    @BeforeEach
    void setUp() {
        mazeBoard = new MazeBoard();
        skeleton = new Skeleton(mazeBoard);
    }

    @Test
    void testInitialWorldPosition() {
        assertEquals(21 * MazeBoard.CELL_SIZE, skeleton.worldX);
        assertEquals(18 * MazeBoard.CELL_SIZE, skeleton.worldY);
    }

    @Test
    void testInitialDirection() {
        assertEquals("up", skeleton.direction);
    }

    @Test
    void testImagesNotNull() {
        assertNotNull(skeleton.up1);
        assertNotNull(skeleton.up2);
        assertNotNull(skeleton.down1);
        assertNotNull(skeleton.down2);
        assertNotNull(skeleton.left1);
        assertNotNull(skeleton.left2);
        assertNotNull(skeleton.right1);
        assertNotNull(skeleton.right2);
    }

    @Test
    void testIsInstanceOfCellEntity() {
        assertTrue(skeleton instanceof CellEntity);
    }

    @Test
    void testInitialActionLockCounter() {
        assertEquals(1, skeleton.actionLockCounter);
    }

    @Test
    void testInitialSpeed() {
        assertEquals(3, skeleton.speed);
    }

    @Test
    void testInitialSolidArea() {
        Rectangle expectedSolidArea = new Rectangle(4, 18, 42, 30);
        assertEquals(expectedSolidArea, skeleton.solidArea);
    }
}
