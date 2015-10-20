package nl.tudelft.controller.util;

import java.util.Random;

public final class Helpers {
    
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private static Random random = new Random();

    private Helpers() {
        
    }

    public static void setRandom(Random newRandom) {
        Helpers.random = newRandom;
    }

    /**
     * Generates a random integer.
     * 
     * @param min
     *            int - minimum value
     * @param max
     *            int - maximum value
     * @return int - a random int
     */
    public static final int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
