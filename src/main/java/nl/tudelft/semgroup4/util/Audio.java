package nl.tudelft.semgroup4.util;

import nl.tudelft.semgroup4.Resources.Resources;

public class Audio {
    public static boolean musicOn = true;

    /**
     * Loops the fire sound if the music is on.
     */
    public static void playFireSound() {
        if (musicOn) {
            Resources.weaponFire.loop();
        }
    }
    
    /**
     * stops the sound of shooting.
     */
    public static void stopFireSound() {    
        Resources.weaponFire.stop();    
    }
    
    /**
     * plays the sound of a bubble splitting.
     */
    public static void playBubbleSplit() {
        if (musicOn) {
            Resources.bubblePop.play();
        }

    }
    
    /**
     * plays the sound of time running out.
     */
    public static void playTimeUp() {
        if (musicOn) {
            Resources.timeUp.play();
        }

    }
    
    /**
     * plays the sound of the titlescreen.
     */
    public static void playTitleScreen() {
        if (musicOn) {
            Resources.titleScreenMusic.play();
        }

    }
    
    /**
     * stops the sound of the titlescreen.
     */
    public static void stopTitleScreen() {
        Resources.titleScreenMusic.stop();

    }
    
    /**
     * plays the sound of the player dying.
     */
    public static void playDeath() {
        if (musicOn) {
            Resources.death.play();
        }

    }
}
