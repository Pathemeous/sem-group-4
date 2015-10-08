package nl.tudelft.semgroup4.resources;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

/**
 * This class holds and loads the Slick2D resources.
 * @author Justin
 */
public final class Resources {

    private static boolean isInitted = false;

    static Image wallImage;
    static Image vwallImage;

    static Image weaponImageRegular;
    static Image weaponImageSticky;
    static Image weaponImageFlower;

    static Image titleScreenBackground;
    static Image backgroundImage;

    static Image dashboardPlayerContainerLeft;
    static Image dashboardPlayerContainerRight;
    static Image dashboardLivesContainer;
    static Image dashboardlivesFull;
    static Image dashboardlivesEmpty;
    static Image levelContainer;

    static Image playerImageStill;
    
    static Image on;
    static Image off;
    static Image soundText;
    static Image optionsText;
    static Image backText;
    static Image newKeyText;
    
    static Image quitText;
    static Image pauseText;
    
    static Image shopBackground;

    static ArrayList<Image> playerImageLeft;
    static ArrayList<Image> playerImageRight;

    static Animation playerWalkLeft;
    static Animation playerWalkRight;

    static Image bubbleImage1;
    static Image bubbleImage2;
    static Image bubbleImage3;
    static Image bubbleImage4;
    static Image bubbleImage5;
    static Image bubbleImage6;

    static Image pickupWeaponRegular;
    static Image pickupWeaponDouble;
    static Image pickupWeaponSticky;
    static Image pickupWeaponFlowers;
    static Image pickupPowerShield;
    static Image pickupPowerInvincible;
    static Image pickupPowerMoney;
    static Image pickupPowerSpeedup;
    static Image pickupPowerPoints;
    static Image pickupUtilitySplit;
    static Image pickupUtilityFreeze;
    static Image pickupUtilitySlow;
    static Image pickupUtilityLevelwon;
    static Image pickupUtilityTime;
    static Image pickupUtilityLife;

    static Image powerShield;
    static Image powerInvincible;

    static Sound bubblePop;
    static Sound weaponFire;
    static Sound death;
    static Sound timeUp;

    static Music titleScreenMusic;

    static Image continueText;
    static Image shopText;
    static Image player1On;
    static Image player1Off;
    static Image player2On;
    static Image player2Off;
    static Image shopImageSpecialWeapon;
    static Image buy;
    
    static TrueTypeFont countdownFont;
    
    /**
     * Private constructor to avoid instantiation of this utility class.
     */
    private Resources() {
        
    }

    

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
        newKeyText = new Image("src/main/resources/img/newKeyText.png");
                
        pauseText = new Image("src/main/resources/img/pausedText.png");
        quitText = new Image("src/main/resources/img/quitText.png");
        
        shopBackground = new Image("src/main/resources/img/shopBackground.png");
        continueText = new Image("src/main/resources/img/continue.png");
        shopText = new Image("src/main/resources/img/shop.png");
        player1On = new Image("src/main/resources/img/player1TextOn.png");
        player1Off = new Image("src/main/resources/img/player1TextOff.png");
        player2On = new Image("src/main/resources/img/player2TextOn.png");
        player2Off = new Image("src/main/resources/img/player2TextOff.png");
        shopImageSpecialWeapon = new Image("src/main/resources/img/pickup_weapon_special.png");
        buy = new Image("src/main/resources/img/buy.png");
        
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
        pickupPowerMoney = new Image("src/main/resources/img/pickup_money.png");
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
        
        Font font = new Font("Calibri", Font.BOLD, 60);
        countdownFont =  new TrueTypeFont(font, true);
    }

}
