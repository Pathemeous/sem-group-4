package nl.tudelft.controller.collision;

import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.Game;

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
        C1 extends AbstractGameObject,
        C2 extends AbstractGameObject> {

    /**
     * Handles the collision between two colliding parties.
     *
     * @param collider
     *            The collider.
     * @param collidee
     *            The collidee.
     */
    void handleCollision(Game game, C1 collider, C2 collidee);
}