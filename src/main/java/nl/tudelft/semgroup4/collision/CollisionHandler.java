package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.Game;

/**
 * Interface that defines a collision handler.
 */
public interface CollisionHandler<A extends AbstractGameObject, B extends AbstractGameObject> {

    void onCollision(Game game, A objA, B objB);

}
