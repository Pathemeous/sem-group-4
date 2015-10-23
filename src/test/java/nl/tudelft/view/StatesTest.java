package nl.tudelft.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class StatesTest {

    @Test
    public void testStates() {
        States states = new States();
        assertEquals(States.class, states.getClass());
    }
}
