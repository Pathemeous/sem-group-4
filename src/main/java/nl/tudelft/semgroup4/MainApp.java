package nl.tudelft.semgroup4;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;

public class MainApp extends StateBasedGame {
	
	 public MainApp(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "target/natives"), LWJGLUtil.getPlatformName()).getAbsolutePath());
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
		addState(new StartScreenState());
		addState(new GameState(this.getTitle()));
		enterState(0);
		
	}
}