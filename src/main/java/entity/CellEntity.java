/**

 The CellEntity class represents a superclass for zombie, skeleton, player, and lava entities.
 It contains common attributes and methods that these entities share.
 */
package entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import maze.MazeBoard;

public class CellEntity {
    // The variables worldX and worldY represent the position of the object in the world
    public int worldX, worldY;
    // Speed represents the speed at which the entity is moving.
    public int speed;

    // These are the set of images used on each entity
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    // Direction is a string variable that indicates where is the entity looking at
    public String direction;

    // SolidArea is the hitbox of the entity
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    // collision is a boolean variable that indicates whether the entity is prone to collision
    public boolean collisionOn = false;

    // The following methods helps us define the changing appearances of the entity
    public int actionLockCounter;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    /**
     * Draws the entity on the screen.
     * @param g2 the graphics object used to draw the entity
     * @param mb the maze board where the entity is drawn
     */
    public void draw(Graphics2D g2, MazeBoard mb) {
        int screenX = worldX - mb.player.worldX + mb.player.getScreenX();
        int screenY = worldY - mb.player.worldY + mb.player.getScreenY();

        if (worldX + MazeBoard.CELL_SIZE > mb.player.worldX - mb.player.getScreenX()
                && worldX - MazeBoard.CELL_SIZE < mb.player.worldX + mb.player.getScreenX()
                && worldY + MazeBoard.CELL_SIZE > mb.player.worldY - mb.player.getScreenY()
                && worldY - MazeBoard.CELL_SIZE < mb.player.worldY + mb.player.getScreenY()) {

            BufferedImage image;
            if (direction.equals("up")) {
                if (spriteNum == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
            } else if (direction.equals("down")) {
                if (spriteNum == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
            } else if (direction.equals("left")) {
                if (spriteNum == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
            } else if (direction.equals("right")) {
                if (spriteNum == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
            } else {
                image = up1;
            }

            g2.drawImage(image, screenX, screenY, MazeBoard.CELL_SIZE, MazeBoard.CELL_SIZE, null);
        }
    }

}