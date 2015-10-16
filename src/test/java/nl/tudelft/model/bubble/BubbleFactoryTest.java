package nl.tudelft.model.bubble;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;

import nl.tudelft.semgroup4.resources.ResourcesWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BubbleFactoryTest {

    private final BubbleFactory factory;
    private final Class<? extends AbstractBubble> expectedClass;
    private final float locX;
    private final float locY;

    /**
     * Constructor of parameterized test.
     *
     * @param factory the factory to test
     * @param expectedClass the expected class
     * @param locX location to test (x)
     * @param locY location to test (y)
     */
    public BubbleFactoryTest(BubbleFactory factory,
                             Class<? extends AbstractBubble> expectedClass,
                             float locX, float locY) {
        this.factory = factory;
        this.expectedClass = expectedClass;
        this.locX = locX;
        this.locY = locY;
    }

    @Test
    public void testType() {
        AbstractBubble bubble = factory.createBubble(locX, locY);
        assertEquals(expectedClass, bubble.getClass());
        assertEquals(locX, bubble.getLocX(), 0.0f);
        assertEquals(locY, bubble.getLocY(), 0.0f);
    }

    /**
     * This is the data for the parameterized test.
     *
     * @return collection of object arrays that represent the parameters
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        final ResourcesWrapper resourcesWrapper = mock(ResourcesWrapper.class);

        return Arrays.asList(new Object[][]{
            {
                new Bubble1Factory(resourcesWrapper),
                Bubble1.class,
                50f, 150.0f,
            },
            {
                new Bubble2Factory(resourcesWrapper),
                Bubble2.class,
                50f, 0.0f,
            },
            {
                new Bubble3Factory(resourcesWrapper),
                Bubble3.class,
                0.0f, 100.0f,
            },
            {
                new Bubble4Factory(resourcesWrapper),
                Bubble4.class,
                0.0f, 150.0f,
            },
            {
                new Bubble5Factory(resourcesWrapper),
                Bubble5.class,
                -1.0f, 0.0f,
            },
            {
                new Bubble6Factory(resourcesWrapper),
                Bubble6.class,
                0.0f, -100.0f,
            },
        });
    }
}
