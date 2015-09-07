package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
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

        if (objA instanceof Player) {
            if (objB instanceof Bubble) {
                playerBubbleHandler.onCollision((Player)objA, (Bubble)objB);
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

    final CollisionHandler<Player, Bubble> playerBubbleHandler = (player, bubble) -> {
        System.out.println("Player <-> bubble collision");

        // TODO: Add code to reset the level.
        player.removeLife();
    };

}
