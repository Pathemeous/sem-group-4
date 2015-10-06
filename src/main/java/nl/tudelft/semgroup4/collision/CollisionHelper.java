package nl.tudelft.semgroup4.collision;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.semgroup4.util.QuadTree;

/**
 * Helper class for anything Collision-related that does not deserve his own class Created by
 * justin on 06/09/15.
 */
public class CollisionHelper {

    /**
     * Returns a map of colliding objects.
     *
     * @param objectA
     *            GameObject - the colliding object.
     * @param quad
     *            Quadtree - the quadtree used to find collisions.
     * @return a Pair of which the Key is the 'objectA' colliding object and the value 'right'
     */
    public static List<AbstractGameObject> getCollisionsFor(AbstractGameObject objectA,
            QuadTree quad) {
        List<AbstractGameObject> out = new ArrayList<>();

        if (quad == null) {
            throw new IllegalArgumentException();
        } else {
            List<AbstractGameObject> returnObjects = new ArrayList<>();
            quad.retrieve(returnObjects, objectA.getBounds());

            for (AbstractGameObject objectB : returnObjects) {

                // do not collide with self
                if (objectA == objectB) {
                    continue;
                }

                if (objectA.getBounds().intersects(objectB.getBounds())) {
                    out.add(objectB);
                }
            }
        }

        return out;
    }

}
