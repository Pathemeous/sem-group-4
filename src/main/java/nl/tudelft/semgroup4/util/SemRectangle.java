package nl.tudelft.semgroup4.util;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * This class overrides the intersects(Shape) method. The original implementation also returns true
 * when two rectangles touch. We don't want this behavior. Created by justin on 08/09/15.
 */
public class SemRectangle extends Rectangle {

    public SemRectangle(float locX, float locY, float width, float height) {
        super(locX, locY, width, height);
    }

    /**
     * @param shape
     *            Shape - the shape to intersect with.
     * @return same as super, except when two rectangles are touching. When two rectangles touch
     *         returns false.
     */
    public boolean intersects(Shape shape) {
        if (shape instanceof Rectangle) {
            Rectangle other = (Rectangle) shape;
            return this.getX() < other.getX() + other.getWidth()
                    && this.getX() + this.getWidth() > other.getX()
                    && this.getY() < other.getY() + other.getHeight()
                    && this.getY() + this.getHeight() > other.getY();
        } else {
            return super.intersects(shape);
        }
    }

}
