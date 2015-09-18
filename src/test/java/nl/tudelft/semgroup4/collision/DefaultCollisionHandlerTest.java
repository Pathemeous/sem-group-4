package nl.tudelft.semgroup4.collision;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.tudelft.model.Bubble;
import nl.tudelft.model.Game;

import nl.tudelft.model.Wall;
import org.junit.Test;

public class DefaultCollisionHandlerTest {

    @Test
    public void testOnCollision1() {
        Game game = mock(Game.class);
        Bubble bubble = mock(Bubble.class);
        Wall wall = mock(Wall.class);
        DefaultCollisionHandler collisionHandler = new DefaultCollisionHandler();
        collisionHandler.onCollision(game, bubble, wall);
        verify(wall, times(1)).getLocX();
    }


}
