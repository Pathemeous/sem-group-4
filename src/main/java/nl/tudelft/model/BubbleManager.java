package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.semgroup4.Resources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class BubbleManager {
	
	private LinkedList<GameObject> bubbles, itemsToDelete, addedItems;
	
	public BubbleManager(LinkedList<GameObject> del, LinkedList<GameObject> add) {
		itemsToDelete = del;
		addedItems = add;
		bubbles = new LinkedList<>();
	}
	
	public void createBubbles(GameContainer container) {
		bubbles.add(new Bubble(Resources.bubbleImage6.copy(), Resources.vwallImage.getWidth() + 100, 
        		container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 6, this));

		bubbles.add(new Bubble(Resources.bubbleImage5.copy(), Resources.vwallImage.getWidth() + 200, 
        		container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 5, this));
        
		bubbles.add(new Bubble(Resources.bubbleImage4.copy(), Resources.vwallImage.getWidth() + 300, 
        		container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 4, this));
        
		bubbles.add(new Bubble(Resources.bubbleImage3.copy(), Resources.vwallImage.getWidth() + 400, 
        		container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 3, this));
        
		bubbles.add(new Bubble(Resources.bubbleImage2.copy(), Resources.vwallImage.getWidth() + 500, 
        		container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 2, this));
          
		bubbles.add(new Bubble(Resources.bubbleImage1.copy(), Resources.vwallImage.getWidth() + 600, 
        		container.getHeight() - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() -400, 1, this));
		
		addedItems.addAll(bubbles);
	}
	
	public void remove(Bubble bubble) {
		itemsToDelete.add(bubble);
	}
	
	public void create(float x, float y, int size, boolean goRight) {
		Image image;
		
		switch(size) {
		case 1: image = Resources.bubbleImage1; break;
		case 2: image = Resources.bubbleImage2; break;
		case 3: image = Resources.bubbleImage3; break;
		case 4: image = Resources.bubbleImage4; break;
		case 5: image = Resources.bubbleImage5; break;
		case 6: image = Resources.bubbleImage6; break;
		default: image = Resources.bubbleImage1;
		}
		
		Bubble bubble = new Bubble(image, x, y, size, this);
		
		bubble.setVerticalSpeed(bubble.getMaxVerticalSpeed()/1.5f);
		if(!goRight) {
			bubble.setHorizontalSpeed(-bubble.getHorizontalSpeed());
		}
		
		addedItems.add(bubble);
	}
}
