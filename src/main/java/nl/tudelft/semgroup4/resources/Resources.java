package nl.tudelft.semgroup4.resources;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
            
            Files.walk(Paths.get("src/main/resources/sound")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        Sound img = new Sound(filePath.toString());
                        sounds.put(filePath.toString(), img);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
            Files.walk(Paths.get("src/main/resources/music")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        Music img = new Music(filePath.toString());
                        music.put(filePath.toString(), img);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
            HashMap<String, HashMap<String, Image>> animationImages = new HashMap<>();
            //LinkedList<String> animationHashes = new LinkedList<>();
            
            //animationImages.add(new HashMap<String, Image>());
            
            Files.walk(Paths.get("src/main/resources/animation")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        String fileString = filePath.toString();
                        String[] items= fileString.split("\\\\");
                        String animationHash = items[items.length - 2];
                        
                        if (animationImages.get(animationHash) == null) {
                            animationImages.put(animationHash, new HashMap<>());
                        }
                        
                        Image image = new Image(fileString);
                        
                        animationImages.get(animationHash).put(fileString, image);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
            for (String key : animationImages.keySet()) {
                HashMap<String, Image> curAnimationImages = animationImages.get(key);
                Animation animation = new Animation();
                
                for (int i  = 0; i < curAnimationImages.size(); i++) {
                    Image img = curAnimationImages.get("src\\main\\resources\\animation\\" 
                            + key + "\\" + (i + 1) + ".png");
                    animation.addFrame(img, 20);
                }
                
                animations.put(key, animation);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Fonts.init();
    }

}
