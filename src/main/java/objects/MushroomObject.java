package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A class that represents an iron object in the game world.
 */
public class MushroomObject extends SuperObject {

    /**
     * Creates an MushroomObject instance.
     * Sets the name of the object, loads its image from file, and sets collision to true.
     */
    public MushroomObject() {
        name = "mushroom";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectsRe/mushroom.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
