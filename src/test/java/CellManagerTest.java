import maze.CellManager;
import maze.MazeBoard;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CellManagerTest {

    private final CellManager cellManager = new CellManager();

    @Test
    void loadMap() {
        int[][] expected = new int[MazeBoard.MAX_WORLD_ROWS][MazeBoard.MAX_WORLD_COLUMNS];
        try (Scanner scanner = new Scanner(new FileInputStream("src/test/resources/Maps/Map.txt"))) {
            for (int row = 0; row < MazeBoard.MAX_WORLD_ROWS && scanner.hasNextLine(); row++) {
                String line = scanner.nextLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < MazeBoard.MAX_WORLD_COLUMNS; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    expected[row][col] = num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertArrayEquals(expected, cellManager.mazeCellNum);
    }


    @Test
    void getCellImage() throws IOException {
        BufferedImage grassImage = ImageIO.read(new File("src/main/resources/RoomsRe/grass.png"));
        BufferedImage wallImage = ImageIO.read(new File("src/main/resources/RoomsRe/wall.png"));
        BufferedImage waterImage = ImageIO.read(new File("src/main/resources/RoomsRe/water.png"));
        BufferedImage earthImage = ImageIO.read(new File("src/main/resources/RoomsRe/earth.png"));
        BufferedImage treeImage = ImageIO.read(new File("src/main/resources/RoomsRe/tree.png"));
        BufferedImage sandImage = ImageIO.read(new File("src/main/resources/RoomsRe/sand.png"));
        BufferedImage doorImage = ImageIO.read(new File("src/main/resources/RoomsRe/door.png"));

        assertEquals(grassImage.getWidth(), cellManager.cellTypes[0].image.getWidth());
        assertEquals(grassImage.getHeight(), cellManager.cellTypes[0].image.getHeight());

        assertEquals(wallImage.getWidth(), cellManager.cellTypes[1].image.getWidth());
        assertEquals(wallImage.getHeight(), cellManager.cellTypes[1].image.getHeight());

        assertEquals(waterImage.getWidth(), cellManager.cellTypes[2].image.getWidth());
        assertEquals(waterImage.getHeight(), cellManager.cellTypes[2].image.getHeight());

        assertEquals(earthImage.getWidth(), cellManager.cellTypes[3].image.getWidth());
        assertEquals(earthImage.getHeight(), cellManager.cellTypes[3].image.getHeight());

        assertEquals(treeImage.getWidth(), cellManager.cellTypes[4].image.getWidth());
        assertEquals(treeImage.getHeight(), cellManager.cellTypes[4].image.getHeight());

        assertEquals(sandImage.getWidth(), cellManager.cellTypes[5].image.getWidth());
        assertEquals(sandImage.getHeight(), cellManager.cellTypes[5].image.getHeight());

        assertEquals(doorImage.getWidth(), cellManager.cellTypes[6].image.getWidth());
        assertEquals(doorImage.getHeight(), cellManager.cellTypes[6].image.getHeight());

    }

    @Test
    void cellTypes() {
        // This test verifies that the cellTypes array has the correct length.
        assertEquals(7, cellManager.cellTypes.length);
    }

    @Test
    void mazeCellNum() {
        // This test verifies that the mazeCellNum array has the correct dimensions.
        assertEquals(MazeBoard.MAX_WORLD_COLUMNS, cellManager.mazeCellNum.length);
        assertEquals(MazeBoard.MAX_WORLD_ROWS, cellManager.mazeCellNum[0].length);
    }

    @Test
    void screenProperties() {
        // This test verifies that the screen properties are set correctly.
        assertEquals(MazeBoard.SCREEN_WIDTH, cellManager.getSCREEN_WIDTH());
        assertEquals(MazeBoard.SCREEN_HEIGHT, cellManager.getSCREEN_HEIGHT());
        assertEquals(MazeBoard.COLUMNS, cellManager.getCOLUMNS());
        assertEquals(MazeBoard.ROWS, cellManager.getROWS());
        assertEquals(MazeBoard.CELL_SIZE, cellManager.getCELL_SIZE());
    }

    @Test
    void wallCollision() {
        // This test verifies that walls have collision set to true.
        assertTrue(cellManager.cellTypes[1].collision);
        assertTrue(cellManager.cellTypes[2].collision);
        assertTrue(cellManager.cellTypes[4].collision);
    }
}
