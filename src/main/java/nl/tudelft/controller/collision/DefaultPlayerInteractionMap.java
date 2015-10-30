package nl.tudelft.controller.collision;

import java.util.Timer;
import java.util.TimerTask;

import nl.tudelft.controller.logger.LogSeverity;
import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.pickups.AbstractPickup;
import nl.tudelft.model.pickups.powerup.Hit3ShieldPowerup;
import nl.tudelft.model.pickups.powerup.Powerup;
import nl.tudelft.model.pickups.powerup.ShieldPowerup;
import nl.tudelft.model.pickups.utility.AbstractUtility;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.pickups.weapon.Weapon;
import nl.tudelft.model.player.Player;
import nl.tudelft.model.wall.AbstractMovingWall;
import nl.tudelft.model.wall.AbstractWall;
import nl.tudelft.model.wall.HorMovingWall;
import nl.tudelft.model.wall.RegularWall;
import nl.tudelft.model.wall.VerMovingWall;

import org.newdawn.slick.geom.Shape;

/**
 * An extensible default interaction map for collisions caused by the player.
 *
 * <p>The implementation makes use of the interactionmap, and as such can be easily
 * and declaratively extended when new types of AbstractGameObjects (ghosts, players, ...) are
 * added.</p>
 *
 */
public class DefaultPlayerInteractionMap implements CollisionMap {

    private CollisionMap collisions = defaultCollisions();

    @Override
    public void collide(AbstractGame game, GameObject mover, GameObject movedInto) {
        collisions.collide(game, mover, movedInto);
    }

    /**
     * Creates the default collisions for the bubble trouble game.
     *
     * @return The collision map containing the default collisions.
     */
    private static CollisionInteractionMap defaultCollisions() {
        CollisionInteractionMap collisionMap = new CollisionInteractionMap();

        collisionMap.onCollision(AbstractBubble.class, AbstractWall.class, false,
                bubbleWallHandler);
        collisionMap.onCollision(AbstractBubble.class, Player.class, false,
                playerBubbleHandler);
        collisionMap.onCollision(AbstractBubble.class, Projectile.class, false,
                projectileBubbleHandler);
        collisionMap.onCollision(AbstractWall.class, Player.class, false,
                wallPlayerHandler);
        collisionMap.onCollision(HorMovingWall.class, AbstractWall.class, false,
                horWallWallHandler);
        collisionMap.onCollision(VerMovingWall.class, AbstractWall.class, false,
                verWallWallHandler);
        collisionMap.onCollision(RegularWall.class, Projectile.class, false,
                wallProjectileHandler);
        collisionMap.onCollision(AbstractMovingWall.class, Projectile.class, false,
                movingwallProjectileHandler);
        collisionMap.onCollision(Powerup.class, Player.class, false,
                playerPowerupHandler);
        collisionMap.onCollision(Weapon.class, Player.class, false,
                playerWeaponHandler);
        collisionMap.onCollision(AbstractUtility.class, Player.class, false,
                playerUtilityHandler);
        collisionMap.onCollision(AbstractPickup.class, AbstractWall.class, false,
                pickupWallHandler);

        return collisionMap;
    }

    private static CollisionHandler<AbstractBubble, AbstractWall> bubbleWallHandler =
            (game, bubble, wall) -> {
        float offset = bubble.getMaxSpeed();

        if (wall.getLocX() < bubble.getLocX()
                && (wall.getLocX() + wall.getBounds().getWidth() - offset) <= bubble.getLocX()) {
            // left collision
            AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision",
                    "Bubble collided with wall to its left");

            bubble.setHorizontalSpeed(Math.abs(bubble.getHorizontalSpeed()));
        } else if (wall.getLocY() < bubble.getLocY()
                && (wall.getLocY() + wall.getBounds().getHeight() - offset) <= bubble.getLocY()) {
            AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision",
                    "Bubble collided with wall above it");
            // top collision
            bubble.setVerticalSpeed(-Math.abs(bubble.getVerticalSpeed()));
        } else if ((wall.getLocY() + offset) >= bubble.getLocY()
                && (bubble.getLocX() + bubble.getBounds().getWidth()) >= wall.getLocX() + offset) {
            AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision",
                    "Bubble collided with wall underneath it");
            // bottom collision
            bubble.setVerticalSpeed(Math.abs(bubble.getMaxVerticalSpeed()));
        } else {
            // right collision
            AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision",
                    "Bubble collided with wall to its right");
            bubble.setHorizontalSpeed(-Math.abs(bubble.getHorizontalSpeed()));
        }
    };

    private static CollisionHandler<AbstractBubble, Player> playerBubbleHandler
            = (game, bubble, player) -> {
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision", "bubble - player collision");

        if (!player.isAlive()) {
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision",
                    "Player is not alive, no collision checked");
            // nothing happens
            return;
        }
        if (player.isInvincible() || bubble.isFrozen()) {
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision",
                    "Player hit bubble, but is invincible");
            // nothing happens
            return;
        }
        if (player.hasShield()) {
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision",
                  "Player hit bubble, but has a shield");

            // The shield is removed and the bubble is split (tagged as isHit).
            ShieldPowerup shield = (ShieldPowerup)player.getPowerup(Powerup.SHIELD);
            if (!shield.isHit()) {
                shield.setHit(true);
                bubble.setIsHit();
            }
        } else if (player.hasShopShield()) {
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision",
                    "Player hit bubble, but has a shopshield");
            Hit3ShieldPowerup shield = (Hit3ShieldPowerup)player.getPowerup(Powerup.SHOPSHIELD);
            if (!shield.isHit()) {
                shield.incrementHit();
                bubble.setIsHit();
            }
        } else {
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player hit bubble, and died");
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

    private static CollisionHandler<AbstractBubble, Projectile> projectileBubbleHandler =
            (game, bubble, projectile) -> {
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Bubble - Projectile collision");

        if (!projectile.isHitBubble()) {
            AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision",
                    "Projectile hit bubble, and the bubble is split");
            projectile.setHitBubble();
            Player player = projectile.getWeapon().getPlayer();
            player.setScore(player.getScore() + 50);
            bubble.setIsHit();
        }
    };

    private static CollisionHandler<AbstractWall, Player> wallPlayerHandler =
            (game, wall, player) -> {
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Player - wall collision");

        Shape playerRect = player.getBounds();
        Shape wallRect = wall.getBounds();

        if (wallRect.getX() < playerRect.getX()) {
            player.setLocX(player.getLocX()
                    + (wallRect.getX() + wallRect.getWidth() - playerRect.getX()));
        } else {
            player.setLocX(player.getLocX()
                    - (playerRect.getX() + playerRect.getWidth() - wallRect.getX()));
        }
    };

    private static CollisionHandler<HorMovingWall, AbstractWall> horWallWallHandler =
            (game, movingWall, abstractWall) -> {
        float offset = Math.abs(movingWall.getSpeed()) * 2 + 1;

        // Check if the horizontalwall engages in a frontal collision with another wall.
        // If it has a frontal collision with an object to its right, it should move
        // to the left, else it should move to the left.
        // A small offset is used in checking the frontal collision, because there can be
        // a small overlap between two walls.
        if (movingWall.getLocX() < abstractWall.getLocX()
                && movingWall.getLocX() + movingWall.getWidth() < abstractWall.getLocX() + offset) {
            movingWall.setSpeed(-Math.abs(movingWall.getSpeed()));
        } else if (movingWall.getLocX() > abstractWall.getLocX()
                && movingWall.getLocX()
                > abstractWall.getLocX() + abstractWall.getWidth() - offset) {
            movingWall.setSpeed(Math.abs(movingWall.getSpeed()));
        }
    };

    private static CollisionHandler<VerMovingWall, AbstractWall> verWallWallHandler =
            (game, movingWall, abstractWall) -> {
        float offset = Math.abs(movingWall.getSpeed()) * 2 + 1;

        // Check if the verticalwall engages in a frontal collision with another wall.
        // If it has a frontal collision with an object below it, it should move
        // to upwards, else it should move downwards.
        // A small offset is used in checking the frontal collision, because there can be
        // a small overlap between two walls.
        if (movingWall.getLocY() < abstractWall.getLocY()
                && movingWall.getLocY() + movingWall.getHeight()
                < abstractWall.getLocY() + offset) {
            movingWall.setSpeed(-Math.abs(movingWall.getSpeed()));
        } else if (movingWall.getLocY() > abstractWall.getLocY()
                && movingWall.getLocY()
                > abstractWall.getLocY() + abstractWall.getHeight() - offset) {
            movingWall.setSpeed(Math.abs(movingWall.getSpeed()));
        }
    };

    private static CollisionHandler<RegularWall, Projectile> wallProjectileHandler =
            (game, wall, projectile) -> {
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Projectile - wall collision");

        Shape projectileRect = projectile.getBounds();
        Shape wallRect = wall.getBounds();
        // This structure makes the projectile ignore any walls below it (such as the floor wall).

        if (wallRect.getY() < projectileRect.getY()) {
            AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Projectile hit the ceiling");
            projectile.setHitWall();
        }
    };

    private static CollisionHandler<AbstractMovingWall, Projectile> movingwallProjectileHandler =
            (game, wall, projectile) -> {
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Projectile hit a moving wall");
        projectile.setHitWall();
    };

    private static CollisionHandler<Powerup, Player> playerPowerupHandler =
            (game, powerup, player) -> {
        AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a powerup");
        powerup.activate(player);
    };

    private static CollisionHandler<Weapon, Player> playerWeaponHandler =
            (game, weapon, player) -> {
        AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a new weapon");
        if (!player.isShopWeapon()) {
            weapon.activate(player);
        }
    };

    private static CollisionHandler<AbstractUtility, Player> playerUtilityHandler =
            (game, util, player) -> {
        AbstractGame.LOGGER.log(LogSeverity.DEBUG, "Collision", "Player picked up a utility");
        util.activate(game.getCurLevel());
    };

    private static CollisionHandler<AbstractPickup, AbstractWall> pickupWallHandler =
            (game, pickup, wall) -> {
        AbstractGame.LOGGER.log(LogSeverity.VERBOSE, "Collision", "Pickup - Wall collision");

        Shape pickupRect = pickup.getBounds();
        Shape wallRect = wall.getBounds();

        if (wallRect.getY() >= pickupRect.getY()) {
            pickup.setLocY((int) (wallRect.getY() - pickupRect.getHeight()));
            pickup.setOnGround(true);
        }
    };

}
