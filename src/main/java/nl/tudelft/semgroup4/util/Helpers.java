package nl.tudelft.semgroup4.util;

import java.util.Random;

public class Helpers {

    /**
     * Generates a random integer.
     * 
     * @param min
     *            int - minimum value
     * @param max
     *            int - maximum value
     * @return int - a random int
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
