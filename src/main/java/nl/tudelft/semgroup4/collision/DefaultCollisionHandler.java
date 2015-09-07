package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.GameObject;
import nl.tudelft.model.Player;
import nl.tudelft.model.Wall;

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
    }

    final CollisionHandler<Player, Wall> playerWallHandler = (player, wall) -> {
        System.out.println("Player <-> wall collision");
        if (wall.x_location < player.x_location) {
            player.setX_location(wall.x_location + wall.getWidth());
        } else {
            player.setX_location(wall.x_location - player.getWidth());
        }
    };

}
