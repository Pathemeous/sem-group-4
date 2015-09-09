package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.BubbleManager;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Projectile;
import nl.tudelft.model.Wall;
import nl.tudelft.model.pickups.Pickup;

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
        
        if (objA instanceof Pickup) {
        	if (objB instanceof Wall) {
        		pickupWallHandler.onCollision((Pickup)objA, (Wall)objB);
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
    	//System.out.println("Bubble wall collision!");
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
        final Shape projectileRect = projectile.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getY() < projectileRect.getY()) {
        	//System.out.println("Projectile <-> wall collision");
            projectile.reset();
        }
    };
    
    final CollisionHandler<Bubble, Projectile> projectileBubbleHandler = (bubble, projectile) -> {
    	//System.out.println("Projectile <-> Bubble collision");
    	projectile.reset();
    	
    	BubbleManager manager = bubble.getBubbleManager();
    	if(!projectile.getHitBubble()) {
    		projectile.setHitBubble(true);
    		manager.remove(bubble);
    		
    		if(bubble.containsPickup()) {
    			bubble.getPickup().setInBubble(false);
    			bubble.getPickup().setLocX(bubble.getLocX());
    			bubble.getPickup().setLocY(bubble.getLocY());
    		}
        	
        	if(bubble.getSize() > 1) {
        		manager.create(bubble.getLocX(), bubble.getLocY(), bubble.getSize()-1, true);
        		manager.create(bubble.getLocX(), bubble.getLocY(), bubble.getSize()-1, false);
        	}
    	}
    };
    
    final CollisionHandler<Pickup, Wall> pickupWallHandler = (pickup, wall) -> {
    	final Shape pickupRect = pickup.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getY() >= pickupRect.getY()) {
            pickup.setLocY((int) (wallRect.getY() - pickupRect.getHeight()));
        }
    };

}
