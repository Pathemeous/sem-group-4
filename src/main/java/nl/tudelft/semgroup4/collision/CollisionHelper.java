package nl.tudelft.semgroup4.collision;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.GameObject;

/**
 * Helper class for anything Collision-related that does not deserve his own class
 * Created by justin on 06/09/15.
 */
public class CollisionHelper {

    /**
     * Returns a map of colliding objects.
     *
     * @param objects the objects to check
     * @return a Pair of which the Key is the 'objectA' colliding object and the value 'right'
     */
    public static List<GameObject> collideObjectWithList(
            GameObject objectA,
            List<GameObject> objects) {

        List<GameObject> out = new ArrayList<>();

        for (GameObject objectB : objects) {
            // do not collide with self
            if (objectA == objectB) {
                continue;
            }

            if (objectA.getBounds().intersects(objectB.getBounds())) {
                out.add(objectB);
            }
        }
        return out;
    }

}
