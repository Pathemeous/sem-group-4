package nl.tudelft.controller.collision;

import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.GameObject;

/**
 * Handles the collision between two colliding parties.
 *
 * @author Michael de Jong
 *
 * @param <C1>
 *            The collider type.
 * @param <C2>
 *            The collidee type.
 */
public interface CollisionHandler<
        C1 extends GameObject,
        C2 extends GameObject> {

    /**
     * Handles the collision between two colliding parties.
     *
     * @param collider
     *            The collider.
     * @param collidee
     *            The collidee.
     */
    void handleCollision(AbstractGame game, C1 collider, C2 collidee);
}