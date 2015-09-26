package nl.tudelft.semgroup4.util;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

public class Audio {
    public static boolean musicOn = true;
    private static final ResourcesWrapper RESOURCES = new ResourcesWrapper();

    /**
     * Loops the fire sound if the music is on.
     */
    public static void playFireSound() {
        if (musicOn) {
            RESOURCES.getWeaponFire().loop();
        }
    }

    /**
     * stops the sound of shooting.
     */
    public static void stopFireSound() {
        RESOURCES.getWeaponFire().stop();
    }

    /**
     * plays the sound of a bubble splitting.
     */
    public static void playBubbleSplit() {
        if (musicOn) {
            RESOURCES.getBubblePop().play();
        }

    }

    /**
     * plays the sound of time running out.
     */
    public static void playTimeUp() {
        if (musicOn) {
            RESOURCES.getTimeUp().play();
        }

    }

    /**
     * plays the sound of the titlescreen.
     */
    public static void playTitleScreen() {
        if (musicOn) {
            RESOURCES.getTitleScreenMusic().play();
        }

    }

    /**
     * stops the sound of the titlescreen.
     */
    public static void stopTitleScreen() {
        RESOURCES.getTitleScreenMusic().stop();

    }

    /**
     * plays the sound of the player dying.
     */
    public static void playDeath() {
        if (musicOn) {
            RESOURCES.getDeath().play();
        }

    }

    /**
     * Private constructor to avoid instantiation.
     */
    private Audio() {

    }
}
