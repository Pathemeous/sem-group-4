package nl.tudelft.model;

import nl.tudelft.semgroup4.collision.CollisionHelper;
import nl.tudelft.semgroup4.collision.CollisionMap;
import nl.tudelft.semgroup4.collision.DefaultPlayerInteractionMap;
import nl.tudelft.semgroup4.util.QuadTree;

import org.newdawn.slick.geom.Rectangle;

public class CollisionController {
    
    private final Game game;
    private final CollisionMap collisionHandler;
    
    public CollisionController(Game game) {
        this.game = game;
        collisionHandler = getNewCollisionMap();
    }

    /**
     * Updates collisions.
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
        for (AbstractGameObject obj : game.getPlayers()) {
            quad.insert(obj);
        }
        for (AbstractGameObject obj : game.getCurLevel().getProjectiles()) {
            quad.insert(obj);
        }
        for (AbstractGameObject obj : game.getCurLevel().getWalls()) {
            quad.insert(obj);
        }
    }
    
    /**
     * Checks for every player if it collides with anything a bubble can collide with.
     */
    private void bubbleCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : game.getCurLevel().getBubbles()) {
            
            // Remove a bubble when it goes out of bounds
            boolean outOfBounds = collidesWithA.getLocX() < 0 || collidesWithA.getLocX() 
                    + collidesWithA.getWidth() > game.getContainerWidth() 
                    || collidesWithA.getLocY() < 0 || collidesWithA.getLocY() 
                    + collidesWithA.getHeight() > game.getContainerHeight();
            
            if (outOfBounds) {
                game.getCurLevel().toRemove(collidesWithA);
                continue;
            }
            
            // bubbles check against walls, players and projectiles
            for (AbstractGameObject collidesWithB : CollisionHelper.getCollisionsFor(
                    collidesWithA, quad)) {
                collisionHandler.collide(game, collidesWithA, collidesWithB);
            }
        }
    }
    
    /**
     * Checks for every pickup if it collides with anything a pickup can collide with.
     */
    private void pickupCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : game.getCurLevel().getPickups()) {
            // collision with walls and players
            for (AbstractGameObject collidesWithB : CollisionHelper.getCollisionsFor(
                    collidesWithA, quad)) {
                collisionHandler.collide(game, collidesWithA, collidesWithB);
            }
        }
    }
    
    /**
     * Checks for every wall if it collides with anything a wall can collide with.
     */
    private void wallCollision(QuadTree quad) {
        for (AbstractGameObject collidesWithA : game.getCurLevel().getWalls()) {
            for (AbstractGameObject collidesWithB : CollisionHelper.getCollisionsFor(
                    collidesWithA, quad)) {
                collisionHandler.collide(game, collidesWithA, collidesWithB);
            }
        }
    }
    
    /**
     * game will use CollisionHandler returned in this method.
     * 
     * @return the CollisionHandler that will be used.
     */
    protected final CollisionMap getNewCollisionMap() {
        return new DefaultPlayerInteractionMap();
    }
}
