/**

The Player class represents the player entity in the game. It extends the CellEntity class
and implements the behavior of the player.
*/
package entity;

import maze.KeyHandler;
import maze.MazeBoard;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends CellEntity {
    private static final int DEFAULT_WORLD_X = MazeBoard.CELL_SIZE * 23;
    private static final int DEFAULT_WORLD_Y = MazeBoard.CELL_SIZE * 21;
    private static final int DEFAULT_SPEED = 4;
    private static final String DEFAULT_DIRECTION = "down";
    private final MazeBoard mazeBoard;
    private final KeyHandler keyH;
    private final int screenX, screenY;
    private boolean alive = true;
    private BufferedImage grave;
    private double points = 0;
    private boolean win;

    /**
     * Creates a new instance of the player entity.
     * 
     * @param mb   the maze board where the player is placed
     * @param keyH the keyboard handler used to control the player
     */
    public Player(MazeBoard mb, KeyHandler keyH) {
        win = false;
        this.mazeBoard = mb;
        this.keyH = keyH;

        screenX = MazeBoard.SCREEN_WIDTH / 2 - MazeBoard.CELL_SIZE / 2;
        screenY = MazeBoard.SCREEN_HEIGHT / 2 - MazeBoard.CELL_SIZE / 2;

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Loads the images for the player entity from the resources directory.
     */
    private void getPlayerImage() {
        String[] imgNames = { "boy_up_1", "boy_up_2", "boy_down_1", "boy_down_2",
                "boy_left_1", "boy_left_2", "boy_right_1", "boy_right_2", "grave" };

        try {
            for (int i = 0; i < imgNames.length; i++) {
                BufferedImage img = ImageIO.read(getClass().getResource("/PlayerRe/" + imgNames[i] + ".png"));

                if (i == 0) {
                    up1 = img;
                } else if (i == 1) {
                    up2 = img;
                } else if (i == 2) {
                    down1 = img;
                } else if (i == 3) {
                    down2 = img;
                } else if (i == 4) {
                    left1 = img;
                } else if (i == 5) {
                    left2 = img;
                } else if (i == 6) {
                    right1 = img;
                } else if (i == 7) {
                    right2 = img;
                } else if (i == 8) {
                    grave = img;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Sets the default values for the player entity.
     */
    private void setDefaultValues() {
        worldX = DEFAULT_WORLD_X;
        worldY = DEFAULT_WORLD_Y;
        speed = DEFAULT_SPEED;
        direction = DEFAULT_DIRECTION;
    }

    /**
     * Updates the state of the player entity.
     */
    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            direction = keyH.upPressed ? "up" : keyH.downPressed ? "down" : keyH.leftPressed ? "left" : "right";

            collisionOn = mazeBoard.cChecker.checkTile(this);
            pickUpObject(mazeBoard.cChecker.checkObject(this, true));

            if (!collisionOn) {
                if (direction.equals("up")) {
                    worldY -= speed;
                } else if (direction.equals("down")) {
                    worldY += speed;
                } else if (direction.equals("left")) {
                    worldX -= speed;
                } else if (direction.equals("right")) {
                    worldX += speed;
                }
            }


            updateSprite();
        }
    }

    /**
     * Updates the sprite of the player entity based on its direction.
     */
    private void updateSprite() {
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = spriteNum == 1 ? 2 : 1;
            spriteCounter = 0;
        }
    }

    /**
     * Picks up an object if the player entity is in the same tile as the object.
     * 
     * @param i the index of the object in the object array
     */
    private void pickUpObject(int i) {

        if (i != 999) {

            String objName = mazeBoard.obj[i].name;

            switch (objName) {
                case "diamond":
                    points = points + 15;
                    mazeBoard.obj[i] = null;
                    break;
                case "iron":
                    points = points + 10;
                    mazeBoard.obj[i] = null;
                    break;
            }
            mazeBoard.playMusic(1, false);
        }
    }

    /**
     * Draws the player entity on the screen.
     * 
     * @param g2 the graphics object used to draw
     */
    public void draw(Graphics2D g2) {
        BufferedImage image;

        if (direction.equals("down")) {
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
        } else if (direction.equals("up")) {
            if (spriteNum == 1) {
                image = up1;
            } else {
                image = up2;
            }
        } else {
            image = up2;
        }

        if (!alive) {
            image = grave;
        }

        g2.drawImage(image, screenX, screenY, MazeBoard.CELL_SIZE, MazeBoard.CELL_SIZE, null);

    }

    /**
     * Checks whether the player entity is alive.
     * 
     * @return true if the player is alive, false otherwise
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the state of the player entity to alive or dead.
     * 
     * @param alive true if the player is alive, false otherwise
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Sets the current number of points for the player entity.
     * 
     * @param points the new number of points
     */
    public void setPoints(double points) {
        this.points = points;
    }

    /**
     * Gets the current number of points for the player entity.
     * 
     * @return the number of points
     */
    public double getPoints() {
        return points;
    }

    /**
     * Checks whether the player entity has won the game.
     * 
     * @return true if the player has won, false otherwise
     */
    public boolean hasWon() {
        return win;
    }

    /**
     * Sets the state of the player entity to win or lose.
     * 
     * @param win true if the player has won, false otherwise
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * Gets the x-coordinate of the player entity on the screen.
     * 
     * @return the x-coordinate of the player entity on the screen
     */
    public int getScreenX() {
        return screenX;
    }

    /**
     * Gets the y-coordinate of the player entity on the screen.
     * 
     * @return the y-coordinate of the player entity on the screen
     */
    public int getScreenY() {
        return screenY;
    }
}