package nl.tudelft.semgroup4;

import nl.tudelft.model.GameObject;

/**
 * The Modifiable interface should be implemented by the Level object, allowing other objects to
 * request modifications to the level.
 * 
 * @author Pathemeous
 *
 */
public interface Modifiable {
    
    /**
     * Adds an object to the class, provided that it fits in there.
     * 
     * @param obj GameObject - The object that the caller wants added.
     * @return boolean - true if the addition was successful, false if it was not.
     */
    public void toAdd(GameObject obj);
    
    /**
     * Removes an object from the class, provided that it is in there.
     *  
     * @param obj GameObject - The object that the caller wants removed
     * @return boolean - true if the object is no longer in the class, whether it was removed by this call or already absent.
     */
    public void toRemove(GameObject obj);

}
