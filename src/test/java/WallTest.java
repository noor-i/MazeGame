import maze.Wall;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    @Test
    void wallIsCollisionCell() {
        Wall wall = new Wall();
        wall.collision = true;
        assertTrue(wall.collision);
    }

    @Test
    void wallImageNotNull() throws IOException {
        Wall wall = new Wall();
        wall.image = ImageIO.read(getClass().getResource("/RoomsRe/wall.png"));
        assertNotNull(wall.image);
    }

    @Test
    void wallImageHasCorrectDimensions() throws IOException {
        Wall wall = new Wall();
        wall.image = ImageIO.read(getClass().getResource("/RoomsRe/wall.png"));
        assertEquals(16, wall.image.getWidth());
        assertEquals(16, wall.image.getHeight());
    }
}
