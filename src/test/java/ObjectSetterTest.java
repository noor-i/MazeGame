import maze.MazeBoard;
import maze.ObjectSetter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ObjectSetterTest {

    private MazeBoard mb;

    @BeforeEach
    public void setup() {
        mb = new MazeBoard();
        mb.player.worldX = MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_ROWS / 2;
        mb.player.worldY = MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_COLUMNS / 2;
    }

    @Test
    public void testSetObject() {
        ObjectSetter os = new ObjectSetter(mb);
        os.setObject();
        assertNotNull(mb.obj);
        assertEquals(10, mb.obj.length);

        // Check that diamond objects were created and placed at the correct locations
        assertEquals(8 * MazeBoard.CELL_SIZE, mb.obj[0].worldX);
        assertEquals(10 * MazeBoard.CELL_SIZE, mb.obj[0].worldY);
        assertEquals("diamond", mb.obj[0].name);

        assertEquals(38 * MazeBoard.CELL_SIZE, mb.obj[1].worldX);
        assertEquals(35 * MazeBoard.CELL_SIZE, mb.obj[1].worldY);
        assertEquals("diamond", mb.obj[1].name);

        assertEquals(8 * MazeBoard.CELL_SIZE, mb.obj[2].worldX);
        assertEquals(23 * MazeBoard.CELL_SIZE, mb.obj[2].worldY);
        assertEquals("diamond", mb.obj[2].name);

        assertEquals(10 * MazeBoard.CELL_SIZE, mb.obj[3].worldX);
        assertEquals(38 * MazeBoard.CELL_SIZE, mb.obj[3].worldY);
        assertEquals("diamond", mb.obj[3].name);

        // Check that iron objects were created and placed at the correct locations
        assertEquals(28 * MazeBoard.CELL_SIZE, mb.obj[4].worldX);
        assertEquals(23 * MazeBoard.CELL_SIZE, mb.obj[4].worldY);
        assertEquals("mushroom", mb.obj[4].name);

        assertEquals(22 * MazeBoard.CELL_SIZE, mb.obj[5].worldX);
        assertEquals(36 * MazeBoard.CELL_SIZE, mb.obj[5].worldY);
        assertEquals("mushroom", mb.obj[5].name);

        assertEquals(24 * MazeBoard.CELL_SIZE, mb.obj[6].worldX);
        assertEquals(36 * MazeBoard.CELL_SIZE, mb.obj[6].worldY);
        assertEquals("mushroom", mb.obj[6].name);

        assertEquals(26 * MazeBoard.CELL_SIZE, mb.obj[7].worldX);
        assertEquals(36 * MazeBoard.CELL_SIZE, mb.obj[7].worldY);
        assertEquals("mushroom", mb.obj[7].name);

        assertEquals(24 * MazeBoard.CELL_SIZE, mb.obj[8].worldX);
        assertEquals(23 * MazeBoard.CELL_SIZE, mb.obj[8].worldY);
        assertEquals("mushroom", mb.obj[8].name);

        assertEquals(26 * MazeBoard.CELL_SIZE, mb.obj[9].worldX);
        assertEquals(23 * MazeBoard.CELL_SIZE, mb.obj[9].worldY);
        assertEquals("mushroom", mb.obj[9].name);
    }
}
