package nl.tudelft.semgroup4;

import java.io.File;

import nl.tudelft.semgroup4.resources.Resources;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainApp extends StateBasedGame {
    
    public MainApp(String name) {
        super(name);
    }

    /**
     * The main method.
     * 
     * @param args
     *            String[] - Launcher arguments
     * @throws SlickException
     *             - If the Game Engine fails.
     */
    public static void main(String[] args) throws SlickException {
        Input.disableControllers();
        // Music music = new Music("src/main/resources/sound/pop.ogg");
        // music.loop();
        /**
         * Set the library path for the natives which we need to use for LWJGL. OSX compatibility
         * is fixed by checking the platform name against the string "macosx" and changing it to
         * "osx" if it matches. This is necessary because maven names the native folder for
         * "macosx" "osx".
         **/
        System.setProperty(
                "org.lwjgl.librarypath",
                new File(new File(System.getProperty("user.dir"), "target/natives"), (LWJGLUtil
                        .getPlatformName().equals("macosx")) ? "osx" : LWJGLUtil.getPlatformName())
                        .getAbsolutePath());
        try {
            AppGameContainer container = new AppGameContainer(new MainApp("Bubble Trouble"));

            container.setTargetFrameRate(60);
            container.setUpdateOnlyWhenVisible(true);
            container.setDisplayMode(1200, 800, false);
            container.setVSync(true);
            container.setSmoothDeltas(true);
            container.start();

        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        Resources.init();
        Settings.init();
        addState(new StartScreenState(new ResourcesWrapper()));
        addState(new OptionsState());
        addState(new ShopState(new ResourcesWrapper()));
        addState(new HighscoresState());
        addState(new GameEndedState());
        addState(new KeyBindState());
        enterState(States.StartScreenState);
    }
}
