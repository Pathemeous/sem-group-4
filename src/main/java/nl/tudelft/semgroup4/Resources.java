package nl.tudelft.semgroup4;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class holds the resources
 * Created by justin on 08/09/15.
 */
public class Resources {

    private static boolean isInitted = false;

    public static Image wallImage;
    public static Image vwallImage;
    public static Image weaponImage;

    public static Image titleScreenBackground;
    public static Image backgroundImage;

    public static Image playerImageStill;
    public static Image playerImageLeft;
    public static Image playerImageLeft2;
    public static Image playerImageRight;
    public static Image playerImageRight2;

    public static Animation playerWalkLeft;
    public static Animation playerWalkRight;
    public static Animation playerStill;


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
        playerImageLeft2 = new Image("src/main/resources/img/player_left_1.png");
        playerImageRight =  new Image("src/main/resources/img/player_right.png");
        playerImageRight2 = new Image("src/main/resources/img/player_right_1.png");

        titleScreenBackground = new Image("src/main/resources/img/titleScreen2.png");
        backgroundImage = new Image("src/main/resources/img/level1.jpg");

        weaponImage = new Image("src/main/resources/img/arrow.png");

        playerStill = new Animation();
        playerStill.addFrame(playerImageStill, 1);
        playerWalkLeft = new Animation();
        playerWalkLeft.addFrame(playerImageLeft, 4);
        playerWalkLeft.addFrame(playerImageLeft2, 4);
        playerWalkRight = new Animation();
        playerWalkRight.addFrame(playerImageRight, 4);
        playerWalkRight.addFrame(playerImageRight2, 4);

    }

}
