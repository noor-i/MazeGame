/**

Zombie class represents a specific type of monster in the game.
It extends the abstract Monster class and provides a custom image for the Zombie monster.
*/
package entity.monster;

import maze.MazeBoard;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Zombie extends Monster {
        /**
         * Constructor for the Zombie class.
         * Initializes the Zombie monster by calling the superclass constructor.
         *
         * @param mb The MazeBoard on which the zombie is placed.
         */
        public Zombie(MazeBoard mb) {
                super(mb);
        }

        /**
         * Loads the Zombie monster's images.
         * Overrides the getImage method from the abstract Monster class.
         */
        @Override
        public void getImage() {
                String[] imgNames = {
                                "zombie7", "zombie8", "zombie1", "zombie2",
                                "zombie3", "zombie6", "zombie4", "zombie5"
                };

                try {
                        for (int i = 0; i < imgNames.length; i++) {
                                InputStream stream = getClass().getResourceAsStream("/EnemiesRe/zombie/" + imgNames[i] + ".png");
                                BufferedImage img = ImageIO.read(stream);
                                switch (i) {
                                        case 0:
                                                up1 = img;
                                                break;
                                        case 1:
                                                up2 = img;
                                                break;
                                        case 2:
                                                down1 = img;
                                                break;
                                        case 3:
                                                down2 = img;
                                                break;
                                        case 4:
                                                left1 = img;
                                                break;
                                        case 5:
                                                left2 = img;
                                                break;
                                        case 6:
                                                right1 = img;
                                                break;
                                        case 7:
                                                right2 = img;
                                                break;
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}