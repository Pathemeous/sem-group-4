package nl.tudelft.semgroup4.collision;

import java.util.Timer;
import java.util.TimerTask;

import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.AbstractWall;
import nl.tudelft.model.Game;
import nl.tudelft.model.MovingWall;
import nl.tudelft.model.Player;
import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.powerup.Hit3ShieldPowerup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.powerup.ShieldPowerup;
import nl.tudelft.model.pickups.utility.Utility;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.semgroup4.logger.LogSeverity;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

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
            if (objB instanceof AbstractWall) {
                bubbleWallHandler.onCollision(game, (Bubble) objA, (AbstractWall) objB);
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
            if (objB instanceof AbstractWall) {
                projectileWallHandler.onCollision(game, (Projectile) objA, (AbstractWall) objB);
            }
        }

        if (objA instanceof Player) {
            if (objB instanceof AbstractWall) {
                playerWallHandler.onCollision(game, (Player) objA, (AbstractWall) objB);
            }
        }
        
        if (objA instanceof Powerup) {
            if (objB instanceof Player) {
                playerPowerupHandler.onCollision(game, (Powerup)objA, (Player)objB );
            }
        }
        
        if (objA instanceof Weapon) {
            if (objB instanceof Player) {
                playerWeaponHandler.onCollision(game, (Weapon)objA, (Player)objB );
            }
        }
        
        if (objA instanceof Utility) {
            if (objB instanceof Player) {
                playerUtilityHandler.onCollision(game, (Utility)objA, (Player)objB );
            }
        }

        if (objA instanceof Pickup) {
            if (objB instanceof AbstractWall) {
                pickupWallHandler.onCollision(game, (Pickup) objA, (AbstractWall) objB);
            }
        }
        
        if (objA instanceof MovingWall) {
            if (objB instanceof AbstractWall) {
                wallWallHandler.onCollision(game, (MovingWall) objA, (AbstractWall) objB);
            }
        }
    }

    private final CollisionHandler<Player, AbstractWall> playerWallHandler = 
            (game, player, wall) -> {
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

    private final CollisionHandler<Bubble, AbstractWall> bubbleWallHandler = 
            (game, bubble, wall) -> {
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

        if (!player.isAlive()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision",
                    "Player is not alive, no collision checked");
            // nothing happens
            return;
        }
        if (player.isInvincible() || bubble.isFrozen()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player hit bubble, but is invincible");
            // nothing happens
            return;
        }
        if (player.hasShield()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player hit bubble, but has a shield");
            
            // The shield is removed and the bubble is split (tagged as isHit).
            ShieldPowerup shield = (ShieldPowerup)player.getPowerup(Powerup.SHIELD);
            if (!shield.isHit()) {
                shield.setHit(true);
                bubble.setIsHit();
            }
        } else if (player.hasShopShield()) {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision",
                    "Player hit bubble, but has a shopshield");
            Hit3ShieldPowerup shield = (Hit3ShieldPowerup)player.getPowerup(Powerup.SHOPSHIELD);
            if (!shield.isHit()) {
                shield.incrementHit();
                bubble.setIsHit();
            }
        } else {
            Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player hit bubble, and died");
            game.setPaused(true);
            new ResourcesWrapper().playDeath();
            
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    game.setPaused(false);
                    player.die();
                    game.levelReset();
                }
            }, 1000);
        }
    };

    final CollisionHandler<Projectile, AbstractWall> projectileWallHandler = 
            (game, projectile, wall) -> {
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
            Player player = projectile.getWeapon().getPlayer();
            player.setScore(player.getScore() + 50);
            bubble.setIsHit();
        }
    };

    final CollisionHandler<Pickup, AbstractWall> pickupWallHandler = (game, pickup, wall) -> {
        Game.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Pickup - Wall collision");
        
        final Shape pickupRect = pickup.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getY() >= pickupRect.getY()) {
            pickup.setLocY((int) (wallRect.getY() - pickupRect.getHeight()));
            pickup.setOnGround(true);
        }
    };
    
    final CollisionHandler<Powerup, Player> playerPowerupHandler = (game, powerup, player) -> {
        Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a powerup");
        powerup.activate(player);
    };
    
    final CollisionHandler<Weapon, Player> playerWeaponHandler = (game, weapon, player) -> {
        Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a new weapon");
        if (!player.isShopWeapon()) {
            weapon.activate(player);
        }
    };
    
    final CollisionHandler<Utility, Player> playerUtilityHandler = (game, util, player) -> {
        Game.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a utility");
        util.activate(game.getCurLevel());
    };
    
    private final CollisionHandler<MovingWall, AbstractWall> wallWallHandler = 
            (game, movingWall, abstractWall) -> {
        movingWall.setSpeed(-movingWall.getSpeed());
    };
}
