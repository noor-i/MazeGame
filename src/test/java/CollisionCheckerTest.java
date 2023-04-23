import entity.Player;
import entity.monster.Monster;
import entity.monster.Zombie;
import maze.CollisionChecker;
import maze.KeyHandler;
import maze.MazeBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollisionCheckerTest {
    MazeBoard mazeBoard = new MazeBoard();
    KeyHandler keyHandler = new KeyHandler(mazeBoard);
    CollisionChecker collisionChecker = new CollisionChecker(mazeBoard);
    Player player = mazeBoard.player;

    @Test
    void playerFacingDoor() {
        // door information:
        //  - x: 38
        //  - y: 18

        // mapX = worldX / MazeBoard.CELL_SIZE
        int doorWorldX = 38 * MazeBoard.CELL_SIZE;
        int doorWorldY = 18 * MazeBoard.CELL_SIZE;

        // below door
        player.direction = "up";
        player.worldX = doorWorldX + MazeBoard.CELL_SIZE;
        player.worldY = doorWorldY;

        assertTrue(collisionChecker.checkdoor(player));

        // above door
        player.direction = "down";
        player.worldX = doorWorldX + MazeBoard.CELL_SIZE;
        player.worldY = doorWorldY - MazeBoard.CELL_SIZE;

        assertTrue(collisionChecker.checkdoor(player));

        // left of door
        player.direction = "right";
        player.worldX = doorWorldX + MazeBoard.CELL_SIZE;
        player.worldY = doorWorldY;

        assertTrue(collisionChecker.checkdoor(player));

        // right of door
        player.direction = "left";
        player.worldX = doorWorldX + MazeBoard.CELL_SIZE;
        player.worldY = doorWorldY - MazeBoard.CELL_SIZE;

        assertTrue(collisionChecker.checkdoor(player));
    }

    @Test
    void playerNotFacingDoor() {
        player.worldX = 0;
        player.worldY = 0;
        player.direction = "right";

        assertFalse(collisionChecker.checkdoor(player));
    }

    @Test
    void playerFacingWall() {
        // setup 3x3 grid where middle cell is a wall and rest is space
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mazeBoard.cellManager.mazeCellNum[i][j] = 0;
            }
        }

        mazeBoard.cellManager.mazeCellNum[1][1] = 4;

        int wallWorldX = MazeBoard.CELL_SIZE;
        int wallWorldY = MazeBoard.CELL_SIZE;

        // below wall
        player.direction = "up";
        player.worldX = wallWorldX;
        player.worldY = wallWorldY;
        assertTrue(collisionChecker.checkTile(player));

        // above door
        player.direction = "down";
        player.worldX = wallWorldX;
        player.worldY = 0;
        assertTrue(collisionChecker.checkTile(player));

        // left of door
        player.direction = "right";
        player.worldX = wallWorldX + MazeBoard.CELL_SIZE;
        player.worldY = wallWorldY + MazeBoard.CELL_SIZE;
        assertTrue(collisionChecker.checkTile(player));

        // right of door
        player.direction = "left";
        player.worldX = 0;
        player.worldY = wallWorldY + MazeBoard.CELL_SIZE;
        assertTrue(collisionChecker.checkTile(player));
    }

    @Test
    void playerNotFacingWall() {
        // setup 3x3 grid of empty space
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mazeBoard.cellManager.mazeCellNum[i][j] = 0;
            }
        }

        player.direction = "right";
        player.worldX = 0;
        player.worldY = 0;
        assertFalse(collisionChecker.checkTile(player));
    }

    @Test
    void playerOnEnemy() {
        player.worldX = 0;
        player.worldY = 0;

        for (Zombie z : mazeBoard.zombie) {
            z.worldX = 0;
            z.worldY = 0;
        }

        assertTrue(collisionChecker.isPlayerOnEnemy(player));

        for (Monster m : mazeBoard.monster) {
            m.worldX = 0;
            m.worldY = 0;
        }

        assertTrue(collisionChecker.isPlayerOnEnemy(player));
    }

    @Test
    void playerNotOnEnemy() {
        player.worldX = 0;
        player.worldY = 0;

        for (Zombie z : mazeBoard.zombie) {
            z.worldX = MazeBoard.CELL_SIZE;
            z.worldY = MazeBoard.CELL_SIZE;
        }

        assertFalse(collisionChecker.isPlayerOnEnemy(player));

        for (Monster m : mazeBoard.monster) {
            m.worldX = MazeBoard.CELL_SIZE;
            m.worldY = MazeBoard.CELL_SIZE;
        }

        assertFalse(collisionChecker.isPlayerOnEnemy(player));

    }

    @Test
    void playerOnLava() {
        // lava at (40, 21)
        player.worldX = 40 * MazeBoard.CELL_SIZE;
        player.worldY = 21 * MazeBoard.CELL_SIZE;

        assertTrue(collisionChecker.isLava(player));
    }

    @Test
    void playerNotOnLava() {
        player.worldX = 0;
        player.worldY = 0;
        assertFalse(collisionChecker.isLava(player));
    }
}
