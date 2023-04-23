/**

The Lava class represents a non-moving enemy in the game. It extends the CellEntity class
and implements the behavior of the Lava entity.
*/
package entity;

import maze.MazeBoard;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Lava extends CellEntity {
    MazeBoard mb;
    BufferedImage img;

    /**
     * Creates a new instance of the Lava enemy.
     * 
     * @param mb     the maze board where the Lava is placed
     * @param worldX the X coordinate of the Lava in the world
     * @param worldY the Y coordinate of the Lava in the world
     */
    public Lava(MazeBoard mb, int worldX, int worldY) {
        this.mb = mb;
        solidArea = new Rectangle(4, 18, 42, 30);
        getImage();
        this.worldX = MazeBoard.CELL_SIZE * worldX;
        this.worldY = MazeBoard.CELL_SIZE * worldY;
    }

    /**
     * Loads the image for the Lava enemy from the resources directory.
     */
    public void getImage() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/StaticEnemiesRe/lava.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the Lava enemy on the screen.
     * 
     * @param g2 the graphics object used to draw the entity
     * @param mb the maze board where the Lava is drawn
     */
    public void draw(Graphics2D g2, MazeBoard mb) {
        int screenX = worldX - mb.player.worldX + mb.player.getScreenX();
        int screenY = worldY - mb.player.worldY + mb.player.getScreenY();

        if (worldX + MazeBoard.CELL_SIZE > mb.player.worldX - mb.player.getScreenX()
                && worldX - MazeBoard.CELL_SIZE < mb.player.worldX + mb.player.getScreenX()
                && worldY + MazeBoard.CELL_SIZE > mb.player.worldY - mb.player.getScreenY()
                && worldY - MazeBoard.CELL_SIZE < mb.player.worldY + mb.player.getScreenY()) {
            g2.drawImage(img, screenX, screenY, MazeBoard.CELL_SIZE, MazeBoard.CELL_SIZE, null);
        }
    }

    /**
     * Updates the state of the Lava enemy.
     */
    public void update() {
    }

    /**
     * Returns the image used for the Lava enemy.
     * 
     * @return the image used for the Lava enemy
     */
    public BufferedImage returnImage() {
        return img;
    }
}