package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Wall;
import nl.tudelft.model.Wall.WallType;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


/**
 * The default collision handler for the game.
 * Collides player with walls, to prevent falling through them.
 *
 * Created by justin on 06/09/15.
 */
public class DefaultCollisionHandler implements CollisionHandler<GameObject, GameObject> {

    @Override
    public void onCollision(GameObject objA, GameObject objB) {
        if (objA instanceof Player) {
            if (objB instanceof Wall) {
                playerWallHandler.onCollision((Player)objA, (Wall)objB);
            }
        }
        if (objA instanceof Bubble) {
        	if (objB instanceof Wall) {
        		bubbleWallHandler.onCollision((Bubble)objA, (Wall)objB);
        	}
        }
    }

    private final CollisionHandler<Player, Wall> playerWallHandler = (player, wall) -> {
        System.out.println("Player <-> wall collision");
        final Shape playerRect = player.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getX() < playerRect.getX()) {
            player.setLocX((int) (wallRect.getX() + wallRect.getWidth()));
        } else {
            player.setLocX((int) (wallRect.getX() - playerRect.getWidth()));
        }
    };
    
    private final CollisionHandler<Bubble, Wall> bubbleWallHandler = (bubble, wall) -> {
//    	if (wall.getWallType() == WallType.HORIZONTAL_WALL) {
//    		bubble.setVerticalSpeed(-1*bubble.getVerticalSpeed());
//    	} else {
//    		bubble.setHorizontalSpeed(-1*bubble.getHorizontalSpeed());
//    	}
    	System.out.println("Wall Y: "+wall.getLocY() + ", Wall X: "+wall.getLocX() + "Height: "+wall.getBounds().getHeight());
    	System.out.println("Bubble Y: "+bubble.getLocY() + ", Bubble X: "+bubble.getLocX() + "Height: "+bubble.getBounds().getHeight());
    	// left collision
    	if (wall.getLocX() < bubble.getLocX() && (wall.getLocX()+wall.getBounds().getWidth()) <= bubble.getLocX() ) {
    		System.out.println("LEft collision");
    		bubble.setHorizontalSpeed(-1*bubble.getHorizontalSpeed());
    	} // top collision
    	else if (wall.getLocY() < bubble.getLocY() && (wall.getLocY()+wall.getBounds().getHeight()) <= bubble.getLocY()) {
    		System.out.println("Top collision");
    		bubble.setVerticalSpeed(-1*bubble.getVerticalSpeed());
    	} // bottom collision
    	else if (wall.getLocY() >= bubble.getLocY() && (bubble.getLocX()+bubble.getBounds().getWidth()) >= wall.getLocX()) {
    		System.out.println("Bottom collision");
    		bubble.setVerticalSpeed(-1*bubble.getVerticalSpeed());
    	} // right collision
    	else {
    		System.out.println("Right collision");
    		bubble.setHorizontalSpeed(0);
    	}
    };

}
