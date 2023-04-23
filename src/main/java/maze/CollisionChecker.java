package maze;

import entity.CellEntity;
import entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * CollisionChecker is a class used for detecting collisions between game
 * entities, tiles,
 * and objects in a maze game.
 */
public class CollisionChecker {
    MazeBoard mb;

    /**
     * Constructs a new CollisionChecker object.
     *
     * @param mazeBoard The MazeBoard object used for collision detection.
     */
    public CollisionChecker(MazeBoard mazeBoard) {
        this.mb = mazeBoard;
    }

    /**
     * Checks if the given CellEntity is stepping into the door.
     *
     * @param entity The CellEntity to check for collisions with the door.
     * @return true if the entity steps into the door, false otherwise.
     */
    public boolean checkdoor(CellEntity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / MazeBoard.CELL_SIZE;
        int entityRightCol = entityRightWorldX / MazeBoard.CELL_SIZE;
        int entityTopRow = entityTopWorldY / MazeBoard.CELL_SIZE;
        int entityBottomRow = entityBottomWorldY / MazeBoard.CELL_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / MazeBoard.CELL_SIZE;
                tileNum1 = mb.cellManager.mazeCellNum[entityLeftCol][entityTopRow];
                tileNum2 = mb.cellManager.mazeCellNum[entityRightCol][entityTopRow];
                if (tileNum1 == 6
                        || tileNum2 == 6) {
                    return true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / MazeBoard.CELL_SIZE;
                tileNum1 = mb.cellManager.mazeCellNum[entityLeftCol][entityBottomRow];
                tileNum2 = mb.cellManager.mazeCellNum[entityRightCol][entityBottomRow];
                if (tileNum1 == 6
                        || tileNum2 == 6) {
                    return true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / MazeBoard.CELL_SIZE;
                tileNum1 = mb.cellManager.mazeCellNum[entityLeftCol][entityTopRow];
                tileNum2 = mb.cellManager.mazeCellNum[entityLeftCol][entityBottomRow];
                if (tileNum1 == 6
                        || tileNum2 == 6) {
                    return true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / MazeBoard.CELL_SIZE;
                tileNum1 = mb.cellManager.mazeCellNum[entityRightCol][entityTopRow];
                tileNum2 = mb.cellManager.mazeCellNum[entityRightCol][entityBottomRow];
                if (tileNum1 == 6
                        || tileNum2 == 6) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Checks if the given CellEntity is currently stepping on a block with
     * collision activated.
     *
     * @param entity The CellEntity to check for collisions with the block.
     */
    public boolean checkTile(CellEntity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y + 15;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / MazeBoard.CELL_SIZE;
        int entityRightCol = entityRightWorldX / MazeBoard.CELL_SIZE;
        int entityTopRow = entityTopWorldY / MazeBoard.CELL_SIZE;
        int entityBottomRow = entityBottomWorldY / MazeBoard.CELL_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / MazeBoard.CELL_SIZE;
                tileNum1 = mb.cellManager.mazeCellNum[entityLeftCol][entityTopRow];
                tileNum2 = mb.cellManager.mazeCellNum[entityRightCol][entityTopRow];
                if (mb.cellManager.cellTypes[tileNum1].collision == true
                        || mb.cellManager.cellTypes[tileNum2].collision == true) {
                    return true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / MazeBoard.CELL_SIZE;
                tileNum1 = mb.cellManager.mazeCellNum[entityLeftCol][entityBottomRow];
                tileNum2 = mb.cellManager.mazeCellNum[entityRightCol][entityBottomRow];
                if (mb.cellManager.cellTypes[tileNum1].collision == true
                        || mb.cellManager.cellTypes[tileNum2].collision == true) {
                    return true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / MazeBoard.CELL_SIZE;
                tileNum1 = mb.cellManager.mazeCellNum[entityLeftCol][entityTopRow];
                tileNum2 = mb.cellManager.mazeCellNum[entityLeftCol][entityBottomRow];
                if (mb.cellManager.cellTypes[tileNum1].collision == true
                        || mb.cellManager.cellTypes[tileNum2].collision == true) {
                    return true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / MazeBoard.CELL_SIZE;
                tileNum1 = mb.cellManager.mazeCellNum[entityRightCol][entityTopRow];
                tileNum2 = mb.cellManager.mazeCellNum[entityRightCol][entityBottomRow];
                if (mb.cellManager.cellTypes[tileNum1].collision == true
                        || mb.cellManager.cellTypes[tileNum2].collision == true) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Checks if the given CellEntity collides with any object.
     *
     * @param entity The CellEntity to check for collisions with objects.
     * @param player true if the entity is a player, false otherwise.
     * @return the index of the object collided with if the entity is a player, or
     *         999 if there is no collision.
     */
    public int checkObject(CellEntity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < mb.obj.length; i++) {

            if (mb.obj[i] != null) {
                // get the entity's solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get the object's solid area pos
                mb.obj[i].solidArea.x = mb.obj[i].worldX + mb.obj[i].solidArea.x;
                mb.obj[i].solidArea.y = mb.obj[i].worldY + mb.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(mb.obj[i].solidArea)) {
                            if (mb.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(mb.obj[i].solidArea)) {
                            if (mb.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(mb.obj[i].solidArea)) {
                            if (mb.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(mb.obj[i].solidArea)) {
                            if (mb.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                mb.obj[i].solidArea.x = mb.obj[i].solidAreaDefaultX;
                mb.obj[i].solidArea.y = mb.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    /**
     * Checks if the given Player is on an enemy.
     *
     * @param player The Player to check for collisions with enemies.
     * @return true if the player is on an enemy, false otherwise.
     */
    public boolean isPlayerOnEnemy(Player player) {
        ArrayList<Point> points = new ArrayList<Point>();

        Arrays.stream(mb.zombie).forEach(enemy -> points.add(new Point(enemy.worldX, enemy.worldY)));
        Arrays.stream(mb.monster).forEach(enemy -> points.add(new Point(enemy.worldX, enemy.worldY)));

        // Arrays.stream(mazeBoard.lava).forEach(enemy -> points.add(new
        // Point(enemy.worldX, enemy.worldY)));

        for (Point point : points) {
            int x = Math.abs(point.x - player.worldX);
            int y = Math.abs(point.y - player.worldY);

            if (x < 25 && y < 25)
                return true;
        }

        return false;
    }

    /**
     * Checks if the given Player is on lava.
     *
     * @param player The Player to check for collisions with lava.
     * @return
     *         true if the player is on lava, false otherwise.
     */

    public boolean isLava(Player player) {
        ArrayList<Point> points = new ArrayList<Point>();
        Arrays.stream(mb.lava).forEach(enemy -> points.add(new Point(enemy.worldX, enemy.worldY)));
        for (Point point : points) {
            int x = Math.abs(point.x - player.worldX);
            int y = Math.abs(point.y - player.worldY);

            if (x < 25 && y < 25)
                return true;
        }
        return false;
    }
}