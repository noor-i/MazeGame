package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A class that represents an iron object in the game world.
 */
public class IronObject extends SuperObject {

    /**
     * Creates an IronObject instance.
     * Sets the name of the object, loads its image from file, and sets collision to true.
     */
    public IronObject() {
        name = "iron";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectsRe/iron.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
