package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.BubbleManager;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Projectile;
import nl.tudelft.model.Wall;

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
        if (objA instanceof Bubble) {
        	if (objB instanceof Wall) {
        		bubbleWallHandler.onCollision((Bubble)objA, (Wall)objB);
        	}
        }

        if (objA instanceof Bubble) {
            if (objB instanceof Player) {
                playerBubbleHandler.onCollision((Bubble)objA, (Player)objB);
            }
        }
        
        if (objA instanceof Bubble) {
        	if (objB instanceof Projectile) {
        		projectileBubbleHandler.onCollision((Bubble)objA, (Projectile)objB);
        	}
        }

        if (objA instanceof Projectile) {
            if (objB instanceof Wall) {
                projectileWallHandler.onCollision((Projectile)objA, (Wall)objB);
            }
        }
        
        if (objA instanceof Player) {
            if (objB instanceof Wall) {
                playerWallHandler.onCollision((Player)objA, (Wall)objB);
            }
        }
    }

    private final CollisionHandler<Player, Wall> playerWallHandler = (player, wall) -> {
        //System.out.println("Player <-> wall collision");
        final Shape playerRect = player.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getX() < playerRect.getX()) {
            player.setLocX((int) (wallRect.getX() + wallRect.getWidth()));
        } else {
            player.setLocX((int) (wallRect.getX() - playerRect.getWidth()));
        }
    };
    
    private final CollisionHandler<Bubble, Wall> bubbleWallHandler = (bubble, wall) -> {
    	float offset = bubble.getMaxSpeed();
    	
    	// left collision
    	if (wall.getLocX() < bubble.getLocX() && (wall.getLocX()+wall.getBounds().getWidth()-offset) <= bubble.getLocX() ) {
    		//System.out.println("Left collision");
    		bubble.setHorizontalSpeed(Math.abs(bubble.getHorizontalSpeed()));
    	} // top collision
    	else if (wall.getLocY() < bubble.getLocY() && (wall.getLocY()+wall.getBounds().getHeight()-offset) <= bubble.getLocY()) {
    		//System.out.println("Top collision");
    		bubble.setVerticalSpeed(-Math.abs(bubble.getVerticalSpeed()));
    	} // bottom collision
    	else if ((wall.getLocY()+offset) >= bubble.getLocY() && (bubble.getLocX()+bubble.getBounds().getWidth()) >= wall.getLocX()+offset) {
    		//System.out.println("Bottom collision");
    		bubble.setVerticalSpeed(Math.abs(bubble.getMaxVerticalSpeed()));
    	} // right collision
    	else {
    		//System.out.println("Right collision");
    		bubble.setHorizontalSpeed(-Math.abs(bubble.getHorizontalSpeed()));
    	}
    };

    final CollisionHandler<Bubble, Player> playerBubbleHandler = (bubble, player) -> {
        //System.out.println("Player <-> bubble collision");

        // TODO: Add code to reset the level.
        player.removeLife();
    };
    
    final CollisionHandler<Projectile, Wall> projectileWallHandler = (projectile, wall) -> {
        //System.out.println("Projectile <-> wall collision");
        final Shape projectileRect = projectile.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getY() < projectileRect.getY()) {
            projectile.reset();
        }
    };
    
    final CollisionHandler<Bubble, Projectile> projectileBubbleHandler = (bubble, projectile) -> {
    	System.out.println("Projectile <-> Bubble collision");
    	projectile.reset();
    	
    	BubbleManager manager = bubble.getBubbleManager();
    	manager.remove(bubble);
    	
    	if(bubble.getSize() > 1) {
    		manager.create(bubble.getLocX(), bubble.getLocY(), bubble.getSize()-1, true);
    		manager.create(bubble.getLocX(), bubble.getLocY(), bubble.getSize()-1, false);
    	}
    };

}
