package nl.tudelft.semgroup4.util;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.model.AbstractGameObject;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class QuadTree {

    private static final int MAX_OBJECTS = 10;
    private static final int MAX_LEVELS = 3;

    private final int depthLevel;
    private final List<AbstractGameObject> objects;
    private final Rectangle bounds;
    private QuadTree[] nodes;

    /**
     * generates a quadtree to search for collisions.
     * 
     * @param depthLevel
     *            int - the current QuadTree level
     * @param bounds
     *            Rectangle - the container to split in 4.
     */
    public QuadTree(int depthLevel, Rectangle bounds) {
        this.depthLevel = depthLevel;
        this.objects = new ArrayList<>();
        this.bounds = bounds;
        this.nodes = new QuadTree[4];
    }

    /**
     * Accesses the depthLevel field.
     * 
     * <p>
     * This field is protected and should only be used for testing purposes.
     * </p>
     * 
     * @return int - The depthLevel of this QuadTree.
     */
    protected final int getDepthLevel() {
        return depthLevel;
    }

    /**
     * Accesses the bounds field.
     * 
     * <p>
     * This field is protected and should only be used for testing purposes.
     * </p>
     * 
     * @return {@link Rectangle} - The boundaries of this QuadTree
     */
    protected final Rectangle getBounds() {
        return bounds;
    }
    
    protected final List<AbstractGameObject> getObjects() {
        return objects;
    }

    /**
     * Clears the QuadTree.
     */
    public void clear() {
        objects.clear();

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

    /**
     * Splits the node into 4 sub-nodes.
     */
    private void split() {
        int subWidth = (int) (bounds.getWidth() / 2);
        int subHeight = (int) (bounds.getHeight() / 2);
        int locX = (int) bounds.getX();
        int locY = (int) bounds.getY();

        nodes[0] =
                new QuadTree(depthLevel + 1, new Rectangle(locX + subWidth, locY, subWidth,
                        subHeight));
        nodes[1] =
                new QuadTree(depthLevel + 1, new Rectangle(locX, locY, subWidth, subHeight));
        nodes[2] =
                new QuadTree(depthLevel + 1, new Rectangle(locX, locY + subHeight, subWidth,
                        subHeight));
        nodes[3] =
                new QuadTree(depthLevel + 1, new Rectangle(locX + subWidth, locY + subHeight,
                        subWidth, subHeight));
    }

    /**
     * Determine which node the object belongs to. -1 means object cannot completely fit within a
     * child node and is part of the parent node
     * 
     * @param rect
     *            {@link Shape} - The bounding box of interest.
     * @return int - the node in which the object belongs.
     */
    private int getIndex(Shape rect) {
        int index = -1;
        double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
        double horizontalMid = bounds.getY() + (bounds.getHeight() / 2);

        // Object can completely fit within the top quadrants
        boolean topQuadrant =
                (rect.getY() < horizontalMid && rect.getY() + rect.getHeight() < horizontalMid);
        // Object can completely fit within the bottom quadrants
        boolean bottomQuadrant = (rect.getY() > horizontalMid);

        // Object can completely fit within the left quadrants
        if (rect.getX() < verticalMidpoint && rect.getX() + rect.getWidth() < verticalMidpoint) {
            if (topQuadrant) {
                index = 1;
            } else if (bottomQuadrant) {
                index = 2;
            }
        } else if (rect.getX() > verticalMidpoint) {
            // Object can completely fit within the right quadrants
            if (topQuadrant) {
                index = 0;
            } else if (bottomQuadrant) {
                index = 3;
            }
        }

        return index;
    }

    /**
     * Insert the object into the quadtree. If the node exceeds the capacity, it will split and add
     * all objects to their corresponding nodes.
     * 
     * @param rect
     *            GameObject - the object to insert.
     */
    public void insert(AbstractGameObject rect) {
        if (nodes[0] != null) {
            int index = getIndex(rect.getBounds());

            if (index != -1) {
                nodes[index].insert(rect);

                return;
            }
        }

        objects.add(rect);

        if (objects.size() > MAX_OBJECTS && depthLevel < MAX_LEVELS) {
            if (nodes[0] == null) {
                split();
            }

            int count = 0;
            while (count < objects.size()) {
                int index = getIndex(objects.get(count).getBounds());
                if (index != -1) {
                    nodes[index].insert(objects.remove(count));
                } else {
                    count++;
                }
            }
        }
    }

    /**
     * Return all objects that could collide with the given object.
     * 
     * @param returnObjects
     *            List - a list of GameObjects.
     * @param rect
     *            Shape - the given shape.
     * @return List - the list of collidable objects.
     */
    public List<AbstractGameObject>
            retrieve(List<AbstractGameObject> returnObjects, Shape rect) {
        int index = getIndex(rect);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(returnObjects, rect);
        }

        returnObjects.addAll(objects);

        return returnObjects;
    }
}
