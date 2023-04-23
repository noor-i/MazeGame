package maze;

import java.awt.image.BufferedImage;

/**
 * Represents a type of cell that can be present in a maze.
 */
public class CellType {
    /**
     * The graphical representation of the cell type.
     */
    public BufferedImage image;

    /**
     * Indicates whether the cell type is a collision cell (i.e., the player cannot pass through it).
     */
    public boolean collision = false;

}
