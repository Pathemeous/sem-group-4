package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Bubble;
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
        if (objA instanceof Player) {
            if (objB instanceof Wall) {
                playerWallHandler.onCollision((Player)objA, (Wall)objB);
            }
        }

        if (objA instanceof Player) {
            if (objB instanceof Bubble) {
                playerBubbleHandler.onCollision((Player)objA, (Bubble)objB);
            }
        }

        if (objA instanceof Projectile) {
            if (objB instanceof Wall) {
                projectileWallHandler.onCollision((Projectile)objA, (Wall)objB);
            }
        }
    }

    final CollisionHandler<Player, Wall> playerWallHandler = (player, wall) -> {
        System.out.println("Player <-> wall collision");
        final Shape playerRect = player.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getX() < playerRect.getX()) {
            player.setLocX((int) (wallRect.getX() + wallRect.getWidth()));
        } else {
            player.setLocX((int) (wallRect.getX() - playerRect.getWidth()));
        }
    };

    final CollisionHandler<Player, Bubble> playerBubbleHandler = (player, bubble) -> {
        System.out.println("Player <-> bubble collision");

        // TODO: Add code to reset the level.
        player.removeLife();
    };
    
    final CollisionHandler<Projectile, Wall> projectileWallHandler = (projectile, wall) -> {
        System.out.println("Projectile <-> wall collision");
        final Shape projectileRect = projectile.getBounds();
        final Shape wallRect = wall.getBounds();

        if (wallRect.getY() < projectileRect.getY()) {
            projectile.reset();
        }
    };

}
