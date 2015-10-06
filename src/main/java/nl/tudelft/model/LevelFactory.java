package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.model.bubble.Bubble;
import nl.tudelft.model.bubble.Bubble1;
import nl.tudelft.model.bubble.Bubble2;
import nl.tudelft.model.bubble.Bubble3;
import nl.tudelft.model.bubble.Bubble4;
import nl.tudelft.model.bubble.Bubble5;
import nl.tudelft.model.bubble.Bubble6;
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
    private LinkedList<Wall> wallInit() {
        LinkedList<Wall> walls = new LinkedList<>();

        walls.add(new Wall(resources.getVwallImage(), 0, 0));
        walls.add(new Wall(resources.getVwallImage(), game.getContainerWidth()
                - resources.getVwallImage().getWidth(), 0));

        walls.add(new Wall(resources.getWallImage(), 0, 0));
        for (int i = 1; i <= 5; i++) {
            // five walls of space for the UI
            walls.add(new Wall(resources.getWallImage(), 0, game.getContainerHeight()
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

        LinkedList<Wall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();

        bubbles.add(new Bubble1(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 100, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);
    }

    /**
     * Builds the second level.
     * 
     * @return {@link Level} - level 2.
     */
    private Level getLevel2() {
        final int id = 2;

        LinkedList<Wall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();

        bubbles.add(new Bubble6(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 100, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));
        bubbles.add(new Bubble5(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 300, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);

    }

    /**
     * Builds the third level.
     * 
     * @return {@link Level} - level 3.
     */
    private Level getLevel3() {
        final int id = 3;

        final LinkedList<Wall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();

        bubbles.add(new Bubble3(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 100, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));
        bubbles.add(new Bubble3(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 200, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));
        bubbles.add(new Bubble3(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 300, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

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

        final LinkedList<Wall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();

        bubbles.add(new Bubble6(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 100, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

        bubbles.add(new Bubble5(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 200, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

        bubbles.add(new Bubble4(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 300, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

        bubbles.add(new Bubble3(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 400, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

        bubbles.add(new Bubble2(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 500, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

        bubbles.add(new Bubble1(new ResourcesWrapper(),
                resources.getVwallImage().getWidth() + 600, game.getContainerHeight()
                        - resources.getWallImage().getHeight()
                        - resources.getBubbleImage6().getWidth() - 400));

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 240000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);
    }
}
