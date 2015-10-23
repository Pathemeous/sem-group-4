package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.controller.resources.ResourcesWrapper;
import nl.tudelft.model.bubble.AbstractBubble;
import nl.tudelft.model.bubble.BacchelliBubble;
import nl.tudelft.model.bubble.Bubble1;
import nl.tudelft.model.bubble.Bubble2;
import nl.tudelft.model.bubble.Bubble3;
import nl.tudelft.model.bubble.Bubble4;
import nl.tudelft.model.bubble.Bubble5;
import nl.tudelft.model.bubble.Bubble6;
import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.model.pickups.weapon.Projectile;
import nl.tudelft.model.wall.AbstractWall;
import nl.tudelft.model.wall.HorMovingWall;
import nl.tudelft.model.wall.RegularWall;
import nl.tudelft.model.wall.VerMovingWall;

/**
 * A factory that can return full-fledged {@link Level}s on demand.
 */
public class LevelFactory {

    private final Game game;
    private final ResourcesWrapper resources;
    private static final int LEVEL_COUNT = 5;

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
            case 5: 
                return getBossLevel();
            default:
                throw new IllegalArgumentException();
        }
    }

    private Level getBossLevel() {
        final int id = 5;
        // Create Bubbles for level
        LinkedList<AbstractBubble> bubbles = new LinkedList<>();

        AbstractBubble bubble = new BacchelliBubble(resources, 200,
                resources.getVwallImage().getWidth() );

        bubbles.add(bubble);

        LinkedList<AbstractWall> walls = wallInit();
        {
//            float gap = 40;
//            for (float x = resources.getVwallImage().getWidth();
//                 x <= game.getContainerWidth();
//                 x += resources.getSmallHWallImage().getWidth() + gap) {
//                walls.add(new RegularWall(resources.getSmallHWallImage(), x, 500));
//            }
        }

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 200000;

        return new Level(resources.getBackgroundImage(), walls, projectiles, pickups, bubbles,
                time, id);

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
        LinkedList<AbstractBubble> bubbles = new LinkedList<>();

        Bubble2 bubble1 = new Bubble2(resources, resources.getVwallImage().getWidth() + 100,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage2().getHeight() - 400);
       
        bubbles.add(bubble1);

        LinkedList<AbstractWall> walls = wallInit();

        HorMovingWall movingWall =
                new HorMovingWall(resources.getSmallHWallImage(), 300, 200, 2);
        HorMovingWall movingWall2 =
                new HorMovingWall(resources.getSmallHWallImage(), 800, 200, -2);
        walls.add(movingWall);
        walls.add(movingWall2);

        VerMovingWall movingWall3 =
                new VerMovingWall(resources.getSmallVWallImage(), 200, 200, -2);
        walls.add(movingWall3);

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(resources.getBackgroundImage(), walls, projectiles, pickups, bubbles,
                time, id);
    }

    /**
     * Builds the second level.
     * 
     * @return {@link Level} - level 2.
     */
    private Level getLevel2() {
        final int id = 2;

        // Create Bubbles for level
        LinkedList<AbstractBubble> bubbles = new LinkedList<>();

        Bubble6 bubble1 = new Bubble6(resources, resources.getVwallImage().getWidth() + 100,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage6().getHeight() - 400);

        bubbles.add(bubble1);

        LinkedList<AbstractWall> walls = wallInit();

        HorMovingWall movingWall =
                new HorMovingWall(resources.getSmallHWallImage(), 300, 400, 2);
        HorMovingWall movingWall2 =
                new HorMovingWall(resources.getSmallHWallImage(), 800, 400, -2);
        walls.add(movingWall);
        walls.add(movingWall2);

        VerMovingWall movingWall3 =
                new VerMovingWall(resources.getSmallVWallImage(), 250, 200, -2);
        walls.add(movingWall3);
        VerMovingWall movingWall4 =
                new VerMovingWall(resources.getSmallVWallImage(), 400, 200, -2);
        walls.add(movingWall4);

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(resources.getBackgroundImage(), walls, projectiles, pickups, bubbles,
                time, id);

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
        LinkedList<AbstractBubble> bubbles = new LinkedList<>();
        
        Bubble3 bubble1 = new Bubble3(resources, resources.getVwallImage().getWidth() + 100,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage3().getHeight() - 400);        
        bubbles.add(bubble1);
        
        Bubble3 bubble2 = new Bubble3(resources, resources.getVwallImage().getWidth() + 200,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage3().getHeight() - 400);       
        bubbles.add(bubble2);
        
        Bubble3 bubble3 = new Bubble3(resources, resources.getVwallImage().getWidth() + 300,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage3().getHeight() - 400);        
        bubbles.add(bubble3);

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(resources.getBackgroundImage(), walls, projectiles, pickups, bubbles,
                time, id);

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
        LinkedList<AbstractBubble> bubbles = new LinkedList<>();
        
        Bubble1 bubble1 = new Bubble1(resources, resources.getVwallImage().getWidth() + 100,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage1().getHeight() - 400);        
        bubbles.add(bubble1);
        
        Bubble2 bubble2 = new Bubble2(resources, resources.getVwallImage().getWidth() + 200,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage2().getHeight() - 400);        
        bubbles.add(bubble2);
        
        Bubble3 bubble3 = new Bubble3(resources, resources.getVwallImage().getWidth() + 300,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage3().getHeight() - 400);        
        bubbles.add(bubble3);
        
        Bubble4 bubble4 = new Bubble4(resources, resources.getVwallImage().getWidth() + 400,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage4().getHeight() - 400);        
        bubbles.add(bubble4);
        
        Bubble5 bubble5 = new Bubble5(resources, resources.getVwallImage().getWidth() + 500,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage5().getHeight() - 400);        
        bubbles.add(bubble5);
        
        Bubble6 bubble6 = new Bubble6(resources, resources.getVwallImage().getWidth() + 600,
                game.getContainerHeight() - resources.getWallImage().getHeight()
                - resources.getBubbleImage6().getHeight() - 400);        
        bubbles.add(bubble6);


        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 240000;

        return new Level(resources.getBackgroundImage(), walls, projectiles, pickups, bubbles,
                time, id);
    }
}
