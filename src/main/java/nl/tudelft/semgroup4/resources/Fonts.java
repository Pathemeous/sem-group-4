package nl.tudelft.semgroup4.resources;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public abstract class Fonts {
    
    static TrueTypeFont countdownFont;
    
    public static void init() {
        Font font = new Font("Calibri", Font.BOLD, 60);
        countdownFont =  new TrueTypeFont(font, true);
    }
}
