package nl.tudelft.semgroup4.util;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Created by justin on 08/09/15.
 */
public class SEMRectangle extends Rectangle {

    public SEMRectangle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public boolean intersects(Shape shape) {
        if(shape instanceof Rectangle) {
            Rectangle other = (Rectangle)shape;
            return     this.getX() < other.getX() + other.getWidth()
                    && this.getX() + this.getWidth()  > other.getX() && this.getY() < other.getY() + other.getHeight()
                    && this.getY() + this.getHeight() > other.getY();
        } else {
            return super.intersects(shape);
        }
    }

}
