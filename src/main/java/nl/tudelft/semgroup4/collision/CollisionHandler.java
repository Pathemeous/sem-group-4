package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Game;
import nl.tudelft.model.GameObject;

/**
 * Interface that defines a collision handler.
 */
public interface CollisionHandler<O1 extends GameObject, O2 extends GameObject> {

    void onCollision(Game game, O1 objA, O2 objB);

}
