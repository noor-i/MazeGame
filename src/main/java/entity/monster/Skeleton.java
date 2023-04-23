/**

Skeleton class represents a specific type of monster in the game.
It extends the abstract Monster class and provides a custom image for the Skeleton monster.
*/
package entity.monster;

import maze.MazeBoard;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class Skeleton extends Monster {
        /**
         * Constructor for the Skeleton class.
         * Initializes the Skeleton monster by calling the superclass constructor.
         *
         * @param mb The MazeBoard on which the skeleton is placed.
         */
        public Skeleton(MazeBoard mb) {
                super(mb);
        }

        /**
         * Loads the Skeleton monster's images.
         * Overrides the getImage method from the abstract Monster class.
         */
        @Override
        public void getImage() {
                String[] imgNames = {
                                "Skeleton7", "Skeleton8", "Skeleton1", "Skeleton2",
                                "Skeleton3", "Skeleton4", "Skeleton5", "Skeleton6"
                };

                try {
                        for (int i = 0; i < imgNames.length; i++) {
                                BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/EnemiesRe/skeleton/" + imgNames[i] + ".png"));
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