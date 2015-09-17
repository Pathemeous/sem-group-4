package nl.tudelft.semgroup4.util;

import nl.tudelft.semgroup4.Resources;

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

    public static void stopFireSound() {    
        Resources.weaponFire.stop();    
    }

    public static void playBubbleSplit() {
        if (musicOn) {
            Resources.bubblePop.play();
        }

    }

    public static void playTimeUp() {
        if (musicOn) {
            Resources.timeUp.play();
        }

    }

    public static void playTitleScreen() {
        if (musicOn) {
            Resources.titleScreenMusic.play();
        }
        
    }

    public static void stopTitleScreen() {
        Resources.titleScreenMusic.stop();
        
    }
}
