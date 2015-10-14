package nl.tudelft.semgroup4.resources;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

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
    
    static HashMap<String, Image> images = new HashMap<>();
    static HashMap<String, Sound> sounds = new HashMap<>();
    static HashMap<String, Music> music = new HashMap<>();
    static HashMap<String, Animation> animations = new HashMap<>();

    static Image wallImage;
    static Image smallHWallImage;
    static Image vwallImage;
    static Image smallVWallImage;

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
    static Image keyBindingsText;
    
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
    static Image loggerText;
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
        
        try {
            Files.walk(Paths.get("src/main/resources/img")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        Image img = new Image(filePath.toString());
                        images.put(filePath.toString(), img);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
            Files.walk(Paths.get("src/main/resources/img")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        Image img = new Image(filePath.toString());
                        images.put(filePath.toString(), img);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        weaponFire = new Sound("src/main/resources/sound/weaponFire.ogg");
        bubblePop = new Sound("src/main/resources/sound/pop.ogg");
        death = new Sound("src/main/resources/sound/death.ogg");
        timeUp = new Sound("src/main/resources/sound/timeUp.ogg");

        titleScreenMusic = new Music("src/main/resources/music/titleScreen.ogg");

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
                
        

        
        Font font = new Font("Calibri", Font.BOLD, 60);
        countdownFont =  new TrueTypeFont(font, true);
    }

}
