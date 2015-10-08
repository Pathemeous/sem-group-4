package nl.tudelft.model.bubble;

import nl.tudelft.semgroup4.resources.Resources;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * Created by justin on 08/10/15.
 */
public class DefaultBubbleFactoryFactory implements BubbleFactoryFactory {

    private final BubbleFactory bubbleFactorySize1;
    private final BubbleFactory bubbleFactorySize2;
    private final BubbleFactory bubbleFactorySize3;
    private final BubbleFactory bubbleFactorySize4;
    private final BubbleFactory bubbleFactorySize5;
    private final BubbleFactory bubbleFactorySize6;

    /**
     * This factory creates {@link BubbleFactory}s.
     *
     * <p>This can be used in LevelFactory and for splitting bubbles.</p>
     *
     * @param resources The {@link ResourcesWrapper} that this class can use.
     */
    public DefaultBubbleFactoryFactory(ResourcesWrapper resources) {
        bubbleFactorySize1 = new Bubble1Factory(resources, this);
        bubbleFactorySize2 = new Bubble2Factory(resources, this);
        bubbleFactorySize3 = new Bubble3Factory(resources, this);
        bubbleFactorySize4 = new Bubble4Factory(resources, this);
        bubbleFactorySize5 = new Bubble5Factory(resources, this);
        bubbleFactorySize6 = new Bubble6Factory(resources, this);
    }

    @Override
    public BubbleFactory getSize1() {
        return bubbleFactorySize1;
    }

    @Override
    public BubbleFactory getSize2() {
        return bubbleFactorySize2;
    }

    @Override
    public BubbleFactory getSize3() {
        return bubbleFactorySize3;
    }

    @Override
    public BubbleFactory getSize4() {
        return bubbleFactorySize4;
    }

    @Override
    public BubbleFactory getSize5() {
        return bubbleFactorySize5;
    }

    @Override
    public BubbleFactory getSize6() {
        return bubbleFactorySize6;
    }

}
