package maze;

import entity.CellEntity;

import java.awt.image.BufferedImage;

/**

 The Cell class represents a single cell in a maze.

 It contains information about the image, room number, cell type, cell entity, and row and column coordinates.
 */
public class Cell {

    /** The image of the cell */
    public BufferedImage image;

    /** The room number of the cell */
    public int roomNumber;

    /** The type of the cell */
    public CellType cellType;

    /** The entity occupying the cell */
    public CellEntity cellEntity;

    /** The row coordinate of the cell */
    public int row;

    /** The column coordinate of the cell */
    public int col;

    /**

     Constructs a Cell with default values.
     */
    public Cell(){
    }

    /**

     Constructs a Cell with the given row, column, cell type, image, and cell entity.
     @param row the row coordinate of the cell
     @param col the column coordinate of the cell
     @param cellType the type of the cell
     @param image the image of the cell
     @param cellEntity the entity occupying the cell
     */
    public Cell(int row, int col, CellType cellType, BufferedImage image, CellEntity cellEntity) {
        this.row = row;
        this.col = col;
        this.cellType = cellType;
        this.image = image;
        this.cellEntity = cellEntity;
    }
    /**

     Returns the row coordinate of the cell.
     @return the row coordinate of the cell
     */
    public int getRow() {
        return row;
    }
    /**

     Returns the column coordinate of the cell.
     @return the column coordinate of the cell
     */
    public int getCol() {
        return col;
    }
}