package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import maze.MazeBoard;

/**
 * A SuperObject class represents an object in the game world that has an image and can be collided with.
 */
public class SuperObject {

    /** The image of the object. */
    public BufferedImage image;
    
    /** The name of the object. */
    public String name;
    
    /** Indicates whether this object can be collided with or not. */
    public boolean collision = false;
    
    /** The world coordinates of the object. */
    public int worldX, worldY;
    
    /** The rectangle that represents the solid area of the object. */
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    
    /** The default x-coordinate of the solid area of the object. */
    public int solidAreaDefaultX = 0;
    
    /** The default y-coordinate of the solid area of the object. */
    public int solidAreaDefaultY = 0;

    /**
     * Renders the object and the maze on the screen.
     * 
     * @param g2 the graphics context
     * @param mb the maze board layout
     */
    public void draw(Graphics2D g2, MazeBoard mb) {

        // Calculate the position of the object on the screen based on its world coordinates and the player's position.
        int screenX = worldX - mb.player.worldX + mb.player.getScreenX();
        int screenY = worldY - mb.player.worldY + mb.player.getScreenY();

        // Keep the player within the bounds of the game world.
        if (mb.player.worldX <= 400) {
            mb.player.worldX = 400;
        }
        if (mb.player.worldY <= 300) {
            mb.player.worldY = 300;
        }
        if (mb.player.worldX >= MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_ROWS - 400) {
            mb.player.worldX = MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_ROWS - 400;
        }
        if (mb.player.worldY >= MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_COLUMNS - 300) {
            mb.player.worldY = MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_COLUMNS - 300;
        }

        // Draw the image of the object on the screen.
        g2.drawImage(image, screenX, screenY, MazeBoard.CELL_SIZE, MazeBoard.CELL_SIZE, null);
    }

}

