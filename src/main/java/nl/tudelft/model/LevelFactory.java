package nl.tudelft.model;

import nl.tudelft.model.pickups.Pickup;
import nl.tudelft.semgroup4.Resources;

import java.util.LinkedList;


public class LevelFactory {

    private Game game;

    public LevelFactory(Game game) {
        this.game = game;
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
            default:
                throw new IllegalArgumentException();
        }
    }

    private Level getLevel1() {
        final int id = 1;

        LinkedList<Wall> walls = new LinkedList<>();

        walls.add(new Wall(Resources.vwallImage, 0, 0));
        walls.add(new Wall(Resources.vwallImage, game.getContainerWidth()
                - Resources.vwallImage.getWidth(), 0));

        walls.add(new Wall(Resources.wallImage, 0, 0));
        walls.add(new Wall(Resources.wallImage, 0, game.getContainerHeight()
                - Resources.wallImage.getHeight()));

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
        double time = 120;
        
        return new Level(walls, projectiles, pickups, bubbles, time, id);

    }

}
