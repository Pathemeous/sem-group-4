package nl.tudelft.model;

import org.newdawn.slick.Image;

public abstract class AbstractEnvironmentObject extends AbstractGameObject {

    /**
     * Instantiates the attributes of an AbstractEnvironmentObject.
     * 
     * @param image
     *            {@link Image} - An image from the Bubble Trouble resources that this object
     *            should render.
     * @param locX
     *            float - The top left x-coordinate of the location of this object.
     * @param locY
     *            float - The top left x-coordinate of the location of this object.
     */
    public AbstractEnvironmentObject(Image image, float locX, float locY) {
        super(image, locX, locY);
    }

}
