package nl.tudelft.semgroup4.collision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.tudelft.model.AbstractGameObject;
import nl.tudelft.model.Game;

/**
 * A map of possible collisions and their handlers.
 * 
 * @author Michael de Jong
 * @author Jeroen Roosen 
 */
public class CollisionInteractionMap implements CollisionMap {

    /**
     * The collection of collision handlers.
     */
    private final Map<
        Class<? extends AbstractGameObject>,
        Map<Class<? extends AbstractGameObject>, CollisionHandler<?, ?>>> handlers;

    /**
     * Creates a new, empty collision map.
     */
    public CollisionInteractionMap() {
        this.handlers = new HashMap<>();
    }

    /**
     * Adds a two-way collision interaction to this collection, i.e. the
     * collision handler will be used for both C1 versus C2 and C2 versus C1.
     * 
     * @param <C1>
     *            The collider type.
     * @param <C2>
     *            The collidee (AbstractGameObject that was moved into) type.
     * @param collider
     *            The collider type.
     * @param collidee
     *            The collidee type.
     * @param handler
     *            The handler that handles the collision.
     */
    public <C1 extends AbstractGameObject, C2 extends AbstractGameObject> void onCollision(
            Class<C1> collider, Class<C2> collidee,
            CollisionHandler<C1, C2> handler) {
        onCollision(collider, collidee, true, handler);
    }

    /**
     * Adds a collision interaction to this collection.
     * 
     * @param <C1>
     *            The collider type.
     * @param <C2>
     *            The collidee (AbstractGameObject that was moved into) type.
     * @param collider
     *            The collider type.
     * @param collidee
     *            The collidee type.
     * @param symetric
     *            <code>true</code> if this collision is used for both
     *            C1 against C2 and vice versa;
     *            <code>false</code> if only for C1 against C2.
     * @param handler
     *            The handler that handles the collision.
     */
    public <C1 extends AbstractGameObject, C2 extends AbstractGameObject> void onCollision(
            Class<C1> collider, Class<C2> collidee, boolean symetric,
            CollisionHandler<C1, C2> handler) {
        addHandler(collider, collidee, handler);
        if (symetric) {
            addHandler(collidee, collider, new InverseCollisionHandler<>(handler));
        }
    }

    /**
     * Adds the collision interaction..
     * 
     * @param collider
     *            The collider type.
     * @param collidee
     *            The collidee type.
     * @param handler
     *            The handler that handles the collision.
     */
    private void addHandler(Class<? extends AbstractGameObject> collider,
            Class<? extends AbstractGameObject> collidee, CollisionHandler<?, ?> handler) {
        if (!handlers.containsKey(collider)) {
            handlers.put(collider, new HashMap<>());
        }

        Map<Class<? extends AbstractGameObject>, CollisionHandler<?, ?>> map = handlers
                .get(collider);
        map.put(collidee, handler);
    }

    /**
     * Handles the collision between two colliding parties, if a suitable
     * collision handler is listed.
     * 
     * @param <C1>
     *            The collider type.
     * @param <C2>
     *            The collidee (AbstractGameObject that was moved into) type.
     * @param collider
     *            The collider.
     * @param collidee
     *            The collidee.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <C1 extends AbstractGameObject, C2 extends AbstractGameObject> void collide(
            Game game,
            C1 collider,
            C2 collidee) {
        List<Class<? extends AbstractGameObject>> colliderInheritance =
                getInheritance(collider.getClass());
        List<Class<? extends AbstractGameObject>> collideeInheritance =
                getInheritance(collidee.getClass());

        for (Class<? extends AbstractGameObject> colliderKey : colliderInheritance) {
            if (handlers.get(colliderKey) != null) {
                Map<Class<? extends AbstractGameObject>, CollisionHandler<?, ?>> colliderMap =
                        handlers.get(colliderKey);
//                    colliderMaps.add(colliderMap);

                for (Class<? extends AbstractGameObject> collideeKey : collideeInheritance) {
                    if (colliderMap.get(collideeKey) != null) {
                        ((CollisionHandler<C1, C2>)colliderMap.get(collideeKey))
                                .handleCollision(game, collider, collidee);
                    }
                }
            }
        }
    }

    /**
     * Figures out the most specific class that is listed in the map. I.e. if A
     * extends B and B is listed while requesting A, then B will be returned.
     * 
     * @param map
     *            The map with the key collection to find a matching class in.
     * @param key
     *            The class to search the most suitable key for.
     * @return The most specific class from the key collection.
     */
    private Class<? extends AbstractGameObject> getMostSpecificClass(
            Map<Class<? extends AbstractGameObject>, ?> map,
            Class<? extends AbstractGameObject> key) {
        List<Class<? extends AbstractGameObject>> collideeInheritance = getInheritance(key);
        for (Class<? extends AbstractGameObject> pointer : collideeInheritance) {
            if (map.containsKey(pointer)) {
                return pointer;
            }
        }
        return null;
    }

    /**
     * Returns a list of all classes and interfaces the class inherits.
     * 
     * @param clazz
     *            The class to create a list of super classes and interfaces
     *            for.
     * @return A list of all classes and interfaces the class inherits.
     */
    @SuppressWarnings("unchecked")
    private List<Class<? extends AbstractGameObject>> getInheritance(
            Class<? extends AbstractGameObject> clazz) {
        List<Class<? extends AbstractGameObject>> found = new ArrayList<>();
        found.add(clazz);

        int index = 0;
        while (found.size() > index) {
            Class<?> current = found.get(index);
            Class<?> superClass = current.getSuperclass();
            if (superClass != null && AbstractGameObject.class.isAssignableFrom(superClass)) {
                found.add((Class<? extends AbstractGameObject>) superClass);
            }
            for (Class<?> classInterface : current.getInterfaces()) {
                if (AbstractGameObject.class.isAssignableFrom(classInterface)) {
                    found.add((Class<? extends AbstractGameObject>) classInterface);
                }
            }
            index++;
        }

        return found;
    }

    /**
     * An symmetrical copy of a collision hander.
     * 
     * @author Michael de Jong
     * @param <C1>
     *            The collider type.
     * @param <C2>
     *            The collidee type.
     */
    private static class InverseCollisionHandler<
            C1 extends AbstractGameObject,
            C2 extends AbstractGameObject>
            implements CollisionHandler<C1, C2> {

        /**
         * The handler of this collision.
         */
        private final CollisionHandler<C2, C1> handler;

        /**
         * Creates a new collision handler.
         * 
         * @param handler
         *            The symmetric handler for this collision.
         */
        public InverseCollisionHandler(CollisionHandler<C2, C1> handler) {
            this.handler = handler;
        }

        /**
         * Handles this collision by flipping the collider and collidee, making
         * it compatible with the initial collision.
         */
        @Override
        public void handleCollision(Game game, C1 collider, C2 collidee) {
            handler.handleCollision(game, collidee, collider);
        }
    }

}
