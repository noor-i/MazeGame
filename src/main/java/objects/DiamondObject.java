package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A class that represents a diamond object in the game world.
 */
public class DiamondObject extends SuperObject{

    /**
     * Creates a DiamondObject instance.
     * Sets the name of the object, loads its image from file, and sets collision to true.
     */
    public DiamondObject() {

        name = "diamond";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectsRe/diamond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
