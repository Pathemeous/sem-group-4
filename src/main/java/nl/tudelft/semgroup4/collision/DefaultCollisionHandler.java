package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Projectile;
import nl.tudelft.model.Wall;
import org.newdawn.slick.geom.Rectangle;


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
        if (objA instanceof Projectile) {
            if (objB instanceof Wall) {
                projectileWallHandler.onCollision((Projectile)objA, (Wall)objB);
            }
        }
    }

    final CollisionHandler<Player, Wall> playerWallHandler = (player, wall) -> {
        System.out.println("Player <-> wall collision");
        final Rectangle playerRect = player.getBounds();
        final Rectangle wallRect = wall.getBounds();

        if (wallRect.getX() < playerRect.getX()) {
            player.setLocX((int) (wallRect.getX() + wallRect.getWidth()));
        } else {
            player.setLocX((int) (wallRect.getX() - playerRect.getWidth()));
        }
    };

    final CollisionHandler<Projectile, Wall> projectileWallHandler = (projectile, wall) -> {
        System.out.println("Projectile <-> wall collision");
        final Rectangle projectileRect = projectile.getBounds();
        final Rectangle wallRect = wall.getBounds();

        if (wallRect.getY() < 100 && wallRect.getY() < projectileRect.getY()) {
            projectile.reset();
        }
    };

}
