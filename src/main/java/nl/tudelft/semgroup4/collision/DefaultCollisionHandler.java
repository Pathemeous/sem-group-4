package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.Game;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Projectile;
import nl.tudelft.model.Wall;
import nl.tudelft.model.Weapon;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.PickupContent;
import nl.tudelft.model.pickups.Powerup;
import nl.tudelft.model.pickups.Utility;

import org.newdawn.slick.geom.Shape;


/**
 * The default collision handler for the game.
 * Collides player with walls, to prevent falling through them.
 *
 * Created by justin on 06/09/15.
 */
public class DefaultCollisionHandler implements CollisionHandler<Game, GameObject, GameObject> {

    @Override
    public void onCollision(Game game, GameObject objA, GameObject objB) {
        if (objA instanceof Bubble) {
        	if (objB instanceof Wall) {
        		bubbleWallHandler.onCollision(game, (Bubble)objA, (Wall)objB);
        	}
        }

        if (objA instanceof Bubble) {
            if (objB instanceof Player) {
                playerBubbleHandler.onCollision(game, (Bubble)objA, (Player)objB);
            }
        }
        
        if (objA instanceof Bubble) {
        	if (objB instanceof Projectile) {
        		projectileBubbleHandler.onCollision(game, (Bubble)objA, (Projectile)objB);
        	}
        }

        if (objA instanceof Projectile) {
            if (objB instanceof Wall) {
                projectileWallHandler.onCollision(game, (Projectile)objA, (Wall)objB);
            }
        }
        
        if (objA instanceof Player) {
            if (objB instanceof Wall) {
                playerWallHandler.onCollision(game, (Player)objA, (Wall)objB);
            }
        }
        
        if (objA instanceof Player) {
        	if (objB instanceof Pickup) {
        		playerPickupHandler.onCollision(game, (Player)objA, (Pickup)objB);
        	}
        }
        
        if (objA instanceof Pickup) {
        	if (objB instanceof Wall) {
        		pickupWallHandler.onCollision(game, (Pickup)objA, (Wall)objB);
        	}
        }
    }

    private final CollisionHandler<Game, Player, Wall> playerWallHandler = (game, player, wall) -> {
        //System.out.println("Player <-> wall collision");
        final Shape playerRect = player.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getX() < playerRect.getX()) {
            player.setLocX((int) (wallRect.getX() + wallRect.getWidth()));
        } else {
            player.setLocX((int) (wallRect.getX() - playerRect.getWidth()));
        }
    };
    
    private final CollisionHandler<Game, Bubble, Wall> bubbleWallHandler = (game, bubble, wall) -> {
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

    final CollisionHandler<Game, Bubble, Player> playerBubbleHandler = (game, bubble, player) -> {
        //System.out.println("Player <-> bubble collision");

        // TODO: Add code to reset the level.
    	if(player.isInvincible()) {
    		// nothing happens
    	} else if(player.hasShield()) {
    		player.removeShield();
    		System.out.println("Player collided with bubble but has a shield!");
    		// add code so the bubble splits when the player has a shield and is hit
    		bubble.setIsHit();
    	} else {
    		System.out.println("Player died");
    		player.removeLife();
    	}
    };
    
    final CollisionHandler<Game, Projectile, Wall> projectileWallHandler = (game, projectile, wall) -> {
        final Shape projectileRect = projectile.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getY() < projectileRect.getY()) {
            projectile.setHitWall();
        }
    };
    
    final CollisionHandler<Game, Bubble, Projectile> projectileBubbleHandler = (game, bubble, projectile) -> {
    	projectile.setHitBubble();
    	bubble.setIsHit();
    };
    
    final CollisionHandler<Game, Pickup, Wall> pickupWallHandler = (game, pickup, wall) -> {
    	final Shape pickupRect = pickup.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getY() >= pickupRect.getY()) {
            pickup.setLocY((int) (wallRect.getY() - pickupRect.getHeight()));
            pickup.setOnGround(true);
        }
    };
    
    final CollisionHandler<Game, Player, Pickup> playerPickupHandler = (game, player, pickup) -> {
        // TODO Add deletion of the pickup through the Game object.
        
    	PickupContent content = pickup.getContent();
    	if(content instanceof Weapon) {
    		// set new weapon
    		Weapon weapon = (Weapon)content;
    		System.out.println("Pickup a weapon of type: "+weapon.getType());
    		player.setWeapon(weapon);
    	} else if(content instanceof Powerup) {
    		Powerup powerup = (Powerup)content;
    		System.out.println("Pickup a power up of type: "+powerup.getPowerType());
    		
    		player.addPowerup(powerup);
    		// use powerup
    	} else {
    		System.out.println("Pickup a utility");
    		Utility util = (Utility)content;
    		// get level from player and apply utility
    	}
    };

}
