import entity.CellEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CellEntityTest {
    private TestCellEntity testCellEntity;

    @BeforeEach
    void setUp() {
        testCellEntity = new TestCellEntity();
    }

    @Test
    void testInitialDirection() {
        assertEquals("up", testCellEntity.direction);
    }

    @Test
    void testInitialSolidArea() {
        assertNull(testCellEntity.solidArea);
    }

    @Test
    void testCollisionOn() {
        assertFalse(testCellEntity.collisionOn);
    }

    @Test
    void testActionLockCounter() {
        assertEquals(0, testCellEntity.actionLockCounter);
    }

    @Test
    void testSpriteCounter() {
        assertEquals(0, testCellEntity.spriteCounter);
    }

    @Test
    void testSpriteNum() {
        assertEquals(1, testCellEntity.spriteNum);
    }

    private static class TestCellEntity extends CellEntity {
        TestCellEntity() {
            direction = "up";
        }
    }
}
