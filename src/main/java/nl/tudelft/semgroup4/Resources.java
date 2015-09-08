package nl.tudelft.semgroup4;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by justin on 08/09/15.
 */
public class Resources {

    private static boolean isInitted = false;

    public static Image wallImage;
    public static Image vwallImage;

    public static Image titleScreenBackground;
    public static Image backgroundImage;

    public static Image playerImageStill;
    public static Image playerImageLeft;
    public static Image playerImageRight;


    /**
     * initialises the resources
     */
    public static void init() throws SlickException {
        if (isInitted) {
            return;
        } else {
            isInitted = true;
        }

        wallImage =  new Image("src/main/resources/img/wall2_h.png");
        vwallImage =  new Image("src/main/resources/img/wall2_v.png");

        playerImageStill =  new Image("src/main/resources/img/player_still.png");
        playerImageLeft =  new Image("src/main/resources/img/player_left.png");
        playerImageRight =  new Image("src/main/resources/img/player_right.png");

        titleScreenBackground = new Image("src/main/resources/img/titleScreen2.png");
        backgroundImage = new Image("src/main/resources/img/level1.jpg");

    }

}
