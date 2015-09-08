package nl.tudelft.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wall extends GameObject {
	
	public enum WallType {
		HORIZONTAL_WALL, VERTICAL_WALL
	}
	
	private WallType type;

	public Wall(Image image, int locX, int locY, WallType type) {
		super(image, locX, locY);
		this.type = type;
	}
	
	public WallType getWallType() {
		return type;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

	}
	
//	public boolean isLeftSide(int x, int y) {
//		int leftTopX = getLocX();
//		
//		return leftTopX == x;
//	}
//	
//	public boolean isRightSide(int x, int y) {
//		int leftTopX = getLocX();
//		int width = getImage().getWidth();
//		
//		return (leftTopX +width) == x;
//	}
//	
//	public boolean isTop(int x, int y) {
//		int leftTopY = getLocY();
//		
//		return leftTopY == y;
//	}
//	
//	public boolean isBottom(int x, int y) {
//		int leftTopY = getLocY();
//		int height = getImage().getHeight();
//		
//		return (leftTopY + height) == y;
//	}

}
