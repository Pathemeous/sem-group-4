package nl.tudelft.model;

import java.util.LinkedList;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.semgroup4.Resources;

public class LevelFactory {

    private Game game;
    private int levelCount = 4;

    public LevelFactory(Game game) {
        this.game = game;
    }
    
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

        for (int i = 1; i <= levelCount; i++) {
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

    private LinkedList<Wall> wallInit() {
        LinkedList<Wall> walls = new LinkedList<>();

        walls.add(new Wall(Resources.vwallImage, 0, 0));
        walls.add(new Wall(Resources.vwallImage, game.getContainerWidth()
                - Resources.vwallImage.getWidth(), 0));

        walls.add(new Wall(Resources.wallImage, 0, 0));
        for (int i = 1; i <= 5; i++) {
            // five walls of space for the UI
            walls.add(new Wall(Resources.wallImage, 0, game.getContainerHeight()
                    - Resources.wallImage.getHeight() * i));
        }
        return walls;
    }

    private Level getLevel1() {
        final int id = 1;

        LinkedList<Wall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 100, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 6));

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);

    }

    private Level getLevel2() {
        final int id = 2;

        LinkedList<Wall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 100, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 6));
        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 300, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 5));

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);

    }

    private Level getLevel3() {
        final int id = 3;

        final LinkedList<Wall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 100, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 3));
        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 200, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 3));
        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 300, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 3));

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 120000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);

    }

    private Level getLevel4() {
        final int id = 4;

        final LinkedList<Wall> walls = wallInit();

        // Create Bubbles for level
        LinkedList<Bubble> bubbles = new LinkedList<>();

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 100, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 6));

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 200, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 5));

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 300, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 4));

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 400, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 3));

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 500, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 2));

        bubbles.add(new Bubble(Resources.vwallImage.getWidth() + 600, game.getContainerHeight()
                - Resources.wallImage.getHeight() - Resources.bubbleImage6.getWidth() - 400, 1));

        LinkedList<Projectile> projectiles = new LinkedList<>();
        LinkedList<Pickup> pickups = new LinkedList<>();
        int time = 240000;

        return new Level(walls, projectiles, pickups, bubbles, time, id);
    }
}
