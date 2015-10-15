package nl.tudelft.semgroup4.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * This class holds and loads the Slick2D resources.
 * 
 * @author Justin
 */
public final class Resources {

    private static boolean isInitted = false;

    private static HashMap<String, Image> images = new HashMap<>();
    private static HashMap<String, Sound> sounds = new HashMap<>();
    private static HashMap<String, Music> music = new HashMap<>();
    private static HashMap<String, Animation> animations = new HashMap<>();

    
    /**
     * Private constructor to avoid instantiation of this utility class.
     */
    private Resources() {
    }
    /**
     * Initializes the resources.
     * 
     * @throws SlickException
     *             If the Game engine crashes.
     */
    public static void init() throws SlickException {
        if (isInitted) {
            return;
        } else {
            isInitted = true;
        }

        // Loads all the images, sounds, music and animations from resource folders.
        try {
            String fileSeparator = System.getProperty("file.separator");
            System.out.println(fileSeparator);
            loadImages(fileSeparator);
            loadSounds(fileSeparator);
            loadMusic(fileSeparator);
            loadAnimations(fileSeparator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that loops over all the files in the img folder, and makes an constructs a Slick image
     * from each file, storing the image in a hashmap, with the filepath as key.
     * 
     * @throws IOException
     *             If loading the files goes wrong.
     */
    private static void loadImages(String fileSeparator) throws IOException {
        Files.walk(Paths.get("src" + fileSeparator + "main" + fileSeparator + "resources" 
            + fileSeparator + "img")).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        try {
                            String fileString = filePath.toString();
                            int startIndex = fileString.indexOf("img");
                            String key = fileString.substring(startIndex);
                            
                            Image img = new Image(fileString);
                            images.put(key, img);
        
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * Method that loops over all the files in the sounds folder, and makes an constructs a Slick
     * sound from each file, storing the sound in a hashmap, with the filepath as key.
     * 
     * @throws IOException
     *             If loading the files goes wrong.
     */
    private static void loadSounds(String fileSeparator) throws IOException {
        Files.walk(Paths.get("src" + fileSeparator + "main" + fileSeparator + "resources" 
            + fileSeparator + "sound")).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        try {
                            String fileString = filePath.toString();
                            int startIndex = fileString.indexOf("sound");
                            String key = fileString.substring(startIndex);
                            
                            Sound img = new Sound(fileString);
                            sounds.put(key, img);
        
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * Method that loops over all the files in the music folder, and makes an constructs a Slick
     * music from each file, storing the music in a hashmap, with the filepath as key.
     * 
     * @throws IOException
     *             If loading the files goes wrong.
     */
    private static void loadMusic(String fileSeparator) throws IOException {
        Files.walk(Paths.get("src" + fileSeparator + "main" + fileSeparator + "resources" 
            + fileSeparator + "music")).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        try {
                            String fileString = filePath.toString();
                            int startIndex = fileString.indexOf("music");
                            String key = fileString.substring(startIndex);
                            
                            Music img = new Music(fileString);
                            music.put(key, img);
        
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * Method that loops over all the files in the animation folder, and makes an constructs a Slick
     * animation from the images in each inner folder, storing the animation in a hashmap, with the
     * filepath as key.
     * 
     * @throws IOException
     *             If loading the files goes wrong.
     */
    private static void loadAnimations(String fileSeparator) throws IOException {
        HashMap<String, HashMap<String, Image>> animationImages = new HashMap<>();

        Files.walk(Paths.get("src" + fileSeparator + "main" + fileSeparator + "resources" 
            + fileSeparator + "animation")).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        try {
                            String fileString = filePath.toString();
                            int startIndex = fileString.indexOf("animation");
                            String key = fileString.substring(startIndex);
                            
                            // Split the filePath with backslash as delimiter
                            String[] layers = key.split("\\\\");
                            
                            // Store the all but the filename of the image as key
                            String animationHash = "";
                            for (int i = 0; i < layers.length - 1; i++) {
                                if (animationHash.isEmpty()) {
                                    animationHash = layers[i];
                                } else {
                                    animationHash = animationHash + "\\" + layers[i];
                                }
                            }
            
                            // If there is no item which belongs to this folder, create one.
                            if (animationImages.get(animationHash) == null) {
                                animationImages.put(animationHash, new HashMap<>());
                            }
            
                            Image image = new Image(fileString);
            
                            // Add the image belonging to this path to the hashmap. The
                            // hashmap belongs to one of the folders in animation.
                            animationImages.get(animationHash).put(fileString, image);
        
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        makeAnimations(animationImages);
    }

    /**
     * Loops over each item of the hashmap. Each item consists of a hashmap, which contains images
     * belonging to one animation. The key of the outer hashmap is used as key to store the
     * animation.
     */
    private static void makeAnimations(HashMap<String, HashMap<String, Image>> animationImages) {
        // loop over all the items in the hashmap
        for (String key : animationImages.keySet()) {
            HashMap<String, Image> curImages = animationImages.get(key);
            Animation animation = new Animation();
            
            // Loop over all the items in the current map, where each item is an image.
            // Each image is added to the frame of the animation.
            for (int i = 0; i < curImages.size(); i++) {
                Image img = curImages.get("src\\main\\resources\\" + key + "\\"
                        + (i + 1) + ".png");
                animation.addFrame(img, 20);
            }

            // Store the animation in a hashmap, using the folder with the images for this
            // animation as the key.
            animations.put(key, animation);
        }
    }

    static final HashMap<String, Image> getImages() {
        return images;
    }

    static final HashMap<String, Sound> getSounds() {
        return sounds;
    }

    static final HashMap<String, Music> getMusic() {
        return music;
    }

    static final HashMap<String, Animation> getAnimations() {
        return animations;
    }

}
