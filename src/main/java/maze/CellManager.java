package maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * This class manages the cells of the maze, loads the map, and draws the cells
 * on the screen.
 */
public class CellManager {

    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;
    private final int COLUMNS;
    private final int ROWS;

    /**
     * Returns the screen width.
     *
     * @return The screen width.
     */
    public int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    /**
     * Returns the screen height.
     *
     * @return The screen height.
     */
    public int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    /**
     * Returns the number of columns.
     *
     * @return The number of columns.
     */
    public int getCOLUMNS() {
        return COLUMNS;
    }

    /**
     * Returns the number of rows.
     *
     * @return The number of rows.
     */
    public int getROWS() {
        return ROWS;
    }

    /**
     * Returns the cell size.
     *
     * @return The cell size.
     */
    public int getCELL_SIZE() {
        return CELL_SIZE;
    }

    public int[][] getMazeCellNum() {
        return mazeCellNum;
    }

    public CellType[] getCellTypes() {
        return cellTypes;
    }

    private final int CELL_SIZE;

    public final int[][] mazeCellNum;
    public final CellType[] cellTypes;

    // The offset represents the brown space that we will use for keeping the score.
    private static final int NUM_CELL_TYPES = 7;

    /**
     * Constructor for CellManager class.
     * Initializes screen properties, cell types, and loads the map.
     */
    public CellManager() {
        // Screen Properties
        SCREEN_WIDTH = MazeBoard.SCREEN_WIDTH;
        SCREEN_HEIGHT = MazeBoard.SCREEN_HEIGHT;
        COLUMNS = MazeBoard.COLUMNS;
        ROWS = MazeBoard.ROWS;
        CELL_SIZE = MazeBoard.CELL_SIZE;

        cellTypes = new CellType[NUM_CELL_TYPES];

        mazeCellNum = new int[MazeBoard.MAX_WORLD_COLUMNS][MazeBoard.MAX_WORLD_ROWS];

        // Load image variations for cells
        getCellImage();
        loadMap();
    }

    /**
     * Loads the map matrix from src/main/resources/Maps/Map.txt.
     */
    public void loadMap() {

        try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/MapsRe/Map.txt"))) {
            for (int row = 0; row < MazeBoard.MAX_WORLD_ROWS && scanner.hasNextLine(); row++) {
                String line = scanner.nextLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < MazeBoard.MAX_WORLD_COLUMNS; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mazeCellNum[row][col] = num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves images from the resources directory and assigns them in a list.
     */
    public void getCellImage() {
        try {
            cellTypes[0] = new Path();
            cellTypes[0].image = ImageIO.read(getClass().getResourceAsStream("/RoomsRe/grass.png"));

            cellTypes[1] = new Wall();
            cellTypes[1].image = ImageIO.read(getClass().getResourceAsStream("/RoomsRe/wall.png"));
            cellTypes[1].collision = true;

// More images

            cellTypes[2] = new Wall();
            cellTypes[2].image = ImageIO.read(getClass().getResourceAsStream("/RoomsRe/water.png"));
            cellTypes[2].collision = true;

            cellTypes[3] = new Path();
            cellTypes[3].image = ImageIO.read(getClass().getResourceAsStream("/RoomsRe/earth.png"));

            cellTypes[4] = new Wall();
            cellTypes[4].image = ImageIO.read(getClass().getResourceAsStream("/RoomsRe/tree.png"));
            cellTypes[4].collision = true;

            cellTypes[5] = new Path();
            cellTypes[5].image = ImageIO.read(getClass().getResourceAsStream("/RoomsRe/sand.png"));

            cellTypes[6] = new Door();
            cellTypes[6].image = ImageIO.read(getClass().getResourceAsStream("/RoomsRe/door.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the cells on the screen.
     *
     * @param g2        The graphics object used for drawing.
     * @param mazeBoard The maze board to which the cells belong.
     */
    public void draw(Graphics2D g2, MazeBoard mazeBoard) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < MazeBoard.MAX_WORLD_COLUMNS && worldRow < MazeBoard.MAX_WORLD_ROWS) {
            int tileNum = mazeCellNum[worldCol][worldRow];

            int worldX = worldCol * CELL_SIZE;
            int worldY = worldRow * CELL_SIZE;
            int screenX = worldX - mazeBoard.player.worldX + mazeBoard.player.getScreenX();
            int screenY = worldY - mazeBoard.player.worldY + mazeBoard.player.getScreenY();

            // Limits for the character
            if (mazeBoard.player.worldX <= 400) {
                mazeBoard.player.worldX = 400;
            }
            if (mazeBoard.player.worldY <= 300) {
                mazeBoard.player.worldY = 300;
            }
            if (mazeBoard.player.worldX >= MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_ROWS - 400) {
                mazeBoard.player.worldX = MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_ROWS - 400;
            }
            if (mazeBoard.player.worldY >= MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_COLUMNS - 300) {
                mazeBoard.player.worldY = MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_COLUMNS - 300;
            }

            g2.drawImage(cellTypes[tileNum].image, screenX, screenY, CELL_SIZE, CELL_SIZE, null);
            worldCol++;

            if (worldCol == MazeBoard.MAX_WORLD_COLUMNS) {
                worldCol = 0;
                worldRow++;
            }

        }
        drawNonPlayableArea(g2);
    }

    /**
     * Draws the border of the screen.
     *
     * @param g2 The graphics object used for drawing.
     */
    public void drawNonPlayableArea(Graphics2D g2) {
        Cell outlineCell = new Cell();

        try {
            outlineCell.image = ImageIO.read(getClass().getResourceAsStream("/RoomsRe/outline.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Draw outline image along left and right edges
        int numberOfNonPlayableLeftRows = 3;
        for (int j = 0; j < numberOfNonPlayableLeftRows; j++) {
            for (int i = 0; i < ROWS; i++) {
                g2.drawImage(outlineCell.image, j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
            }
        }
        int numberOfNonPlayableRightRows = 2;
        for (int j = COLUMNS - numberOfNonPlayableRightRows; j < COLUMNS; j++) {
            for (int i = 0; i < ROWS; i++) {
                g2.drawImage(outlineCell.image, j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
            }
        } // Draw outline image along top and bottom edges
        int numberOfNonPlayableTopColumns = 2;
        for (int i = 0; i < numberOfNonPlayableTopColumns; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                g2.drawImage(outlineCell.image, j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
            }
        }

        int numberOfNonPlayableBottomColumns = 2;
        for (int i = ROWS - numberOfNonPlayableBottomColumns; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                g2.drawImage(outlineCell.image, j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
            }
        }
    }
}