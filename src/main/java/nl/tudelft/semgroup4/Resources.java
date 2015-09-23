package nl.tudelft.semgroup4;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * This class holds the resources Created by justin on 08/09/15.
 */
public final class Resources {

    private static boolean isInitted = false;

    public static Image wallImage;
    public static Image vwallImage;

    public static Image weaponImageRegular;
    public static Image weaponImageSticky;
    public static Image weaponImageFlower;

    public static Image titleScreenBackground;
    public static Image backgroundImage;

    public static Image dashboardPlayerContainerLeft;
    public static Image dashboardPlayerContainerRight;
    public static Image dashboardLivesContainer;
    public static Image dashboardlivesFull;
    public static Image dashboardlivesEmpty;
    public static Image levelContainer;

    public static Image playerImageStill;
    
    public static Image on;
    public static Image off;
    public static Image soundText;
    public static Image optionsText;
    public static Image backText;
    
    public static Image quitText;
    public static Image pauseText;

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

    public static Image pickupWeaponRegular;
    public static Image pickupWeaponDouble;
    public static Image pickupWeaponSticky;
    public static Image pickupWeaponFlowers;
    public static Image pickupPowerShield;
    public static Image pickupPowerInvincible;
    public static Image pickupPowerSpeedup;
    public static Image pickupPowerPoints;
    public static Image pickupUtilitySplit;
    public static Image pickupUtilityFreeze;
    public static Image pickupUtilitySlow;
    public static Image pickupUtilityLevelwon;
    public static Image pickupUtilityTime;
    public static Image pickupUtilityLife;

    public static Image powerShield;
    public static Image powerInvincible;

    public static Sound bubblePop;
    public static Sound weaponFire;
    public static Sound death;
    public static Sound timeUp;

    public static Music titleScreenMusic;

    

    /**
     * Initialises the resources.
     * @throws SlickException - If the Game engine crashes.
     */
    public static void init() throws SlickException {
        if (isInitted) {
            return;
        } else {
            isInitted = true;
        }

        weaponFire = new Sound("src/main/resources/sound/weaponFire.ogg");
        bubblePop = new Sound("src/main/resources/sound/pop.ogg");
        death = new Sound("src/main/resources/sound/death.ogg");
        timeUp = new Sound("src/main/resources/sound/timeUp.ogg");

        titleScreenMusic = new Music("src/main/resources/sound/titleScreen.ogg");

        wallImage = new Image("src/main/resources/img/wall2_h.png");
        vwallImage = new Image("src/main/resources/img/wall2_v.png");

        playerImageStill = new Image("src/main/resources/img/player_still.png");
        playerImageLeft = new ArrayList<Image>();
        playerImageRight = new ArrayList<Image>();

        for (int i = 0; i < 20; i++) {
            playerImageLeft.add(new Image("src/main/resources/img/pl/" + (i + 1) + ".png"));
            playerImageRight.add(new Image("src/main/resources/img/pr/" + (i + 1) + ".png"));
        }

        playerWalkLeft = new Animation();
        playerWalkRight = new Animation();
        for (int i = 0; i < 20; i++) {
            playerWalkLeft.addFrame(playerImageLeft.get(i), 20);
            playerWalkRight.addFrame(playerImageRight.get(i), 20);
        }

        titleScreenBackground = new Image("src/main/resources/img/titleScreen2.png");
        backgroundImage = new Image("src/main/resources/img/level1.jpg");

        dashboardPlayerContainerLeft = new Image(
                "src/main/resources/img/dashboard/player_container_1.png");
        dashboardPlayerContainerRight = new Image(
                "src/main/resources/img/dashboard/player_container_2.png");
        dashboardLivesContainer = new Image("src/main/resources/img/dashboard/lives_container.png");
        dashboardlivesFull = new Image("src/main/resources/img/dashboard/lives_full.png");
        dashboardlivesEmpty = new Image("src/main/resources/img/dashboard/lives_empty.png");
        levelContainer = new Image("src/main/resources/img/dashboard/level_container.png");
        
        on = new Image("src/main/resources/img/on.png");
        off = new Image("src/main/resources/img/off.png");
        soundText = new Image("src/main/resources/img/sound.png");
        optionsText = new Image("src/main/resources/img/options.png");
        backText = new Image("src/main/resources/img/back.png");

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

        pickupWeaponRegular = new Image("src/main/resources/img/pickup_regular_weapon.png");
        pickupWeaponDouble = new Image("src/main/resources/img/pickup_weapon_double.png");
        pickupWeaponSticky = new Image("src/main/resources/img/pickup_sticky.png");
        pickupWeaponFlowers = new Image("src/main/resources/img/pickup_flowers.png");
        pickupPowerShield = new Image("src/main/resources/img/pickup_shield.png");
        pickupPowerInvincible = new Image("src/main/resources/img/pickup_invincible.png");
        pickupPowerPoints = new Image("src/main/resources/img/pickup_points.png");
        pickupPowerSpeedup = new Image("src/main/resources/img/pickup_speed.png");
        pickupUtilitySplit = new Image("src/main/resources/img/pickup_split.png");
        pickupUtilityFreeze = new Image("src/main/resources/img/pickup_freeze.png");
        pickupUtilitySlow = new Image("src/main/resources/img/pickup_slow_down.png");
        pickupUtilityLevelwon = new Image("src/main/resources/img/pickup_level_won.png");
        pickupUtilityTime = new Image("src/main/resources/img/pickup_time.png");
        pickupUtilityLife = new Image("src/main/resources/img/pickup_life.png");

        powerInvincible = new Image("src/main/resources/img/powerup_invincible.png");
        powerShield = new Image("src/main/resources/img/powerup_shield.png");
    }

}
