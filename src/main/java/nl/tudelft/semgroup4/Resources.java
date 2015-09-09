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
    
    public static Image quitText;
    public static Image pauseText;
    public static Image playerImageRight2;

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
        playerImageLeft =  new Image("src/main/resources/img/player_left.png");
        playerImageLeft2 = new Image("src/main/resources/img/player_left_1.png");
        playerImageRight =  new Image("src/main/resources/img/player_right.png");
        playerImageRight2 = new Image("src/main/resources/img/player_right_1.png");

        playerWalkLeft = new Animation();
        playerWalkLeft.addFrame(playerImageLeft, 4);
        playerWalkLeft.addFrame(playerImageLeft2, 4);
        playerWalkRight = new Animation();
        playerWalkRight.addFrame(playerImageRight, 4);
        playerWalkRight.addFrame(playerImageRight2, 4);

        titleScreenBackground = new Image("src/main/resources/img/titleScreen2.png");
        backgroundImage = new Image("src/main/resources/img/level1.jpg");
        
        pauseText = new Image("src/main/resources/img/pausedText.png");
        quitText = new Image("src/main/resources/img/quitText.png");

        weaponImage = new Image("src/main/resources/img/arrow.png");

        bubbleImage1 = new Image("src/main/resources/img/yball1.png");
        bubbleImage2 = new Image("src/main/resources/img/yball2.png");
        bubbleImage3 = new Image("src/main/resources/img/yball3.png");
        bubbleImage4 = new Image("src/main/resources/img/rball4.png");
        bubbleImage5 = new Image("src/main/resources/img/rball5.png");
        bubbleImage6 = new Image("src/main/resources/img/rball6.png");

    }

}
