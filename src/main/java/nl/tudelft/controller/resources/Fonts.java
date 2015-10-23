package nl.tudelft.controller.resources;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Fonts {
    
    private static TrueTypeFont typeFont;
    
    /**
     * Private constructor to make sure this class cannot be initialized.
     */
    private Fonts() {
        
    }
    
    static void init() {
        Font font = new Font("Calibri", Font.BOLD, 60);
        typeFont = new TrueTypeFont(font, true);
    }
    
    static TrueTypeFont getTypeFont() {
        return typeFont;
    }
}
