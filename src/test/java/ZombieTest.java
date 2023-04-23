import entity.CellEntity;
import entity.monster.Zombie;
import maze.MazeBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ZombieTest {
    private MazeBoard mazeBoard;
    private Zombie zombie;

    @BeforeEach
    void setUp() {
        mazeBoard = new MazeBoard();
        zombie = new Zombie(mazeBoard);
    }

    @Test
    void testInitialDirection() {
        assertEquals("up", zombie.direction);
    }

    @Test
    void testImagesNotNull() {
        assertNotNull(zombie.up1);
        assertNotNull(zombie.up2);
        assertNotNull(zombie.down1);
        assertNotNull(zombie.down2);
        assertNotNull(zombie.left1);
        assertNotNull(zombie.left2);
        assertNotNull(zombie.right1);
        assertNotNull(zombie.right2);
    }

    @Test
    void testIsInstanceOfCellEntity() {
        assertTrue(zombie instanceof CellEntity);
    }

    @Test
    void testInitialSpeed() {
        assertEquals(3, zombie.speed);
    }

    @Test
    void testInitialSolidArea() {
        Rectangle expectedSolidArea = new Rectangle(4, 18, 42, 30);
        assertEquals(expectedSolidArea, zombie.solidArea);
    }
}
