package nl.tudelft.model;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Level implements Updateable {

    private ArrayList<GameObject> objects;
    private double time;
    private double speed;

    /**
     * Creates a level object with an object list, a timer and a speed.
     * @param objects ArrayList<GameObject> - list of all objects in the game.
     * @param time double - the time that the users have to finish the level.
     * @param speed double - the level speed.
     */
    public Level(ArrayList<GameObject> objects, double time, double speed) {
        this.objects = objects;
        this.time = time;
        this.speed = speed;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return this.time;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }

    /**
     * Checks whether the level is completed.
     * 
     * <p>A level is completed when there are no bubbles left.</p>
     * 
     * @return true iff there are no bubbles remaining in this level.
     */
    public boolean isCompleted() {
        for (GameObject ob : this.objects) {
            if (ob instanceof Bubble) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the level has failed.
     * 
     * <p>A level has failed when a) the timer runs out; b) a player collides with a bubble.</p>
     * 
     * @return boolean - true if the timer hits zero or below.
     */
    public boolean hasFailed() {
        return this.time <= 0;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        // TODO Auto-generated method stub
        
    }

}
