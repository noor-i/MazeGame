/**

This is a superclass for skeleton and zombie classes.
It defines the common properties and methods for all monster entities in the game.
Monsters are placed on the MazeBoard and have a basic movement behavior.
*/
package entity.monster;

import maze.MazeBoard;
import java.awt.*;
import java.util.Random;
import entity.CellEntity;
import java.awt.image.BufferedImage;

public abstract class Monster extends CellEntity {
    protected MazeBoard mb;
    protected BufferedImage img;

    /**
     * Constructor for the Monster class.
     * Initializes the monster's properties and sets its initial position.
     *
     * @param mb The MazeBoard on which the monster is placed.
     */
    public Monster(MazeBoard mb) {
        this.mb = mb;
        actionLockCounter = 1;
        speed = 3;
        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        getImage();
        worldX = MazeBoard.CELL_SIZE * 21;
        worldY = MazeBoard.CELL_SIZE * 18;
        direction = "up";
    }

    /**
     * Abstract method for getting the monster's image.
     * To be implemented by subclasses.
     */
    public abstract void getImage();

    /**
     * Sets the monster's action to a random direction.
     */
    public void setAction() {
        Random random = new Random();
        int i = random.nextInt(100) + 1;
        if (i <= 25) {
            direction = "up";
        }
        if (i > 25 && i <= 50) {
            direction = "down";
        }
        if (i > 50 && i <= 75) {
            direction = "left";
        }
        if (i > 75 && i <= 100) {
            direction = "right";
        }

        actionLockCounter = 1;
    }

    /**
     * Updates the monster's position based on its current direction.
     * Handles collision detection and adjusts the monster's direction accordingly.
     */
    public void update() {
        collisionOn = mb.cChecker.checkTile(this);
        if (collisionOn == true) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
        }
        actionLockCounter++;
        if (actionLockCounter == 40) {
            setAction();
        }
        if (collisionOn == false) {
            switch (direction) {
                case ("up"):
                    worldY -= speed;
                    break;
                case ("down"):
                    worldY += speed;
                    break;
                case ("left"):
                    worldX -= speed;
                    break;
                case ("right"):
                    worldX += speed;
                    break;
            }
        }
        // Border checker
        if (worldX <= 400) {
            worldX = 400;
            img = right1;
            direction = "right";
        }
        if (worldY <= 300) {
            img = up1;
            worldY = 300;
            direction = "down";
        }
        if (worldX >= MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_ROWS - 400) {
            worldX = MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_ROWS - 400;
            img = left1;
            direction = "left";
        }
        if (worldY >= MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_COLUMNS - 300) {
            worldY = MazeBoard.CELL_SIZE * MazeBoard.MAX_WORLD_COLUMNS - 300;
            img = down1;
            direction = "up";
        }
    }
}