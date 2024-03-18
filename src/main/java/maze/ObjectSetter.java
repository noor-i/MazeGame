package maze;

import objects.DiamondObject;
import objects.IronObject;

/**
 * The ObjectSetter class is responsible for setting up the objects and enemies
 * in the game.
 * It places objects and sets the initial coordinates for enemies.
 */
public class ObjectSetter {

    private MazeBoard mb;

    /**
     * Constructs an ObjectSetter object with a reference to the MazeBoard instance.
     * 
     * @param mb The MazeBoard instance.
     */
    public ObjectSetter(MazeBoard mb) {
        this.mb = mb;
    }

    /**
     * Places diamond and iron objects onto the maze board using map coordinates.
     */
    public void setObject() {
        // SAND PIT DIAMOND
        mb.obj[0] = new DiamondObject();
        mb.obj[0].worldX = 8 * MazeBoard.CELL_SIZE;
        mb.obj[0].worldY = 10 * MazeBoard.CELL_SIZE;

        // IN THE LONELY WOODS DIAMOND
        mb.obj[1] = new DiamondObject();
        mb.obj[1].worldX = 38 * MazeBoard.CELL_SIZE;
        mb.obj[1].worldY = 35 * MazeBoard.CELL_SIZE;

        // POND DIAMOND
        mb.obj[2] = new DiamondObject();
        mb.obj[2].worldX = 8 * MazeBoard.CELL_SIZE;
        mb.obj[2].worldY = 23 * MazeBoard.CELL_SIZE;

        // ANOTHER ONE
        mb.obj[3] = new DiamondObject();
        mb.obj[3].worldX = 10 * MazeBoard.CELL_SIZE;
        mb.obj[3].worldY = 38 * MazeBoard.CELL_SIZE;

        // IRON 
        mb.obj[4] = new IronObject();
        mb.obj[4].worldX = 28 * MazeBoard.CELL_SIZE;
        mb.obj[4].worldY = 23 * MazeBoard.CELL_SIZE;

        mb.obj[5] = new IronObject();
        mb.obj[5].worldX = 22 * MazeBoard.CELL_SIZE;
        mb.obj[5].worldY = 36 * MazeBoard.CELL_SIZE;

        mb.obj[6] = new IronObject();
        mb.obj[6].worldX = 24 * MazeBoard.CELL_SIZE;
        mb.obj[6].worldY = 36 * MazeBoard.CELL_SIZE;

        mb.obj[7] = new IronObject();
        mb.obj[7].worldX = 26 * MazeBoard.CELL_SIZE;
        mb.obj[7].worldY = 36 * MazeBoard.CELL_SIZE;

        mb.obj[8] = new IronObject();
        mb.obj[8].worldX = 24 * MazeBoard.CELL_SIZE;
        mb.obj[8].worldY = 23 * MazeBoard.CELL_SIZE;

        mb.obj[9] = new IronObject();
        mb.obj[9].worldX = 26 * MazeBoard.CELL_SIZE;
        mb.obj[9].worldY = 23 * MazeBoard.CELL_SIZE;
    }

    /**
     * Sets the initial coordinates for all enemy entities in the game.
     */
    public void setEnemies() {
        mb.zombie[0].worldX = 38 * MazeBoard.CELL_SIZE;
        mb.zombie[0].worldY = 22 * MazeBoard.CELL_SIZE;

        mb.zombie[1].worldX = 20 * MazeBoard.CELL_SIZE;
        mb.zombie[1].worldY = 15 * MazeBoard.CELL_SIZE;

        mb.zombie[2].worldX = 20 * MazeBoard.CELL_SIZE;
        mb.zombie[2].worldY = 37 * MazeBoard.CELL_SIZE;

        mb.monster[0].worldX = 10 * MazeBoard.CELL_SIZE;
        mb.monster[0].worldY = 35 * MazeBoard.CELL_SIZE;

        mb.monster[1].worldX = 20 * MazeBoard.CELL_SIZE;
        mb.monster[1].worldY = 20 * MazeBoard.CELL_SIZE;

        mb.monster[2].worldX = 20 * MazeBoard.CELL_SIZE;
        mb.monster[2].worldY = 8 * MazeBoard.CELL_SIZE;
    }
}