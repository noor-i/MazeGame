import entity.CellEntity;
import maze.Cell;
import maze.CellType;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellTest {
    @Test
    public void testGetRow() {
        Cell cell = new Cell(0, 0, new CellType(), null, null);
        assertEquals(0, cell.getRow());
    }

    @Test
    public void testGetCol() {
        Cell cell = new Cell(0, 0, new CellType(), null, null);
        assertEquals(0, cell.getCol());
    }

    @Test
    public void testConstructor() {
        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        CellEntity entity = new CellEntity();
        CellType type = new CellType();
        Cell cell = new Cell(1, 2, type, image, entity);
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getCol());
        assertEquals(type, cell.cellType);
        assertEquals(image, cell.image);
        assertEquals(entity, cell.cellEntity);
    }

}
