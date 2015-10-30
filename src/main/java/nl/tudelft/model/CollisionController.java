package nl.tudelft.model;

import nl.tudelft.controller.collision.CollisionHelper;
import nl.tudelft.controller.collision.CollisionMap;
import nl.tudelft.controller.collision.DefaultPlayerInteractionMap;
import nl.tudelft.controller.util.QuadTree;

import org.newdawn.slick.geom.Rectangle;

/**
 * This class handles updating all collisions within the game.
 */
public class CollisionController {

    private final AbstractGame game;
    private final CollisionMap collisionHandler;

    /**
     * Creates a new {@link CollisionController} for the specified game.
     * 
     * @param game
     *            {@link AbstractGame} - The game that this controller will handle collisions for.
     */
    public CollisionController(AbstractGame game) {
        this.game = game;
        collisionHandler = getNewCollisionMap();
    }

    /**
     * Updates and checks collisions.
     */
    public void updateCollisions() {
        final QuadTree quad =
                new QuadTree(0, new Rectangle(0, 0, game.getContainerWidth(),
                        game.getContainerHeight()));
        // collision: QuadTree
        quadFill(quad);

        // collision : CollisionMap
        bubbleCollision(quad);
        pickupCollision(quad);
        wallCollision(quad);
    }

    /**
     * Adds collidable objects to the quad tree.
     */
    private void quadFill(QuadTree quad) {
        for (GameObject obj : game.getPlayers()) {
            quad.insert(obj);
        }
        for (GameObject obj : game.getCurLevel().getProjectiles()) {
            quad.insert(obj);
        }
        for (GameObject obj : game.getCurLevel().getWalls()) {
            quad.insert(obj);
        }
    }

    /**
     * Checks for every bubble if it collides with anything a bubble can collide with.
     */
    private void bubbleCollision(QuadTree quad) {
        for (GameObject collidesWithA : game.getCurLevel().getBubbles()) {

            // Remove a bubble when it goes out of bounds
            boolean outOfBounds =
                    collidesWithA.getLocX() < 0
                            || collidesWithA.getLocX() + collidesWithA.getWidth() > game
                                    .getContainerWidth()
                            || collidesWithA.getLocY() < 0
                            || collidesWithA.getLocY() + collidesWithA.getHeight() > game
                                    .getContainerHeight();

            if (outOfBounds) {
                game.getCurLevel().toRemove(collidesWithA);
                continue;
            }

            // bubbles check against walls, players and projectiles
            for (GameObject collidesWithB : CollisionHelper.getCollisionsFor(collidesWithA,
                    quad)) {
                collisionHandler.collide(game, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * Checks for every pickup if it collides with anything a pickup can collide with.
     */
    private void pickupCollision(QuadTree quad) {
        for (GameObject collidesWithA : game.getCurLevel().getPickups()) {
            // collision with walls and players
            for (GameObject collidesWithB : CollisionHelper.getCollisionsFor(collidesWithA,
                    quad)) {
                collisionHandler.collide(game, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * Checks for every wall if it collides with anything a wall can collide with.
     */
    private void wallCollision(QuadTree quad) {
        for (GameObject collidesWithA : game.getCurLevel().getWalls()) {
            for (GameObject collidesWithB : CollisionHelper.getCollisionsFor(collidesWithA,
                    quad)) {
                collisionHandler.collide(game, collidesWithA, collidesWithB);
            }
        }
    }

    /**
     * The controller will use the CollisionMap returned in this method.
     * 
     * @return {@link CollisionMap} - The CollisionHandler that will be used.
     */
    protected final CollisionMap getNewCollisionMap() {
        return new DefaultPlayerInteractionMap();
    }
}
