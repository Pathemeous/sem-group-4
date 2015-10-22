package nl.tudelft.controller.collision;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.controller.util.QuadTree;
import nl.tudelft.model.GameObject;

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
    public static List<GameObject> getCollisionsFor(GameObject objectA, QuadTree quad) {
        List<GameObject> out = new ArrayList<>();

        if (quad == null) {
            throw new IllegalArgumentException();
        } else {
            List<GameObject> returnObjects = new ArrayList<>();

            returnObjects = quad.retrieve(returnObjects, objectA.getBounds());

            for (GameObject objectB : returnObjects) {

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
