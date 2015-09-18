package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Game;
import nl.tudelft.model.AbstractGameObject;

/**
 * Interface that defines a collision handler.
 */
public interface CollisionHandler<O1 extends AbstractGameObject, O2 extends AbstractGameObject> {

    void onCollision(Game game, O1 objA, O2 objB);

}
