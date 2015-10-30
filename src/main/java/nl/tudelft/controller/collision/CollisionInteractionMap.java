package nl.tudelft.controller.collision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.tudelft.model.AbstractGame;
import nl.tudelft.model.GameObject;

/**
 * This class was inspired by CollisionInteractionMap of jpacman.
 *
 * <p>
 * The different lies in the way handlers are matched to two classes. Our version doesn't stop at
 * the first match, it recursively walks through the hierarchy and calls every handler that is
 * matched.
 * </p>
 *
 * <p>
 * original comment:
 * </p>
 * <p>
 * A map of possible collisions and their handlers.
 * </p>
 *
 * @author Michael de Jong
 * @author Jeroen Roosen
 *
 * @see <a href="https://github.com/SERG-Delft/jpacman-framework">jpacman github</a>
 */
public class CollisionInteractionMap implements CollisionMap {

    /**
     * The collection of collision handlers.
     */
    private final Map<Class<? extends GameObject>,
        Map<Class<? extends GameObject>, CollisionHandler<?, ?>>> handlers;

    /**
     * Creates a new, empty collision map.
     */
    public CollisionInteractionMap() {
        this.handlers = new HashMap<>();
    }

    /**
     * Adds a two-way collision interaction to this collection, i.e. the collision handler will be
     * used for both C1 versus C2 and C2 versus C1.
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
    public <C1 extends GameObject, C2 extends GameObject> void onCollision(Class<C1> collider,
            Class<C2> collidee, CollisionHandler<C1, C2> handler) {
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
     *            <code>true</code> if this collision is used for both C1 against C2 and vice
     *            versa; <code>false</code> if only for C1 against C2.
     * @param handler
     *            The handler that handles the collision.
     */
    public <C1 extends GameObject, C2 extends GameObject> void onCollision(Class<C1> collider,
            Class<C2> collidee, boolean symetric, CollisionHandler<C1, C2> handler) {
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
    private void addHandler(Class<? extends GameObject> collider,
            Class<? extends GameObject> collidee, CollisionHandler<?, ?> handler) {
        if (!handlers.containsKey(collider)) {
            handlers.put(collider, new HashMap<>());
        }

        Map<Class<? extends GameObject>, CollisionHandler<?, ?>> map = handlers.get(collider);
        map.put(collidee, handler);
    }

    /**
     * Handles the collision between two colliding parties, all suitable collision handlers are
     * used.
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
    public <C1 extends GameObject, C2 extends GameObject> void collide(AbstractGame game,
            C1 collider, C2 collidee) {
        List<Class<? extends GameObject>> colliderInheritance =
                getInheritance(collider.getClass());
        List<Class<? extends GameObject>> collideeInheritance =
                getInheritance(collidee.getClass());

        for (Class<? extends GameObject> colliderKey : colliderInheritance) {
            if (handlers.get(colliderKey) != null) {
                Map<Class<? extends GameObject>, CollisionHandler<?, ?>> colliderMap =
                        handlers.get(colliderKey);
                for (Class<? extends GameObject> collideeKey : collideeInheritance) {
                    if (colliderMap.get(collideeKey) != null) {
                        ((CollisionHandler<C1, C2>) colliderMap.get(collideeKey))
                                .handleCollision(game, collider, collidee);
                    }
                }
            }
        }
    }

    /**
     * Returns a list of all classes and interfaces the class inherits.
     *
     * @param clazz
     *            The class to create a list of super classes and interfaces for.
     * @return A list of all classes and interfaces the class inherits.
     */
    @SuppressWarnings("unchecked")
    private List<Class<? extends GameObject>>
            getInheritance(Class<? extends GameObject> clazz) {
        List<Class<? extends GameObject>> found = new ArrayList<>();
        found.add(clazz);

        int index = 0;
        while (found.size() > index) {
            Class<?> current = found.get(index);
            Class<?> superClass = current.getSuperclass();
            if (superClass != null && GameObject.class.isAssignableFrom(superClass)) {
                found.add((Class<? extends GameObject>) superClass);
            }
            for (Class<?> classInterface : current.getInterfaces()) {
                if (GameObject.class.isAssignableFrom(classInterface)) {
                    found.add((Class<? extends GameObject>) classInterface);
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
    private static class InverseCollisionHandler<C1 extends GameObject, C2 extends GameObject>
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
         * Handles this collision by flipping the collider and collidee, making it compatible with
         * the initial collision.
         */
        @Override
        public void handleCollision(AbstractGame game, C1 collider, C2 collidee) {
            handler.handleCollision(game, collidee, collider);
        }
    }

}
