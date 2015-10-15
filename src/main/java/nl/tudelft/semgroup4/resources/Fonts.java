package nl.tudelft.semgroup4.resources;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public final class Fonts {

    private static TrueTypeFont countdownFont;

    /**
     * Private constructor to avoid instantiation.
     */
    private Fonts() {
    }

    public static void init() {
        Font font = new Font("Calibri", Font.BOLD, 60);
        countdownFont = new TrueTypeFont(font, true);
    }

    static final TrueTypeFont getCountdownFont() {
        return countdownFont;
    }
}
