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
    public static Image playerImageLeft3;
    public static Image playerImageLeft4;
    public static Image playerImageLeft5;
    public static Image playerImageLeft6;
    public static Image playerImageLeft7;
    public static Image playerImageLeft8;
    public static Image playerImageLeft9;
    public static Image playerImageLeft10;
    public static Image playerImageLeft11;
    public static Image playerImageLeft12;
    public static Image playerImageLeft13;
    public static Image playerImageLeft14;
    public static Image playerImageLeft15;
    public static Image playerImageLeft16;
    public static Image playerImageLeft17;
    public static Image playerImageLeft18;
    public static Image playerImageLeft19;
    public static Image playerImageLeft20;
    public static Image playerImageRight;
    public static Image playerImageRight2;
    public static Image playerImageRight3;
    public static Image playerImageRight4;
    public static Image playerImageRight5;
    public static Image playerImageRight6;
    public static Image playerImageRight7;
    public static Image playerImageRight8;
    public static Image playerImageRight9;
    public static Image playerImageRight10;
    public static Image playerImageRight11;
    public static Image playerImageRight12;
    public static Image playerImageRight13;
    public static Image playerImageRight14;
    public static Image playerImageRight15;
    public static Image playerImageRight16;
    public static Image playerImageRight17;
    public static Image playerImageRight18;
    public static Image playerImageRight19;
    public static Image playerImageRight20;

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
        playerImageLeft =  new Image("src/main/resources/img/pl/1.png");
        playerImageLeft2 = new Image("src/main/resources/img/pl/2.png");
        playerImageLeft3 = new Image("src/main/resources/img/pl/3.png");
        playerImageLeft4 = new Image("src/main/resources/img/pl/4.png");
        playerImageLeft5 = new Image("src/main/resources/img/pl/5.png");
        playerImageLeft6 = new Image("src/main/resources/img/pl/6.png");
        playerImageLeft7 = new Image("src/main/resources/img/pl/7.png");
        playerImageLeft8 = new Image("src/main/resources/img/pl/8.png");
        playerImageLeft9 = new Image("src/main/resources/img/pl/9.png");
        playerImageLeft10 = new Image("src/main/resources/img/pl/10.png");
        playerImageLeft11 = new Image("src/main/resources/img/pl/11.png");
        playerImageLeft12 = new Image("src/main/resources/img/pl/12.png");
        playerImageLeft13 = new Image("src/main/resources/img/pl/13.png");
        playerImageLeft14 = new Image("src/main/resources/img/pl/14.png");
        playerImageLeft15 = new Image("src/main/resources/img/pl/15.png");
        playerImageLeft16 = new Image("src/main/resources/img/pl/16.png");
        playerImageLeft17 = new Image("src/main/resources/img/pl/17.png");
        playerImageLeft18 = new Image("src/main/resources/img/pl/18.png");
        playerImageLeft19 = new Image("src/main/resources/img/pl/19.png");
        playerImageLeft20 = new Image("src/main/resources/img/pl/20.png");

        playerImageRight =  new Image("src/main/resources/img/pr/1.png");
        playerImageRight2 = new Image("src/main/resources/img/pr/2.png");
        playerImageRight3 = new Image("src/main/resources/img/pr/3.png");
        playerImageRight4 = new Image("src/main/resources/img/pr/4.png");
        playerImageRight5 = new Image("src/main/resources/img/pr/5.png");
        playerImageRight6 = new Image("src/main/resources/img/pr/6.png");
        playerImageRight7 = new Image("src/main/resources/img/pr/7.png");
        playerImageRight8 = new Image("src/main/resources/img/pr/8.png");
        playerImageRight9 = new Image("src/main/resources/img/pr/9.png");
        playerImageRight10 = new Image("src/main/resources/img/pr/10.png");
        playerImageRight11 = new Image("src/main/resources/img/pr/11.png");
        playerImageRight12 = new Image("src/main/resources/img/pr/12.png");
        playerImageRight13 = new Image("src/main/resources/img/pr/13.png");
        playerImageRight14 = new Image("src/main/resources/img/pr/14.png");
        playerImageRight15 = new Image("src/main/resources/img/pr/15.png");
        playerImageRight16 = new Image("src/main/resources/img/pr/16.png");
        playerImageRight17 = new Image("src/main/resources/img/pr/17.png");
        playerImageRight18 = new Image("src/main/resources/img/pr/18.png");
        playerImageRight19 = new Image("src/main/resources/img/pr/19.png");
        playerImageRight20 = new Image("src/main/resources/img/pr/20.png");

        playerWalkLeft = new Animation();
        playerWalkLeft.addFrame(playerImageLeft, 20);
        playerWalkLeft.addFrame(playerImageLeft2, 20);
        playerWalkLeft.addFrame(playerImageLeft3, 20);
        playerWalkLeft.addFrame(playerImageLeft4, 20);
        playerWalkLeft.addFrame(playerImageLeft5, 20);
        playerWalkLeft.addFrame(playerImageLeft6, 20);
        playerWalkLeft.addFrame(playerImageLeft7, 20);
        playerWalkLeft.addFrame(playerImageLeft8, 20);
        playerWalkLeft.addFrame(playerImageLeft9, 20);
        playerWalkLeft.addFrame(playerImageLeft10, 20);
        playerWalkLeft.addFrame(playerImageLeft11, 20);
        playerWalkLeft.addFrame(playerImageLeft12, 20);
        playerWalkLeft.addFrame(playerImageLeft13, 20);
        playerWalkLeft.addFrame(playerImageLeft14, 20);
        playerWalkLeft.addFrame(playerImageLeft15, 20);
        playerWalkLeft.addFrame(playerImageLeft16, 20);
        playerWalkLeft.addFrame(playerImageLeft17, 20);
        playerWalkLeft.addFrame(playerImageLeft18, 20);
        playerWalkLeft.addFrame(playerImageLeft19, 20);
        playerWalkLeft.addFrame(playerImageLeft20, 20);

        playerWalkRight = new Animation();
        playerWalkRight.addFrame(playerImageRight, 20);
        playerWalkRight.addFrame(playerImageRight2, 20);
        playerWalkRight.addFrame(playerImageRight3, 20);
        playerWalkRight.addFrame(playerImageRight4, 20);
        playerWalkRight.addFrame(playerImageRight5, 20);
        playerWalkRight.addFrame(playerImageRight6, 20);
        playerWalkRight.addFrame(playerImageRight7, 20);
        playerWalkRight.addFrame(playerImageRight8, 20);
        playerWalkRight.addFrame(playerImageRight9, 20);
        playerWalkRight.addFrame(playerImageRight10, 20);
        playerWalkRight.addFrame(playerImageRight11, 20);
        playerWalkRight.addFrame(playerImageRight12, 20);
        playerWalkRight.addFrame(playerImageRight13, 20);
        playerWalkRight.addFrame(playerImageRight14, 20);
        playerWalkRight.addFrame(playerImageRight15, 20);
        playerWalkRight.addFrame(playerImageRight16, 20);
        playerWalkRight.addFrame(playerImageRight17, 20);
        playerWalkRight.addFrame(playerImageRight18, 20);
        playerWalkRight.addFrame(playerImageRight19, 20);
        playerWalkRight.addFrame(playerImageRight20, 20);

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
