package nl.tudelft.semgroup4;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

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
    public static ArrayList<Image> playerImageLeft;
    public static ArrayList<Image> playerImageRight;

    public static Animation playerWalkLeft;
    public static Animation playerWalkRight;

    public static Image bubbleImage1;
    public static Image bubbleImage2;
    public static Image bubbleImage3;
    public static Image bubbleImage4;
    public static Image bubbleImage5;
    public static Image bubbleImage6;

    /**
     * Initialises the resources.
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
        playerImageLeft = new ArrayList<Image>();
        playerImageRight = new ArrayList<Image>();

        for(int i = 0; i < 20; i++) {
            playerImageLeft.add(new Image("src/main/resources/img/pl/" + (i+1) + ".png"));
            playerImageRight.add(new Image("src/main/resources/img/pr/" + (i+1) + ".png"));
        }

        playerWalkLeft = new Animation();
        playerWalkRight = new Animation();
        for(int i = 0; i < 20; i++) {
            playerWalkLeft.addFrame(playerImageLeft.get(i), 20);
            playerWalkRight.addFrame(playerImageRight.get(i), 20);
        }

        titleScreenBackground = new Image("src/main/resources/img/titleScreen2.png");
        backgroundImage = new Image("src/main/resources/img/level1.jpg");

        weaponImage = new Image("src/main/resources/img/arrow.png");

        bubbleImage1 = new Image("src/main/resources/img/yball1.png");
        bubbleImage2 = new Image("src/main/resources/img/yball2.png");
        bubbleImage3 = new Image("src/main/resources/img/yball3.png");
        bubbleImage4 = new Image("src/main/resources/img/rball4.png");
        bubbleImage5 = new Image("src/main/resources/img/rball5.png");
        bubbleImage6 = new Image("src/main/resources/img/rball6.png");

    }

}
