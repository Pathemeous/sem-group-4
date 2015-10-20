package nl.tudelft.controller.collision;

import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.Game;

/**
 * A table containing all (relevant) collisions between different types of
 * AbstractGameObjects.
 * 
 * @author Jeroen Roosen 
 */
public interface CollisionMap {

    /**
     * Collides the two AbstractGameObjects and handles the result of the collision, which may
     * be nothing at all.
     * 
     * @param <C1>
     *            The collider type.
     * @param <C2>
     *            The collidee (AbstractGameObject that was moved into) type.
     * @param collider
     *            The AbstractGameObject that causes the collision by occupying a square with
     *            another AbstractGameObject already on it.
     * @param collidee
     *            The AbstractGameObject that is already on the square that is being invaded.
     */
    <C1 extends AbstractGameObject, C2 extends AbstractGameObject> void collide(
            Game game,
            C1 collider,
            C2 collidee);

}
