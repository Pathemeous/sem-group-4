package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.model.bubble.Bubble1Factory;
import nl.tudelft.model.bubble.Bubble2Factory;
import nl.tudelft.model.bubble.Bubble3Factory;
import nl.tudelft.model.bubble.Bubble4Factory;
import nl.tudelft.model.bubble.Bubble5Factory;
import nl.tudelft.model.bubble.Bubble6Factory;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.semgroup4.resources.ResourcesWrapper;

/**
 * A factory that can return full-fledged {@link Level}s on demand.
 */
public class LevelFactory {

    private final Game game;
    private final ResourcesWrapper resources;
    private static final int LEVEL_COUNT = 4;

    /**
     * Constructs a new {@link LevelFactory} for a certain {@link Game}, using the specified
     * {@link ResourcesWrapper}.
     * 
     * @param game
     *            {@link Game} - the game that this factory belongs to.
     * @param wrapper
     *            {@link ResourcesWrapper} - the resources that this factory will use.
     */
    public LevelFactory(Game game, ResourcesWrapper wrapper) {
        this.game = game;
        this.resources = wrapper;
    }

    /**
     * Gets the {@link Game} instance that this factory returns to.
     * 
     * @return {@link Game} - the Game instance that this factory belongs to.
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Returns a list containing all levels that the factory can make.
     * 
     * @return LinkedList - All levels ordered from low to high id.
     */
    public LinkedList<Level> getAllLevels() {
        LinkedList<Level> result = new LinkedList<>();

        for (int i = 1; i <= LEVEL_COUNT; i++) {
            result.add(getLevel(i));
        }
        return result;
    }

    /**
     * Creates a level based on its ID.
     * <p>
     * The ID represents the numbering of the levels. The first level starts with 1.
     * </p>
     * 
     * <p>
     * If the ID provided is not a level, an {@link IllegalArgumentException} is thrown.
     * </p>
     * 
     * @param id
     *            int - the ID for the level.
     * @return Level - the Level object ready for use.
     * @throws IllegalArgumentException
     *             - If the ID is out of range.
     */
    public Level getLevel(int id) throws IllegalArgumentException {
        switch (id) {
            case 1:
                return getLevel1();
            case 2:
                return getLevel2();
            case 3:
                return getLevel3();
            case 4:
                return getLevel4();
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Creates the standard outer walls for a level.
     * 
     * @return {@link LinkedList} of {@link Wall}s - a list containing the 4 default {@link Wall}s.
     */
    private LinkedList<AbstractWall> wallInit() {
        LinkedList<AbstractWall> walls = new LinkedList<>();

        walls.add(new RegularWall(resources.getVwallImage(), 0, 0));
        walls.add(new RegularWall(resources.getVwallImage(), game.getContainerWidth()
                - resources.getVwallImage().getWidth(), 0));

        walls.add(new RegularWall(resources.getWallImage(), 0, 0));
        for (int i = 1; i <= 5; i++) {
            // five walls of space for the UI
            walls.add(new RegularWall(resources.getWallImage(), 0, game.getContainerHeight()
                    - resources.getWallImage().getHeight() * i));
        }
        return walls;
    }

    /**
     * Builds the first level.
     * 
     * @return {@link Level} - level 1.
     */
    private Level getLevel1() {
        final int id = 1;

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();
        
        Bubble bubble = new Bubble6Factory(resources).createBubble();
        bubble.setLocX(resources.getVwallImage().getWidth() + 100);
        bubble.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble.getWidth() - 400);
        bubbles.add(bubble);

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;
        
        LinkedList<AbstractWall> walls = wallInit();
        
        MovingWall movingWall = new MovingWall(resources.getSmallHWallImage(), 300, 200, 2);
        MovingWall movingWall2 = new MovingWall(resources.getSmallHWallImage(), 800, 200, -2);
        walls.add(movingWall);
        walls.add(movingWall2);

        return new Level(walls, projectiles, pickups, bubbles, time, id);
    }

    /**
     * Builds the second level.
     * 
     * @return {@link Level} - level 2.
     */
    private Level getLevel2() {
        final int id = 2;

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();
        
        Bubble bubble1 = new Bubble6Factory(resources).createBubble();
        bubble1.setLocX(resources.getVwallImage().getWidth() + 100);
        bubble1.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble1.getHeight() - 400);
        bubbles.add(bubble1);
        
        Bubble bubble2 = new Bubble5Factory(resources).createBubble();
        bubble2.setLocX(resources.getVwallImage().getWidth() + 300);
        bubble2.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble2.getHeight() - 400);
        bubbles.add(bubble2);

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;
        
        LinkedList<AbstractWall> walls = wallInit();

        return new Level(walls, projectiles, pickups, bubbles, time, id);

    }

    /**
     * Builds the third level.
     * 
     * @return {@link Level} - level 3.
     */
    private Level getLevel3() {
        final int id = 3;

        final LinkedList<AbstractWall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();
        
        Bubble bubble1 = new Bubble3Factory(resources).createBubble();
        bubble1.setLocX(resources.getVwallImage().getWidth() + 100);
        bubble1.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble1.getHeight() - 400);
        bubbles.add(bubble1);
        
        Bubble bubble2 = new Bubble3Factory(resources).createBubble();
        bubble2.setLocX(resources.getVwallImage().getWidth() + 200);
        bubble2.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble2.getHeight() - 400);
        bubbles.add(bubble2);
        
        Bubble bubble3 = new Bubble3Factory(resources).createBubble();
        bubble3.setLocX(resources.getVwallImage().getWidth() + 300);
        bubble3.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble3.getHeight() - 400);
        bubbles.add(bubble3);

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);

    }

    /**
     * Builds the fourth level.
     * 
     * @return {@link Level} - level 4.
     */
    private Level getLevel4() {
        final int id = 4;

        final LinkedList<AbstractWall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();
        
        Bubble bubble1 = new Bubble6Factory(resources).createBubble();
        bubble1.setLocX(resources.getVwallImage().getWidth() + 100);
        bubble1.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble1.getHeight() - 400);
        bubbles.add(bubble1);
        
        Bubble bubble2 = new Bubble5Factory(resources).createBubble();
        bubble2.setLocX(resources.getVwallImage().getWidth() + 200);
        bubble2.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble2.getHeight() - 400);
        bubbles.add(bubble2);
        
        Bubble bubble3 = new Bubble4Factory(resources).createBubble();
        bubble3.setLocX(resources.getVwallImage().getWidth() + 300);
        bubble3.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble3.getHeight() - 400);
        bubbles.add(bubble3);
        
        Bubble bubble4 = new Bubble3Factory(resources).createBubble();
        bubble4.setLocX(resources.getVwallImage().getWidth() + 400);
        bubble4.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble4.getHeight() - 400);
        bubbles.add(bubble4);
        
        Bubble bubble5 = new Bubble2Factory(resources).createBubble();
        bubble5.setLocX(resources.getVwallImage().getWidth() + 500);
        bubble5.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble5.getHeight() - 400);
        bubbles.add(bubble5);
        
        Bubble bubble6 = new Bubble1Factory(resources).createBubble();
        bubble6.setLocX(resources.getVwallImage().getWidth() + 600);
        bubble6.setLocY(game.getContainerHeight() - resources.getWallImage().getHeight() 
                - bubble6.getHeight() - 400);
        bubbles.add(bubble6);

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 240000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);
    }
}
