package nl.tudelft.semgroup4.collision;

import nl.tudelft.model.Game;
import nl.tudelft.model.GameObject;

/**
 * Interface that defines a collision handler
 *
 * Created by justin on 06/09/15.
 */
public interface CollisionHandler<O1 extends GameObject, O2 extends GameObject> {

    void onCollision(Game game, O1 objA, O2 objB);

}
