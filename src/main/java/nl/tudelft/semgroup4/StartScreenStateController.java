package nl.tudelft.semgroup4;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Controller that performs logic for the StartScreenState
 * 
 * @author Pathemeous
 *
 */
public class StartScreenStateController {

    private StartScreenState state;
    private ResourcesWrapper resources;

    /**
     * Creates a new controler for a specific {@link StartScreenState}.
     * 
     * @param state
     *            {@link StartScreenState} - The state that this controller is assigned to.
     * @param resources
     *            {@link ResourcesWrapper} - The resources that this controller may use.
     */
    public StartScreenStateController(StartScreenState state, ResourcesWrapper resources) {
        this.state = state;
        this.resources = resources;
    }

}
