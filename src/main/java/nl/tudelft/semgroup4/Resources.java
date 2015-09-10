package nl.tudelft.semgroup4;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.ArrayList;

/**
 * This class holds the resources
 * Created by justin on 08/09/15.
 */
public class Resources {

    private static boolean isInitted = false;

    public static Image wallImage;
    public static Image vwallImage;
    
    public static Image weaponImageRegular;
    public static Image weaponImageSticky;
    public static Image weaponImageFlower;

    public static Image titleScreenBackground;
    public static Image backgroundImage;

    public static Image playerImageStill;
    
    public static Image quitText;
    public static Image pauseText;
    public static Image playerImageRight2;

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
    
    public static Image pickup_weapon_regular;
    public static Image pickup_weapon_double;
    public static Image pickup_weapon_sticky;
    public static Image pickup_weapon_flowers;
    public static Image pickup_power_shield;
    public static Image pickup_power_invincible;
    public static Image pickup_power_speedup;
    public static Image pickup_power_points;
    public static Image pickup_utility_split;
    public static Image pickup_utility_freeze;
    public static Image pickup_utility_slow;
    public static Image pickup_utility_levelwon;
    public static Image pickup_utility_time;
    public static Image pickup_utility_life;
    
    public static Image power_shield;
    public static Image power_invincible;
    
    public static Sound bubblePop;
    public static Sound weaponFire;

    /**
     * Initialises the resources.
     */
    public static void init() throws SlickException {
        if (isInitted) {
            return;
        } else {
            isInitted = true;
        }
        
        weaponFire =  new Sound("src/main/resources/sound/weaponFire.ogg");
        bubblePop = new Sound("src/main/resources/sound/pop.ogg");

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
        
        pauseText = new Image("src/main/resources/img/pausedText.png");
        quitText = new Image("src/main/resources/img/quitText.png");

        weaponImageRegular = new Image("src/main/resources/img/weapon_arrow.png");
        weaponImageSticky = new Image("src/main/resources/img/weapon_arrow.png");
        weaponImageFlower = new Image("src/main/resources/img/weapon_flowers.png");
        
        bubbleImage1 = new Image("src/main/resources/img/yball1.png");
        bubbleImage2 = new Image("src/main/resources/img/yball2.png");
        bubbleImage3 = new Image("src/main/resources/img/yball3.png");
        bubbleImage4 = new Image("src/main/resources/img/rball4.png");
        bubbleImage5 = new Image("src/main/resources/img/rball5.png");
        bubbleImage6 = new Image("src/main/resources/img/rball6.png");
        
        pickup_weapon_regular = new Image("src/main/resources/img/pickup_regular_weapon.png");
        pickup_weapon_double = new Image("src/main/resources/img/pickup_weapon_double.png");
        pickup_weapon_sticky = new Image("src/main/resources/img/pickup_sticky.png");
        pickup_weapon_flowers = new Image("src/main/resources/img/pickup_flowers.png");
        pickup_power_shield = new Image("src/main/resources/img/pickup_shield.png");
        pickup_power_invincible = new Image("src/main/resources/img/pickup_invincible.png");
        pickup_power_points = new Image("src/main/resources/img/pickup_points.png");
        pickup_power_speedup = new Image("src/main/resources/img/pickup_speed.png");
        pickup_utility_split = new Image("src/main/resources/img/pickup_split.png");
        pickup_utility_freeze = new Image("src/main/resources/img/pickup_freeze.png");
        pickup_utility_slow = new Image("src/main/resources/img/pickup_slow_down.png");
        pickup_utility_levelwon = new Image("src/main/resources/img/pickup_level_won.png");
        pickup_utility_time = new Image("src/main/resources/img/pickup_time.png");
        pickup_utility_life = new Image("src/main/resources/img/pickup_life.png");
        
        power_invincible = new Image("src/main/resources/img/powerup_invincible.png");
        power_shield = new Image("src/main/resources/img/powerup_shield.png");
    }

}
