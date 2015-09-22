package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.Game;
import nl.tudelft.model.Player;
import nl.tudelft.model.Wall;
import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.weapon.Projectile;
import nl.tudelft.model.weapon.Weapon;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.util.Audio;

import org.newdawn.slick.geom.Shape;

/**
 * The default collision handler for the game. Collides player with walls, to prevent falling
 * through them.
 */
public class DefaultCollisionHandler implements CollisionHandler<
        AbstractGameObject, AbstractGameObject> {

    @Override
    public void onCollision(Game game, AbstractGameObject objA, AbstractGameObject objB) {
        if (objA instanceof Bubble) {
            if (objB instanceof Wall) {
                bubbleWallHandler.onCollision(game, (Bubble) objA, (Wall) objB);
            }
        }

        if (objA instanceof Bubble) {
            if (objB instanceof Player) {
                playerBubbleHandler.onCollision(game, (Bubble) objA, (Player) objB);
            }
        }

        if (objA instanceof Bubble) {
            if (objB instanceof Projectile) {
                projectileBubbleHandler.onCollision(game, (Bubble) objA, (Projectile) objB);
            }
        }

        if (objA instanceof Projectile) {
            if (objB instanceof Wall) {
                projectileWallHandler.onCollision(game, (Projectile) objA, (Wall) objB);
            }
        }

        if (objA instanceof Player) {
            if (objB instanceof Wall) {
                playerWallHandler.onCollision(game, (Player) objA, (Wall) objB);
            }
        }

        if (objA instanceof Pickup) {
            if (objB instanceof Player) {
                playerPickupHandler.onCollision(game, (Pickup) objA, (Player) objB);
            }
        }

        if (objA instanceof Pickup) {
            if (objB instanceof Wall) {
                pickupWallHandler.onCollision(game, (Pickup) objA, (Wall) objB);
            }
        }
    }

    private final CollisionHandler<Player, Wall> playerWallHandler = (game, player, wall) -> {
        Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Player - wall collision");
        
        final Shape playerRect = player.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getX() < playerRect.getX()) {
            player.setLocX(player.getLocX()
                    + (wallRect.getX() + wallRect.getWidth() - playerRect.getX()));
        } else {
            player.setLocX(player.getLocX()
                    - (playerRect.getX() + playerRect.getWidth() - wallRect.getX()));
        }
    };

    private final CollisionHandler<Bubble, Wall> bubbleWallHandler = (game, bubble, wall) -> {
        float offset = bubble.getMaxSpeed();

        if (wall.getLocX() < bubble.getLocX()
                && (wall.getLocX() + wall.getBounds().getWidth() - offset) <= bubble.getLocX()) {
            // left collision
            Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", 
                    "Bubble collided with wall to its left");
            
            bubble.setHorizontalSpeed(Math.abs(bubble.getHorizontalSpeed()));
        } else if (wall.getLocY() < bubble.getLocY()
                && (wall.getLocY() + wall.getBounds().getHeight() - offset) <= bubble.getLocY()) {
            Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Bubble collided with wall above it");
            // top collision
            bubble.setVerticalSpeed(-Math.abs(bubble.getVerticalSpeed()));
        } else if ((wall.getLocY() + offset) >= bubble.getLocY()
                && (bubble.getLocX() + bubble.getBounds().getWidth()) >= wall.getLocX() + offset) {
            Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", 
                    "Bubble collided with wall underneath it");
            // bottom collision
            bubble.setVerticalSpeed(Math.abs(bubble.getMaxVerticalSpeed()));
        } else {
            // right collision
            Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", 
                    "Bubble collided with wall to its right");
            bubble.setHorizontalSpeed(-Math.abs(bubble.getHorizontalSpeed()));
        }
    };

    final CollisionHandler<Bubble, Player> playerBubbleHandler = (game, bubble, player) -> {
        Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", "bubble - player collision");
        // TODO: Add code to reset the level.

        if (player.isInvincible()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player hit bubble, but is invincible");
            // nothing happens
        } else if (player.hasShield()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player hit bubble, but has a shield");
            
            // The shield is removed and the bubble is split (tagged as isHit).
            if (!player.removingShield()) {
                player.setShieldInactive();
                bubble.setIsHit();
            }
        } else {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player hit bubble, and died");
            
            Audio.playDeath();
            player.removeLife();
            player.addScore(-1000);
            game.levelReset();
        }
    };

    final CollisionHandler<Projectile, Wall> projectileWallHandler = (game, projectile, wall) -> {
        Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Projectile - wall collision");
        
        final Shape projectileRect = projectile.getBounds();
        final Shape wallRect = wall.getBounds();
        // This structure makes the projectile ignore any walls below it (such as the floor wall).

        if (wallRect.getY() < projectileRect.getY()) {
            Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Projectile hit the ceiling");
            projectile.setHitWall();
        }
    };

    final CollisionHandler<Bubble, Projectile> projectileBubbleHandler =
            (game, bubble, projectile) -> {
        Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Bubble - Projectile collision");
                
        if (!projectile.isHitBubble()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", 
                    "Projectile hit bubble, and the bubble is split");
            projectile.setHitBubble();
            projectile.getWeapon().getPlayer().addScore(50);
            bubble.setIsHit();
        }
    };

    final CollisionHandler<Pickup, Wall> pickupWallHandler = (game, pickup, wall) -> {
        Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Pickup - Wall collision");
        
        final Shape pickupRect = pickup.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getY() >= pickupRect.getY()) {
            pickup.setLocY((int) (wallRect.getY() - pickupRect.getHeight()));
            pickup.setOnGround(true);
        }
    };

    final CollisionHandler<Pickup, Player> playerPickupHandler = (game, pickup, player) -> {
        game.getCurLevel().toRemove(pickup);

        if (pickup instanceof Weapon) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a new weapon");
            // set new weapon
            Weapon weapon = (Weapon) pickup;
            weapon.activate(player);
        }
//        } else if (pickup instanceof Powerup) {
//            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a powerup");
//            Powerup powerup = (Powerup) content;
//            player.addPowerup(powerup);
//        } else {
//            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a utility");
//            Utility util = (Utility) content;
//            game.getCurLevel().applyUtility(util);
//        }
    };

}
